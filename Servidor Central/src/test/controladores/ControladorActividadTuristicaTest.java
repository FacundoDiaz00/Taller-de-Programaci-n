package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.PaqueteYaRegistradoException;
import excepciones.SalidaYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import excepciones.TurismoUyException;
import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;
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

	// TODO: modificar todas las fechas
	// puestas a mano por alguna de
	// estas
	private static LocalDate localDateVieja;
	private static LocalDate localDateMuyVieja;
	private static LocalDate localDateFuturo;

	@BeforeAll
	static void preparacionPrevia() {
		contrActTur = Fabrica.getInstancia().getIControladorActividadTuristica();
		contrUsuario = Fabrica.getInstancia().getIControladorUsuario();
		controladorPaquete = Fabrica.getInstancia().getIControladorPaquete();

		try {
			/*
			 * TODO descomentar cuando esté implementado
			 * cat.altaCategoria("EXTREMO"); cat.altaCategoria("ARTE");
			 * cat.altaCategoria("TRANQUILO");
			 */
		} catch (Exception exception) { // TODO: cambiar Exception a el tipo
										// específico
			// que tira altaCategoria
			// Nada, las categorias ya fueron agregadas
		}

		muestraCategorias = new ArrayList<String>();
		muestraCategorias.add("EXTREMO");
		muestraCategorias.add("ARTE");
		muestraCategorias.add("TRANQUILO");

		localDateNow = LocalDate.now();
		localDateTimeNow = LocalDateTime.now();
		localDateVieja = LocalDate.of(2022, 1, 1);
		localDateMuyVieja = LocalDate.of(2010, 1, 1);
		localDateFuturo = LocalDate.of(2026, 1, 1);
	}

	// No es un test en sí
	static void generarActividades(int cant, String idTest)
			throws ActividadTuristicaYaRegistradaException, ObjetoNoExisteEnTurismoUy {
		preparacionPrevia();
		assertTrue(contrActTur != null);

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
		}
	}

	// No es un test en sí
	static void generarSalidas(int cant, String idTest)
			throws SalidaYaRegistradaException, FechaAltaActividadPosteriorAFechaAltaSalidaException,
			FechaAltaSalidaPosteriorAFechaSalidaException, ObjetoNoExisteEnTurismoUy {
		preparacionPrevia();
		assertTrue(contrActTur != null);

		for (int i = 0; i < cant; i++) {
			String nombreActividad = "Actividad " + idTest + " i=" + i;
			String nombreSalida = "Salida " + idTest + " i=" + i;
			String ciudad = "Ciudad salida";
			LocalDate fechaAlta = localDateNow;
			LocalDateTime fechaYHoraSalida = localDateTimeNow.plusDays(40);
			int cantMaxTur = 50;

			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaYHoraSalida, fechaAlta, ciudad,
					cantMaxTur, null);
		}
	}

	// No es un test en sí
	static void generarDepartamentos(int cant, String idTest) throws DeparamentoYaRegistradoException {
		preparacionPrevia();
		assertTrue(contrActTur != null);

		for (int i = 0; i < cant; i++) {
			String nom = "Departamento " + idTest + " i=" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";

			contrActTur.altaDepartamento(nom, descr, url);
		}
	}

	@Test
	public void testAltaDepartamentoOK() {
		try {
			generarDepartamentos(100, "testAltaDepartamentoOK");
		} catch (DeparamentoYaRegistradoException exception) {
			fail(exception.getMessage());
		}
	}

	@Test
	public void testAltaDepartamentoRepetido() {
		assertTrue(contrActTur != null);

		try {
			generarDepartamentos(1, "testAltaDepartamentoRepetido");
		} catch (DeparamentoYaRegistradoException exception) {
			fail(exception.getMessage());
		}

		// Repito y debería tirar la excepcion
		assertThrows(DeparamentoYaRegistradoException.class, () -> {
			generarDepartamentos(1, "testAltaDepartamentoRepetido");
		});
	}

	@Test
	public void testObtenerIdDepartamentos() {
		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {
			String nom = "Dep testObtenerIdDepartamentos" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";

			try {
				contrActTur.altaDepartamento(nom, descr, url);
			} catch (DeparamentoYaRegistradoException exception) {
				fail(exception.getMessage());
			}

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
	public void testAltaActividadTuristicaOK()
			throws ObjetoNoExisteEnTurismoUy, ActividadTuristicaYaRegistradaException {
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
						nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
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
	public void testAltaActividadTuristicaRepetida()
			throws ActividadTuristicaYaRegistradaException, ObjetoNoExisteEnTurismoUy {
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
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
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
	public void testObtenerIdActividadesTuristicas()
			throws ObjetoNoExisteEnTurismoUy, ActividadTuristicaYaRegistradaException {
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
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
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
	public void testObtenerIdSalidasTuristicas() throws ObjetoNoExisteEnTurismoUy {
		assertTrue(contrActTur != null);

		String idTest = "testObtenerIdSalidasTuristicas";

		try {
			ControladorUsuarioTest.generarProveedores(50, idTest);
			ControladorActividadTuristicaTest.generarDepartamentos(50, idTest);
			ControladorActividadTuristicaTest.generarActividades(50, idTest);
			ControladorActividadTuristicaTest.generarSalidas(50, idTest);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		for (int j = 0; j < 50; j++) {
			String nombreActividad = "Actividad " + idTest + " i=" + j;
			String nombreSalida = "Salida " + idTest + " i=" + j;
			var salidas = contrActTur.obtenerIdSalidasTuristicas(nombreActividad);

			assertEquals(1, salidas.size());
			assertEquals(nombreSalida, salidas.get(0));
		}
	}

	@Test
	public void testObtenerDetallesActividadTuristica()
			throws ActividadTuristicaYaRegistradaException, ObjetoNoExisteEnTurismoUy {
		assertTrue(contrActTur != null);
		String descripcion = "Desc";

		// paquetes:
		String nombre = "Paquete";
		int periodovalidez = 15;
		float descuento = (float) 1;
		try {
			controladorPaquete.altaPaquete(nombre, descripcion, periodovalidez, descuento, LocalDate.of(2022, 1, 1),
					null);
		} catch (PaqueteYaRegistradoException exception) {
			fail(exception.getMessage());
		}

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
				contrUsuario.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor,
						fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);
			if (i % 2 == 0) {
				try {
					contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaConHoraAhora.plusYears(7),
							fechaAlta.plusYears(6), "lugar", 5, null);
				} catch (SalidaYaRegistradaException | FechaAltaActividadPosteriorAFechaAltaSalidaException
						| FechaAltaSalidaPosteriorAFechaSalidaException exception) {
					fail(exception.getMessage());
				}
			}

			try {
				controladorPaquete.agregarActividadAPaquete(nombreActividad, "Paquete");
			} catch (ActividadTuristicaYaRegistradaException exception) {
				fail(exception.getMessage());
			}

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
	public void testObtenerDTSalidasTuristicas() throws ObjetoNoExisteEnTurismoUy {
		String idTest = "testObtenerDTSalidasTuristicas";

		try {
			ControladorUsuarioTest.generarProveedores(100, idTest);
			ControladorActividadTuristicaTest.generarDepartamentos(100, idTest);
			generarActividades(100, idTest);
			generarSalidas(100, idTest);
		} catch (UsuarioYaRegistradoException | DeparamentoYaRegistradoException
				| ActividadTuristicaYaRegistradaException | SalidaYaRegistradaException
				| FechaAltaActividadPosteriorAFechaAltaSalidaException
				| FechaAltaSalidaPosteriorAFechaSalidaException exception) {
			fail(exception.getMessage());
		}

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
	public void testAltaInscripcionSalidaTuristicaOK() throws ObjetoNoExisteEnTurismoUy {
		String nickname = "Turista ";
		String nombre = "NOMBRE TURISTA";
		String apellido = "APELLIDO TURISTA";
		String correo = "TURISTA ";
		String nacionalidad = "CHINA";
		LocalDate fNacimiento = localDateNow;
		try {
			contrUsuario.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
		} catch (UsuarioYaRegistradoException exception) {
			fail(exception.getMessage());
		}
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

		try {
			contrActTur.altaDepartamento(departamento, descripcion, departamento);
		} catch (DeparamentoYaRegistradoException exception) {
			fail(exception.getMessage());
		}
		try {
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
		} catch (UsuarioYaRegistradoException exception) {
			// Esperable, no pasa nada.
		}

		try {
			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);
		} catch (ActividadTuristicaYaRegistradaException exception) {
			fail(exception.getMessage());
		}

		try {
			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
		} catch (SalidaYaRegistradaException exception) {
			fail(exception.getMessage());
		} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException exception) {
			fail(exception.getMessage());
		} catch (FechaAltaSalidaPosteriorAFechaSalidaException exception) {
			fail(exception.getMessage());
		}

		try {
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5));
		} catch (InscripcionYaRegistradaException | SuperaElMaximoDeTuristasException
				| FechaAltaSalidaTuristicaPosteriorAFechaInscripcion
				| AltaInscripcionPosteriorAFechaSalidaException exception) {
			fail(exception.getMessage());
		}

		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getFechaInscripcion(),
				localDateNow.plusYears(5));
		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getCantidadTuristas(), 1);
		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getTurista(), nickname);
		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getSalidaTuristica(), nombreSalida);
		assertEquals(contrActTur.obtenerDTInscripcion(nickname, nombreSalida).getCosto(), costo);

		// TODO: agregar test de compra cuando se implemente

	}

	@Test
	public void testAltaInscripcionSalidaTuristicaRepetida() {

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

		try {
			contrUsuario.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
			contrActTur.altaDepartamento(departamento, descripcion, departamento);
			contrUsuario.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta,
					null, nombreProveedor, nombreProveedor);
			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);
			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5));
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		assertThrows(InscripcionYaRegistradaException.class, () -> {
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 2, localDateNow.plusYears(5));
		});
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida() {
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

		try {
			contrUsuario.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
			contrActTur.altaDepartamento(departamento, descripcion, departamento);
			contrUsuario.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, localDateNow,
					null, nombreProveedor, nombreProveedor);
			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, localDateNow, null, muestraCategorias);
			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, localDateTimeNow, localDateNow, lugar,
					cantMaxTuristas, null);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		assertThrows(FechaAltaSalidaTuristicaPosteriorAFechaInscripcion.class, () -> {
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 2, localDateNow.minusDays(1));
		});
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada() {

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

		try {
			contrUsuario.altaTurista(nickname, nombre, apellido, correo, "1234",fNacimiento, null, nacionalidad);
			contrActTur.altaDepartamento(departamento, descripcion, departamento);
			contrUsuario.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta,
					null, nombreProveedor, nombreProveedor);
			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);
			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5));
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		assertThrows(SuperaElMaximoDeTuristasException.class, () -> {
			contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname + "2", 2, localDateNow.plusYears(5));
		});
	}

	@Test
	public void testAltaSalidaTuristicaOK() throws ObjetoNoExisteEnTurismoUy {
		assertTrue(contrActTur != null);

		for (int i = 0; i < 100; i++) {

			String nombreProveedor = "Proveedor testAltaSalidaTuristicaOK i=" + (i % 10);
			String departamento = "Departamento testAltaSalidaTuristicaOK i=" + (i % 10);
			String nombreActividad = "Actividad testAltaSalidaTuristicaOK i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			String nombreSalida = "Salida testAltaSalidaTuristicaOK" + i;
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
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			try {
				contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion,
						duracion, costo, ciudad, fechaAlta, null, muestraCategorias);
			} catch (TurismoUyException exception) {
				fail(exception.getMessage());
			}

			try {
				contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
						cantMaxTuristas, null);
			} catch (SalidaYaRegistradaException exception) {
				fail(exception.getMessage());
			} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException exception) {
				fail(exception.getMessage());
			} catch (FechaAltaSalidaPosteriorAFechaSalidaException exception) {
				fail(exception.getMessage());
			}

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
	public void testAltaSalidaTuristicaRepetida() throws ObjetoNoExisteEnTurismoUy {
		assertTrue(contrActTur != null);

		for (int i = 0; i < 2; i++) {

			String nombreProveedor = "Proveedor testAltaSalidaTuristicaRepetida i=" + (i % 10);
			String departamento = "Departamento testAltaSalidaTuristicaRepetida i=" + (i % 10);
			String nombreActividad = "Actividad testAltaSalidaTuristicaRepetida i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			String nombreSalida = "Salida testAltaSalidaTuristicaRepetida";
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
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException exception) {
				// Esperable, no pasa nada.
			}

			try {
				contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion,
						duracion, costo, ciudad, fechaAlta, null, muestraCategorias);
			} catch (TurismoUyException exception) {
				fail(exception.getMessage());
			}
			if (i == 1) {
				assertThrows(SalidaYaRegistradaException.class, () -> {
					contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida,
							lugar, cantMaxTuristas, null);
				});
			} else {
				try {
					contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida,
							lugar, cantMaxTuristas, null);
				} catch (SalidaYaRegistradaException exception) {
					fail(exception.getMessage());
				} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException exception) {
					fail(exception.getMessage());
				} catch (FechaAltaSalidaPosteriorAFechaSalidaException exception) {
					fail(exception.getMessage());
				}
			}

		}
	}

	@Test
	public void testAltaSalidaTuristicaFewchaAltaActividadPosteriorASalida() {
		assertTrue(contrActTur != null);
		String nombreProveedor = "Proveedor testAltaSalidaTuristicaAltaPosteriorASalida";
		String departamento = "Departamento testAltaSalidaTuristicaAltaPosteriorASalida";
		String nombreActividad = "Actividad testAltaSalidaTuristicaAltaPosteriorASalida";
		String descripcion = "Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow;

		String nombreSalida = "Salida testAltaSalidaTuristicaAltaPosteriorASalida";
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
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
		} catch (UsuarioYaRegistradoException exception) {
			// Esperable, no pasa nada.
		}

		try {
			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		assertThrows(FechaAltaSalidaPosteriorAFechaSalidaException.class, () -> {
			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
		});
	}

	@Test
	public void testAltaSalidaTuristicaFechaAltaActividadPosteriorAFechaAltaSalidaException() {
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
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
		} catch (UsuarioYaRegistradoException exception) {
			// Esperable, no pasa nada.
		}

		try {
			contrActTur.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion,
					costo, ciudad, fechaAlta, null, muestraCategorias);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		assertThrows(FechaAltaActividadPosteriorAFechaAltaSalidaException.class, () -> {
			contrActTur.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
		});
	}

}
