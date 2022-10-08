package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.CategoriaYaRegistradaException;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.SalidaYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import excepciones.TurismoUyException;
import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;

class ControladorActividadTuristicaTest {
	private static IControladorActividadTuristica contrActTur;
	private static IControladorUsuario contrUsuario;
	private static IControladorPaquete controladorPaquete;

	private static List<String> muestraCategorias;
	private static LocalDate localDateNow;
	private static LocalDateTime localDateTimeNow;

	@BeforeAll
	static void preparacionPrevia() {
		contrActTur = Fabrica.getInstancia().getIControladorActividadTuristica();
		contrUsuario = Fabrica.getInstancia().getIControladorUsuario();
		controladorPaquete = Fabrica.getInstancia().getIControladorPaquete();

		muestraCategorias = new ArrayList<String>();
		muestraCategorias.add("EXTREMO");
		muestraCategorias.add("ARTE");
		muestraCategorias.add("TRANQUILO");

		for (var cat : muestraCategorias)
			try {
				contrActTur.altaCategoria(cat);
			} catch (CategoriaYaRegistradaException e) {
				// No pasa nada, es esperado
			}

		localDateNow = LocalDate.now();
		localDateTimeNow = LocalDateTime.now();
	}

	// No es un test en sí
	static List<String> generarActividades(int cant, String idTest)
			throws ActividadTuristicaYaRegistradaException, ObjetoNoExisteEnTurismoUy {
		preparacionPrevia();
		assertTrue(contrActTur != null);

		List<String> nombresActividades = new ArrayList<>();

		for (int i = 0; i < cant; i++) {
			String nickProveedor = "Proveedor " + idTest + " i=" + i;
			String departamento = "Departamento " + idTest + " i=" + i;
			String nombreActividad = "Actividad " + idTest + " i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10.85;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			contrActTur.altaActividadTuristica(nickProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);

			nombresActividades.add(nombreActividad);
		}
		return nombresActividades;
	}

	// No es un test en sí
	static List<String> generarSalidas(int cant, String idTest)
			throws SalidaYaRegistradaException, FechaAltaActividadPosteriorAFechaAltaSalidaException,
			FechaAltaSalidaPosteriorAFechaSalidaException, ObjetoNoExisteEnTurismoUy {
		preparacionPrevia();
		assertTrue(contrActTur != null);

		List<String> nombresSalidas = new ArrayList<>();

		for (int i = 0; i < cant; i++) {
			String nombreActividad = "Actividad " + idTest + " i=" + i;
			String nombreSalida = "Salida " + idTest + " i=" + i;
			String ciudad = "Ciudad salida";
			LocalDate fechaAlta = localDateNow;
			LocalDateTime fechaYHoraSalida = localDateTimeNow.plusDays(40);
			int cantMaxTur = 50;

			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaYHoraSalida, fechaAlta, ciudad,
					cantMaxTur, null);

			nombresSalidas.add(nombreSalida);
		}
		return nombresSalidas;
	}

	// No es un test en sí
	static List<String> generarDepartamentos(int cant, String idTest) throws DeparamentoYaRegistradoException {
		preparacionPrevia();
		assertTrue(contrActTur != null);

		List<String> nombreDepartamentos = new ArrayList<>();

		for (int i = 0; i < cant; i++) {
			String nom = "Departamento " + idTest + " i=" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";

			contrActTur.altaDepartamento(nom, descr, url);
			nombreDepartamentos.add(nom);
		}
		return nombreDepartamentos;
	}

	private List<String> generarCategorias(int cant, String idTest) throws CategoriaYaRegistradaException {
		preparacionPrevia();
		assertTrue(contrActTur != null);
		List<String> nombreCategorias = new ArrayList<>();

		for (int i = 0; i < cant; i++) {
			String nom = "Categoria " + idTest + " i=" + i;
			contrActTur.altaCategoria(nom);
			nombreCategorias.add(nom);
		}
		return nombreCategorias;
	}

	@Test
	public void testAltaDepartamentoOK() throws TurismoUyException {
		generarDepartamentos(100, "testAltaDepartamentoOK");
	}

	@Test
	public void testAltaDepartamentoRepetido() throws TurismoUyException {
		assertTrue(contrActTur != null);

		generarDepartamentos(1, "testAltaDepartamentoRepetido");

		// Repito y debería tirar la excepcion
		assertThrows(DeparamentoYaRegistradoException.class, () -> {
			generarDepartamentos(1, "testAltaDepartamentoRepetido");
		});
	}

	@Test
	public void testObtenerIdDepartamentos() throws TurismoUyException {
		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {
			String nom = "Dep testObtenerIdDepartamentos" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";

			contrActTur.altaDepartamento(nom, descr, url);

			var idsLoop = contrActTur.obtenerIdDepartamentos();

			// Ambos departamentos deberían estar una única vez
			assertTrue(idsLoop.remove(nom));

			assertFalse(idsLoop.remove(nom));
		}

		var ids = contrActTur.obtenerIdDepartamentos();
		for (int i = 0; i < 100; i++) {
			String nom = "Dep testObtenerIdDepartamentos" + i;
			// Los paquetes deberían estar una única vez
			assertTrue(ids.remove(nom));
			assertFalse(ids.remove(nom));
		}
	}

	@Test
	public void testAltaActividadTuristicaOK() throws TurismoUyException {
		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {

			String nombreProveedor = "Proveedor testAltaActividadTuristicaOK i=" + (i % 10);
			String departamento = "Departamento testAltaActividadTuristicaOK i=" + (i % 10);
			String nombreActividad = "Actividad testAltaActividadTuristicaOK i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			try {
				contrActTur.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
						nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);

			// assertTrue(cat.existeActividadTuristica(nombreActividad));
			assertTrue(contrActTur.obtenerIdActividadesTuristicas(departamento).contains(nombreActividad));

			DTActividadTuristicaDetalle act = contrActTur.obtenerDTActividadTuristicaDetalle(nombreActividad);
			assertTrue(act != null);

			assertEquals(nombreActividad, act.getNombre());
			assertEquals(descripcion, act.getDescripcion());
			assertEquals(duracion, act.getDuracion());
			assertEquals(costo, act.getCostoPorTurista());
			assertEquals(ciudad, act.getCuidad());
			assertEquals(fechaAlta, act.getFechaAlta());

			assertTrue(act.getPaquetes().isEmpty());
			assertTrue(act.getSalidas().isEmpty());
		}
	}

	@Test
	public void testAltaActividadTuristicaSinFecha() throws TurismoUyException {
		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {

			String nombreProveedor = "Proveedor testAltaActividadTuristicaSinFecha i=" + (i % 10);
			String departamento = "Departamento testAltaActividadTuristicaSinFecha i=" + (i % 10);
			String nombreActividad = "Actividad testAltaActividadTuristicaSinFecha i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";

			try {
				contrActTur.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, nombreProveedor, null, null, nombreProveedor,
						nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, null, null, muestraCategorias);

			// assertTrue(cat.existeActividadTuristica(nombreActividad));
			assertTrue(contrActTur.obtenerIdActividadesTuristicas(departamento).contains(nombreActividad));

			DTActividadTuristicaDetalle act = contrActTur.obtenerDTActividadTuristicaDetalle(nombreActividad);
			assertTrue(act != null);

			assertEquals(nombreActividad, act.getNombre());
			assertEquals(descripcion, act.getDescripcion());
			assertEquals(duracion, act.getDuracion());
			assertEquals(costo, act.getCostoPorTurista());
			assertEquals(ciudad, act.getCuidad());
			assertNotEquals(null, act.getFechaAlta());

			assertTrue(act.getPaquetes().isEmpty());
			assertTrue(act.getSalidas().isEmpty());
		}
	}

	@Test
	public void testAltaActividadTuristicaRepetida() throws TurismoUyException {
		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {

			String nombreProveedor = "Proveedor testAltaActividadTuristicaRepetida i=" + (i % 10);
			String departamento = "Departamento testAltaActividadTuristicaRepetida i=" + (i % 10);
			String nombreActividad = "Actividad testAltaActividadTuristicaRepetida i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			try {
				contrActTur.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
						nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);

			// Repito y debería tirar la excepcion
			assertThrows(ActividadTuristicaYaRegistradaException.class, () -> {
				contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion,
						duracion, costo, ciudad, fechaAlta, null, muestraCategorias);
			});

			// assertTrue(cat.existeActividadTuristica(nombreActividad));
			assertTrue(contrActTur.obtenerIdActividadesTuristicas(departamento).contains(nombreActividad));

			DTActividadTuristicaDetalle act = contrActTur.obtenerDTActividadTuristicaDetalle(nombreActividad);
			assertTrue(act != null);

			assertEquals(nombreActividad, act.getNombre());
			assertEquals(descripcion, act.getDescripcion());
			assertEquals(duracion, act.getDuracion());
			assertEquals(costo, act.getCostoPorTurista());
			assertEquals(ciudad, act.getCuidad());
			assertEquals(fechaAlta, act.getFechaAlta());

			assertTrue(act.getPaquetes().isEmpty());
			assertTrue(act.getSalidas().isEmpty());
		}
	}

	@Test
	public void testObtenerIdActividadesTuristicas() throws TurismoUyException {
		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {
			String nombreProveedor = "Proveedor testObtenerIdActividadesTuristicas i=" + (i % 10);
			String departamento = "Departamento testObtenerIdActividadesTuristicas i=" + (i % 10);
			String nombreActividad = "Actividad testObtenerIdActividadesTuristicas i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			try {
				contrActTur.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
						nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);

			var ids_loop = contrActTur.obtenerIdActividadesTuristicas(departamento);

			// Ambos departamentos deberían estar una única vez
			assertTrue(ids_loop.remove(nombreActividad));

			assertFalse(ids_loop.remove(nombreActividad));
		}
	}

	@Test
	public void testObtenerIdSalidasTuristicas() throws TurismoUyException {
		assertTrue(contrActTur != null);

		String idTest = "testObtenerIdSalidasTuristicas";

		ControladorUsuarioTest.generarProveedores(50, idTest);
		ControladorActividadTuristicaTest.generarDepartamentos(50, idTest);
		ControladorActividadTuristicaTest.generarActividades(50, idTest);
		ControladorActividadTuristicaTest.generarSalidas(50, idTest);

		for (int j = 0; j < 50; j++) {
			String nombreActividad = "Actividad " + idTest + " i=" + j;
			String nombreSalida = "Salida " + idTest + " i=" + j;
			var salidas = contrActTur.obtenerIdSalidasTuristicas(nombreActividad);

			assertEquals(1, salidas.size());
			assertEquals(nombreSalida, salidas.get(0));
		}
	}

	@Test
	public void testObtenerDetallesActividadTuristica() throws TurismoUyException {
		assertTrue(contrActTur != null);
		String descripcion = "Desc";

		// paquetes:
		String nombre = "Paquete";
		int periodovalidez = 15;
		float descuento = (float) 1;
		controladorPaquete.altaPaquete(nombre, descripcion, periodovalidez, descuento, LocalDate.of(2022, 1, 1), null);

		for (int i = 0; i < 100; i++) {
			String nombreProveedor = "Proveedor testObtenerDetallesActividadTuristica i=" + (i % 10);
			String departamento = "Departamento testObtenerDetallesActividadTuristica i=" + (i % 10);
			String nombreActividad = "Actividad testObtenerDetallesActividadTuristica i=" + i;
			String nombreSalida = "Salida testObtenerDetallesActividadTuristica i=" + i;
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;
			LocalDateTime fechaConHoraAhora = localDateTimeNow;

			try {
				contrActTur.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			try {
				contrUsuario.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor,
						nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);
			if (i % 2 == 0) {
				contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaConHoraAhora.plusYears(7),
						fechaAlta.plusYears(6), "lugar", 5, null);
			}

			controladorPaquete.agregarActividadAPaquete(nombreActividad, "Paquete");

			DTActividadTuristicaDetalle act = contrActTur.obtenerDTActividadTuristicaDetalle(nombreActividad);
			assertTrue(act != null);

			assertEquals(nombreActividad, act.getNombre());
			assertEquals(descripcion, act.getDescripcion());
			assertEquals(duracion, act.getDuracion());
			assertEquals(costo, act.getCostoPorTurista());
			assertEquals(ciudad, act.getCuidad());
			assertEquals(fechaAlta, act.getFechaAlta());
			assertFalse(act.getPaquetes().isEmpty());

			if (i % 2 == 0) {
				assertNotEquals(0, act.getSalidas().size());

				var nombAct = ((DTSalidaTuristica) act.getSalidas().values().toArray()[0]).getActividad();

				DTSalidaTuristica salida = act.getSalida(nombreSalida);

				assertNotEquals(null, salida);

				assertEquals(nombreSalida, salida.getNombre());
				assertEquals(5, salida.getCantMaxTuristas());
				assertEquals(fechaAlta.plusYears(6), salida.getFechaAlta());
				assertEquals(fechaConHoraAhora.plusYears(7), salida.getFechaHoraSalida());
			} else {
				assertTrue(act.getSalidas().isEmpty());
			}
			assertTrue(act.getPaquetes().containsKey("Paquete"));

		}
	}

	@Test
	public void testObtenerDTSalidasTuristicas() throws TurismoUyException {
		String idTest = "testObtenerDTSalidasTuristicas";

		ControladorUsuarioTest.generarProveedores(100, idTest);
		ControladorActividadTuristicaTest.generarDepartamentos(100, idTest);
		generarActividades(100, idTest);
		generarSalidas(100, idTest);

		for (int i = 0; i < 100; i++) {
			String nombreActividad = "Actividad " + idTest + " i=" + i;
			String nombreSalida = "Salida " + idTest + " i=" + i;
			String ciudad = "Ciudad salida";
			LocalDate fechaAlta = localDateNow;
			LocalDateTime fechaYHoraSalida = localDateTimeNow.plusDays(40);
			int cantMaxTur = 50;

			var salidas = contrActTur.obtenerDTSalidasTuristicas(nombreActividad);

			assertEquals(1, salidas.size());

			for (var sal : salidas) {
				assertEquals(nombreSalida, sal.getNombre());
				assertEquals(ciudad, sal.getLugarSalida());
				assertEquals(fechaAlta, sal.getFechaAlta());
				assertEquals(fechaYHoraSalida, sal.getFechaHoraSalida());
				assertEquals(cantMaxTur, sal.getCantMaxTuristas());
			}
		}
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaOK() throws TurismoUyException {
		String nickname = "Turista ";
		String nombre = "NOMBRE TURISTA";
		String apellido = "APELLIDO TURISTA";
		String correo = "TURISTA ";
		String nacionalidad = "CHINA";
		LocalDate fNacimiento = localDateNow;
		contrUsuario.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
		String nombreProveedor = "prov";
		String departamento = "deptoTest";
		String nombreActividad = "actividad";
		String descripcion = "Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow;

		String nombreSalida = "salida";
		LocalDate fecha = localDateNow;
		LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
		LocalDate fechaAltaSalida = fecha;
		String lugar = "lugar";
		int cantMaxTuristas = 10;

		contrActTur.altaDepartamento(departamento, descripcion, departamento);
		try {
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
					nombreProveedor);
		} catch (UsuarioYaRegistradoException exception) {
			// Esperable, no pasa nada.
		}

		contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
				ciudad, fechaAlta, null, muestraCategorias);

		contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
				cantMaxTuristas, null);

		contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5), null);

		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getFechaInscripcion(),
				localDateNow.plusYears(5));
		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getCantidadTuristas(), 1);
		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getTurista(), nickname);
		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getSalidaTuristica(), nombreSalida);
		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getCosto(), costo);

		// TODO: agregar test de compra cuando se implemente

	}

	@Test
	public void testAltaInscripcionSalidaTuristicaRepetida() throws TurismoUyException {

		String nickname = "testAltaInscripcionSalidaTuristicaRepetida Turista nickname";
		String nombre = "testAltaInscripcionSalidaTuristicaRepetida NOMBRE TURISTA";
		String apellido = "testAltaInscripcionSalidaTuristicaRepetida APELLIDO TURISTA";
		String correo = "testAltaInscripcionSalidaTuristicaRepetida TURISTA correo";
		String nacionalidad = "CHINA";
		LocalDate fNacimiento = localDateNow;

		String nombreProveedor = "testAltaInscripcionSalidaTuristicaRepetida prov";
		String departamento = "testAltaInscripcionSalidaTuristicaRepetida deptoTest";
		String nombreActividad = "testAltaInscripcionSalidaTuristicaRepetida actividad";
		String descripcion = "testAltaInscripcionSalidaTuristicaRepetida Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow;
		String nombreSalida = "testAltaInscripcionSalidaTuristicaRepetida salida";
		LocalDate fecha = localDateNow;
		LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
		LocalDate fechaAltaSalida = fecha;
		String lugar = "lugar";
		int cantMaxTuristas = 10;

		contrUsuario.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
		contrActTur.altaDepartamento(departamento, descripcion, departamento);
		contrUsuario.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor,
				fechaAlta, null, nombreProveedor, nombreProveedor);
		contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
				ciudad, fechaAlta, null, muestraCategorias);
		contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
				cantMaxTuristas, null);
		contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5), null);

		assertThrows(InscripcionYaRegistradaException.class, () -> {
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 2, localDateNow.plusYears(5), null);
		});
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida()
			throws TurismoUyException {
		String nickname = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida Turista nickname";
		String nombre = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida NOMBRE TURISTA";
		String apellido = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida APELLIDO TURISTA";
		String correo = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida TURISTA correo";
		String nacionalidad = "CHINA";
		LocalDate fNacimiento = localDateNow;
		String nombreProveedor = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida prov";
		String departamento = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida deptoTest";
		String nombreActividad = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida actividad";
		String descripcion = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		String nombreSalida = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida salida";
		String lugar = "lugar";
		int cantMaxTuristas = 3;

		contrUsuario.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
		contrActTur.altaDepartamento(departamento, descripcion, departamento);
		contrUsuario.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor,
				localDateNow, null, nombreProveedor, nombreProveedor);
		contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
				ciudad, localDateNow, null, muestraCategorias);
		contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, localDateTimeNow, localDateNow, lugar,
				cantMaxTuristas, null);

		assertThrows(FechaAltaSalidaTuristicaPosteriorAFechaInscripcion.class, () -> {
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 2, localDateNow.minusDays(1), null);
		});
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada() throws TurismoUyException {

		String nickname = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada Turista nickname";
		String nombre = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada NOMBRE TURISTA";
		String apellido = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada APELLIDO TURISTA";
		String correo = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada TURISTA correo";
		String nacionalidad = "CHINA";
		LocalDate fNacimiento = localDateNow;

		String nombreProveedor = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada prov";
		String departamento = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada deptoTest";
		String nombreActividad = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada actividad";
		String descripcion = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow;
		String nombreSalida = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada salida";
		LocalDate fecha = localDateNow;
		LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
		LocalDate fechaAltaSalida = fecha;
		String lugar = "lugar";
		int cantMaxTuristas = 3;

		contrUsuario.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
		contrActTur.altaDepartamento(departamento, descripcion, departamento);
		contrUsuario.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor,
				fechaAlta, null, nombreProveedor, nombreProveedor);
		contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
				ciudad, fechaAlta, null, muestraCategorias);
		contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
				cantMaxTuristas, null);
		contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5), null);

		assertThrows(SuperaElMaximoDeTuristasException.class, () -> {
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname + "2", 2, localDateNow.plusYears(5), null);
		});
	}

	@Test
	public void testAltaSalidaTuristicaOK() throws TurismoUyException {
		String id = "testAltaSalidaTuristicaOK";

		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {

			String nombreProveedor = "Proveedor " + id + " i=" + (i % 10);
			String departamento = "Departamento " + id + " i=" + (i % 10);
			String nombreActividad = "Actividad " + id + " i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			String nombreSalida = "Salida " + id + i;
			LocalDate fecha = localDateNow;
			LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
			LocalDate fechaAltaSalida = fecha;
			String lugar = "lugar";
			int cantMaxTuristas = 10;

			try {
				contrActTur.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}
			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
						nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);

			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);

			// assertTrue(cat.existeSalidaTuristica(nombreSalida));

			DTSalidaTuristica sal = contrActTur.obtenerDTSalidaTuristica(nombreSalida);
			assertTrue(sal != null);

			assertEquals(nombreSalida, sal.getNombre());
			assertEquals(fechaHoraSalida, sal.getFechaHoraSalida());
			assertEquals(fechaAlta, sal.getFechaAlta());
			assertEquals(lugar, sal.getLugarSalida());
			assertEquals(cantMaxTuristas, sal.getCantMaxTuristas());

			DTSalidaTuristicaDetalle salDetalle = contrActTur.obtenerDTSalidaTuristicaDetalle(nombreSalida);
			assertTrue(salDetalle.getInscripciones().isEmpty());
		}
	}

	@Test
	public void testAltaSalidaTuristicaSinFecha() throws TurismoUyException {
		String id = "testAltaSalidaTuristicaSinFecha";

		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {

			String nombreProveedor = "Proveedor " + id + " i=" + (i % 10);
			String departamento = "Departamento " + id + " i=" + (i % 10);
			String nombreActividad = "Actividad " + id + " i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			String nombreSalida = "Salida " + id + i;
			LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
			LocalDate fechaAltaSalida = null;
			String lugar = "lugar";
			int cantMaxTuristas = 10;

			try {
				contrActTur.altaDepartamento(departamento, descripcion, departamento);
			} catch (TurismoUyException exception) {
				// OK, falla porque creamos repetidos a propósito
			}
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
					nombreProveedor);

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);

			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
			DTSalidaTuristica sal = contrActTur.obtenerDTSalidaTuristica(nombreSalida);
			assertTrue(sal != null);
			assertNotEquals(fechaAltaSalida, sal.getFechaAlta());
		}
	}

	@Test
	public void testAltaSalidaTuristicaRepetida() throws TurismoUyException {
		String identif = "testAltaSalidaTuristicaRepetida";

		assertTrue(contrActTur != null);

		for (int i = 0; i < 2; i++) {

			String nombreProveedor = "Proveedor " + identif + " i=" + (i % 10);
			String departamento = "Departamento " + identif + " i=" + (i % 10);
			String nombreActividad = "Actividad " + identif + " i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			String nombreSalida = "Salida " + identif;
			LocalDate fecha = localDateNow;
			LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
			LocalDate fechaAltaSalida = fecha;
			String lugar = "lugar";
			int cantMaxTuristas = 10;

			try {
				contrActTur.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}
			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
						nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);
			if (i == 1) {
				assertThrows(SalidaYaRegistradaException.class, () -> {
					contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida,
							lugar, cantMaxTuristas, null);
				});
			} else {
				contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
						cantMaxTuristas, null);
			}

		}
	}

	@Test
	public void testAltaSalidaTuristicaFewchaAltaActividadPosteriorASalida() throws TurismoUyException {
		String identif = "testAltaSalidaTuristicaFewchaAltaActividadPosteriorASalida";

		assertTrue(contrActTur != null);
		String nombreProveedor = "Proveedor " + identif;
		String departamento = "Departamento " + identif;
		String nombreActividad = "Actividad " + identif;
		String descripcion = "Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow;

		String nombreSalida = "Salida " + identif;
		LocalDate fecha = localDateNow;
		LocalDateTime fechaHoraSalida = localDateTimeNow.minusMonths(1);
		LocalDate fechaAltaSalida = fecha;
		String lugar = "lugar";
		int cantMaxTuristas = 10;

		try {
			contrActTur.altaDepartamento(departamento, descripcion, departamento);
		} catch (DeparamentoYaRegistradoException exception) {
			// Esperable, no pasa nada.
		}
		try {
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
					nombreProveedor);
		} catch (UsuarioYaRegistradoException exception) {
			// Esperable, no pasa nada.
		}

		contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
				ciudad, fechaAlta, null, muestraCategorias);

		assertThrows(FechaAltaSalidaPosteriorAFechaSalidaException.class, () -> {
			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
		});
	}

	@Test
	public void testAltaSalidaTuristicaFechaAltaActividadPosteriorAFechaAltaSalidaException()
			throws TurismoUyException {
		assertTrue(contrActTur != null);
		String nombreProveedor = "Proveedor testAltaSalidaTuristicaFechaAltaSalidaPosteriorAFechaSalida";
		String departamento = "Departamento testAltaSalidaTuristicaFechaAltaSalidaPosteriorAFechaSalida";
		String nombreActividad = "Actividad testAltaSalidaTuristicaFechaAltaSalidaPosteriorAFechaSalida";
		String descripcion = "Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow.plusMonths(1);

		String nombreSalida = "Salida testAltaSalidaTuristicaFechaAltaSalidaPosteriorAFechaSalida";
		LocalDateTime fechaHoraSalida = localDateTimeNow;
		LocalDate fechaAltaSalida = fechaHoraSalida.toLocalDate();
		String lugar = "lugar";
		int cantMaxTuristas = 10;

		try {
			contrActTur.altaDepartamento(departamento, descripcion, departamento);
		} catch (DeparamentoYaRegistradoException exception) {
			// Esperable, no pasa nada.
		}
		try {
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor,
					nombreProveedor);
		} catch (UsuarioYaRegistradoException exception) {
			// Esperable, no pasa nada.
		}

		contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
				ciudad, fechaAlta, null, muestraCategorias);

		assertThrows(FechaAltaActividadPosteriorAFechaAltaSalidaException.class, () -> {
			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
		});
	}

	@Test
	public void testCategorias() throws TurismoUyException {
		generarCategorias(100, "testCategorias");

		var categorias = contrActTur.obtenerIdCategorias();

		var categoriasTest = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			String nom = "Categoria testCategorias i=" + i;
			categoriasTest.add(nom);
		}

		categoriasTest.forEach((var cat) -> assertTrue(categorias.contains(cat)));
	}

	@Test
	public void testObtenerIdActividadesTuristicasConfirmadasPorCategoria() throws TurismoUyException {
		String id = "testObtenerIdActividadesTuristicasConfirmadasPorCategoria";
		generarDepartamentos(2, id);
		ControladorUsuarioTest.generarProveedores(2, id);
		generarActividades(2, id);

		contrActTur.aceptarORechazarActividadTuristica("Actividad " + id + " i=0", true);
		contrActTur.aceptarORechazarActividadTuristica("Actividad " + id + " i=1", false);

		for (int i = 0; i < muestraCategorias.size(); i++) {
			var actividades = contrActTur
					.obtenerIdActividadesTuristicasConfirmadasPorCategoria(muestraCategorias.get(i));
			assertTrue(actividades.contains("Actividad " + id + " i=0"));
			assertFalse(actividades.contains("Actividad " + id + " i=1"));
		}
	}

	@Test
	public void testObtenerDTActividadesTuristicas() throws TurismoUyException {
		String id = "testObtenerDTActividadesTuristicas";
		generarDepartamentos(2, id);
		ControladorUsuarioTest.generarProveedores(2, id);
		generarActividades(2, id);

		contrActTur.aceptarORechazarActividadTuristica("Actividad " + id + " i=0", true);
		contrActTur.aceptarORechazarActividadTuristica("Actividad " + id + " i=1", false);

		var actividades = contrActTur.obtenerDTActividadesTuristicas();

		boolean esta0 = false;
		boolean esta1 = false;
		for (DTActividadTuristica dtActividadTuristica : actividades) {
			if (dtActividadTuristica.getNombre().equals("Actividad " + id + " i=0"))
				esta0 = true;
			if (dtActividadTuristica.getNombre().equals("Actividad " + id + " i=1"))
				esta1 = true;
		}

		assertTrue(esta0);
		assertFalse(esta1);
	}

	@Test
	public void testObtenerDTActividadesTuristicasConfirmadasPorDepartamento() throws TurismoUyException {
		String id = "testObtenerDTActividadesTuristicasConfirmadasPorDepartamento";
		generarDepartamentos(5, id);
		ControladorUsuarioTest.generarProveedores(5, id);
		generarActividades(5, id);

		contrActTur.aceptarORechazarActividadTuristica("Actividad " + id + " i=0", true);
		contrActTur.aceptarORechazarActividadTuristica("Actividad " + id + " i=1", false);

		String dep = "Departamento " + id + " i=0";
		var actividades = contrActTur.obtenerDTActividadesTuristicasConfirmadasPorDepartamento(dep);

		boolean[] esta = new boolean[1];
		esta[0] = false;

		actividades.forEach((var actividad) -> {
			esta[0] = esta[0] || actividad.getNombre().equals("Actividad " + id + " i=0");
		});
		assertTrue(esta[0]);

		dep = "Departamento " + id + " i=1";
		actividades = contrActTur.obtenerDTActividadesTuristicasConfirmadasPorDepartamento(dep);

		esta[0] = false;

		actividades.forEach((var actividad) -> {
			esta[0] = esta[0] || actividad.getNombre().equals("Actividad " + id + " i=1");
		});
		assertFalse(esta[0]);

	}

	@Test
	public void testObtenerIdActividadesTuristicasAgregadas() throws TurismoUyException {
		String id = "testObtenerIdActividadesTuristicasAgregadas";
		generarDepartamentos(3, id);
		ControladorUsuarioTest.generarProveedores(3, id);
		generarActividades(3, id);

		contrActTur.aceptarORechazarActividadTuristica("Actividad " + id + " i=0", true);
		contrActTur.aceptarORechazarActividadTuristica("Actividad " + id + " i=1", false);

		var actividades = contrActTur.obtenerIdActividadesTuristicasAgregadas();

		assertTrue(actividades.contains("Actividad " + id + " i=2"));

		assertFalse(actividades.contains("Actividad " + id + " i=0"));
		assertFalse(actividades.contains("Actividad " + id + " i=1"));

	}

}
