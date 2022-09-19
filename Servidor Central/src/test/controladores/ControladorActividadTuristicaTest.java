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
	private static IControladorActividadTuristica cat;
	private static IControladorUsuario cu;
	private static IControladorPaquete cp;

	private static List<String> muestraCategorias;
	private static LocalDate localDateNow;
	private static LocalDateTime localDateTimeNow;

	private static LocalDate localDateVieja;
	private static LocalDate localDateMuyVieja;
	private static LocalDate localDateFuturo;

	@BeforeAll
	static void preparacionPrevia() {
		cat = Fabrica.getInstancia().getIControladorActividadTuristica();
		cu = Fabrica.getInstancia().getIControladorUsuario();
		cp = Fabrica.getInstancia().getIControladorPaquete();

		try {
			/*
			 * TODO descomentar cuando esté implementado
			 * cat.altaCategoria("EXTREMO"); cat.altaCategoria("ARTE");
			 * cat.altaCategoria("TRANQUILO");
			 */
		} catch (Exception e) { // TODO: cambiar Exception a el tipo específico
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
	static void generarActividades(int cant, String id) throws ActividadTuristicaYaRegistradaException {
		preparacionPrevia();
		assertTrue(cat != null);

		for (int i = 0; i < cant; i++) {
			String nickProveedor = "Proveedor " + id + " i=" + i;
			String departamento = "Departamento " + id + " i=" + i;
			String nombreActividad = "Actividad " + id + " i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10.85;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = localDateNow;

			cat.altaActividadTuristica(nickProveedor, departamento, nombreActividad, descripcion, duracion, costo,
					ciudad, fechaAlta, null, muestraCategorias);
		}
	}

	// No es un test en sí
	static void generarSalidas(int cant, String id) throws SalidaYaRegistradaException,
			FechaAltaActividadPosteriorAFechaAltaSalidaException, FechaAltaSalidaPosteriorAFechaSalidaException {
		preparacionPrevia();
		assertTrue(cat != null);

		for (int i = 0; i < cant; i++) {
			String nombreActividad = "Actividad " + id + " i=" + i;
			String nombreSalida = "Salida " + id + " i=" + i;
			String ciudad = "Ciudad salida";
			LocalDate fechaAlta = localDateNow;
			LocalDateTime fechaYHoraSalida = localDateTimeNow.plusDays(40);
			int cantMaxTur = 50;

			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaYHoraSalida, fechaAlta, ciudad, cantMaxTur,
					null);
		}
	}

	// No es un test en sí
	static void generarDepartamentos(int cant, String id) throws DeparamentoYaRegistradoException {
		preparacionPrevia();
		assertTrue(cat != null);

		for (int i = 0; i < cant; i++) {
			String nom = "Departamento " + id + " i=" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";

			cat.altaDepartamento(nom, descr, url);
		}
	}

	@Test
	public void testAltaDepartamentoOK() {
		try {
			generarDepartamentos(100, "testAltaDepartamentoOK");
		} catch (DeparamentoYaRegistradoException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAltaDepartamentoRepetido() {
		assertTrue(cat != null);

		try {
			generarDepartamentos(1, "testAltaDepartamentoRepetido");
		} catch (DeparamentoYaRegistradoException e) {
			fail(e.getMessage());
		}

		// Repito y debería tirar la excepcion
		assertThrows(DeparamentoYaRegistradoException.class, () -> {
			generarDepartamentos(1, "testAltaDepartamentoRepetido");
		});
	}

	@Test
	public void testObtenerIdDepartamentos() {
		assertTrue(cat != null);

		for (int i = 0; i < 100; i++) {
			String nom = "Dep testObtenerIdDepartamentos" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";

			try {
				cat.altaDepartamento(nom, descr, url);
			} catch (DeparamentoYaRegistradoException e) {
				fail(e.getMessage());
			}
			;

			var ids_loop = cat.obtenerIdDepartamentos();

			// Ambos departamentos deberían estar una única vez
			assertTrue(ids_loop.remove(nom));

			assertFalse(ids_loop.remove(nom));
		}

		var ids = cat.obtenerIdDepartamentos();
		for (int i = 0; i < 100; i++) {
			String nom = "Dep testObtenerIdDepartamentos" + i;
			// Los paquetes deberían estar una única vez
			assertTrue(ids.remove(nom));
			assertFalse(ids.remove(nom));
		}
	}

	@Test
	public void testAltaActividadTuristicaOK() {
		assertTrue(cat != null);

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
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			}

			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			}

			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
						ciudad, fechaAlta, null, muestraCategorias);
			} catch (ActividadTuristicaYaRegistradaException e) {
				fail(e.getMessage());
			}

			// assertTrue(cat.existeActividadTuristica(nombreActividad));
			assertTrue(cat.obtenerIdActividadesTuristicas(departamento).contains(nombreActividad));

			DTActividadTuristicaDetalle act = cat.obtenerDTActividadTuristicaDetalle(nombreActividad);
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
	public void testAltaActividadTuristicaRepetida() {
		assertTrue(cat != null);

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
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			}

			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			}

			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
						ciudad, fechaAlta, null, muestraCategorias);
			} catch (ActividadTuristicaYaRegistradaException e) {
				fail(e.getMessage());
			}

			// Repito y debería tirar la excepcion
			assertThrows(ActividadTuristicaYaRegistradaException.class, () -> {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
						ciudad, fechaAlta, null, muestraCategorias);
			});

			// assertTrue(cat.existeActividadTuristica(nombreActividad));
			assertTrue(cat.obtenerIdActividadesTuristicas(departamento).contains(nombreActividad));

			DTActividadTuristicaDetalle act = cat.obtenerDTActividadTuristicaDetalle(nombreActividad);
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
	public void testObtenerIdActividadesTuristicas() {
		assertTrue(cat != null);

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
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			}

			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			}

			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
						ciudad, fechaAlta, null, muestraCategorias);
			} catch (ActividadTuristicaYaRegistradaException e) {
				fail(e.getMessage());
			}

			var ids_loop = cat.obtenerIdActividadesTuristicas(departamento);

			// Ambos departamentos deberían estar una única vez
			assertTrue(ids_loop.remove(nombreActividad));

			assertFalse(ids_loop.remove(nombreActividad));
		}
	}

	@Test
	public void testObtenerIdSalidasTuristicas() {
		assertTrue(cat != null);

		String id = "testObtenerIdSalidasTuristicas";

		try {
			ControladorUsuarioTest.generarProveedores(50, id);
			ControladorActividadTuristicaTest.generarDepartamentos(50, id);
			ControladorActividadTuristicaTest.generarActividades(50, id);
			ControladorActividadTuristicaTest.generarSalidas(50, id);
		} catch (UsuarioYaRegistradoException e) {
			fail(e.getMessage());
		} catch (DeparamentoYaRegistradoException e) {
			fail(e.getMessage());
		} catch (ActividadTuristicaYaRegistradaException e) {
			fail(e.getMessage());
		} catch (SalidaYaRegistradaException e) {
			fail(e.getMessage());
		} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException e) {
			fail(e.getMessage());
		} catch (FechaAltaSalidaPosteriorAFechaSalidaException e) {
			fail(e.getMessage());
		}

		for (int j = 0; j < 50; j++) {
			String nombreActividad = "Actividad " + id + " i=" + j;
			String nombreSalida = "Salida " + id + " i=" + j;
			var salidas = cat.obtenerIdSalidasTuristicas(nombreActividad);

			assertEquals(1, salidas.size());
			assertEquals(nombreSalida, salidas.get(0));
		}
	}

	@Test
	public void testObtenerDetallesActividadTuristica() {
		assertTrue(cat != null);
		String descripcion = "Desc";

		// paquetes:
		String nombre = "Paquete";
		int periodovalidez = 15;
		float descuento = (float) 1;
		try {
			cp.altaPaquete(nombre, descripcion, periodovalidez, descuento, LocalDate.of(2022, 1, 1), null);
		} catch (PaqueteYaRegistradoException e) {
			fail(e.getMessage());
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
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			}

			try {
				cu.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null,
						nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			}

			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
						ciudad, fechaAlta, null, muestraCategorias);
			} catch (ActividadTuristicaYaRegistradaException e) {
				fail(e.getMessage());
			}
			if (i % 2 == 0) {
				try {
					cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaConHoraAhora.plusYears(7),
							fechaAlta.plusYears(6), "lugar", 5, null);
				} catch (SalidaYaRegistradaException | FechaAltaActividadPosteriorAFechaAltaSalidaException
						| FechaAltaSalidaPosteriorAFechaSalidaException e) {
					fail(e.getMessage());
				}
			}

			try {
				cp.agregarActividadAPaquete(nombreActividad, "Paquete");
			} catch (ActividadTuristicaYaRegistradaException e) {
				fail(e.getMessage());
			}

			DTActividadTuristicaDetalle act = cat.obtenerDTActividadTuristicaDetalle(nombreActividad);
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
	public void testObtenerDTSalidasTuristicas() {
		String id = "testObtenerDTSalidasTuristicas";

		try {
			ControladorUsuarioTest.generarProveedores(100, id);
			ControladorActividadTuristicaTest.generarDepartamentos(100, id);
			generarActividades(100, id);
			generarSalidas(100, id);
		} catch (UsuarioYaRegistradoException | DeparamentoYaRegistradoException
				| ActividadTuristicaYaRegistradaException | SalidaYaRegistradaException
				| FechaAltaActividadPosteriorAFechaAltaSalidaException
				| FechaAltaSalidaPosteriorAFechaSalidaException e) {
			fail(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			String nombreActividad = "Actividad " + id + " i=" + i;
			String nombreSalida = "Salida " + id + " i=" + i;
			String ciudad = "Ciudad salida";
			LocalDate fechaAlta = localDateNow;
			LocalDateTime fechaYHoraSalida = localDateTimeNow.plusDays(40);
			int cantMaxTur = 50;

			var salidas = cat.obtenerDTSalidasTuristicas(nombreActividad);

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
	public void testAltaInscripcionSalidaTuristicaOK() {
		String nickname = "Turista ";
		String nombre = "NOMBRE TURISTA";
		String apellido = "APELLIDO TURISTA";
		String correo = "TURISTA ";
		String nacionalidad = "CHINA";
		LocalDate FNacimiento = localDateNow;
		try {
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, null, nacionalidad);
		} catch (UsuarioYaRegistradoException e) {
			fail(e.getMessage());
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
		LocalDate f = localDateNow;
		LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
		LocalDate fechaAltaSalida = f;
		String lugar = "lugar";
		int cantMaxTuristas = 10;

		try {
			cat.altaDepartamento(departamento, descripcion, departamento);
		} catch (DeparamentoYaRegistradoException e) {
			fail(e.getMessage());
		}
		try {
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
		} catch (UsuarioYaRegistradoException e) {
			// Esperable, no pasa nada.
		}

		try {
			cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
					ciudad, fechaAlta, null, muestraCategorias);
		} catch (ActividadTuristicaYaRegistradaException e) {
			fail(e.getMessage());
		}

		try {
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
		} catch (SalidaYaRegistradaException e) {
			fail(e.getMessage());
		} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException e) {
			fail(e.getMessage());
		} catch (FechaAltaSalidaPosteriorAFechaSalidaException e) {
			fail(e.getMessage());
		}

		try {
			cat.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5));
		} catch (InscripcionYaRegistradaException | SuperaElMaximoDeTuristasException
				| FechaAltaSalidaTuristicaPosteriorAFechaInscripcion
				| AltaInscripcionPosteriorAFechaSalidaException e) {
			fail(e.getMessage());
		}

		assertEquals(cat.obtenerDTInscripcion(nickname, nombreSalida).getFechaInscripcion(), localDateNow.plusYears(5));
		assertEquals(cat.obtenerDTInscripcion(nickname, nombreSalida).getCantidadTuristas(), 1);
		assertEquals(cat.obtenerDTInscripcion(nickname, nombreSalida).getTurista(), nickname);
		assertEquals(cat.obtenerDTInscripcion(nickname, nombreSalida).getSalidaTuristica(), nombreSalida);
		assertEquals(cat.obtenerDTInscripcion(nickname, nombreSalida).getCosto(), costo);

		// TODO: agregar test de compra cuando se implemente

	}

	@Test
	public void testAltaInscripcionSalidaTuristicaRepetida() {

		String nickname = "testAltaInscripcionSalidaTuristicaRepetida Turista nickname";
		String nombre = "testAltaInscripcionSalidaTuristicaRepetida NOMBRE TURISTA";
		String apellido = "testAltaInscripcionSalidaTuristicaRepetida APELLIDO TURISTA";
		String correo = "testAltaInscripcionSalidaTuristicaRepetida TURISTA correo";
		String nacionalidad = "CHINA";
		LocalDate FNacimiento = localDateNow;

		String nombreProveedor = "testAltaInscripcionSalidaTuristicaRepetida prov";
		String departamento = "testAltaInscripcionSalidaTuristicaRepetida deptoTest";
		String nombreActividad = "testAltaInscripcionSalidaTuristicaRepetida actividad";
		String descripcion = "testAltaInscripcionSalidaTuristicaRepetida Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow;
		String nombreSalida = "testAltaInscripcionSalidaTuristicaRepetida salida";
		LocalDate f = localDateNow;
		LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
		LocalDate fechaAltaSalida = f;
		String lugar = "lugar";
		int cantMaxTuristas = 10;

		try {
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, null, nacionalidad);
			cat.altaDepartamento(departamento, descripcion, departamento);
			cu.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null,
					nombreProveedor, nombreProveedor);
			cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
					ciudad, fechaAlta, null, muestraCategorias);
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
			cat.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5));
		} catch (UsuarioYaRegistradoException | DeparamentoYaRegistradoException
				| ActividadTuristicaYaRegistradaException | SalidaYaRegistradaException
				| FechaAltaActividadPosteriorAFechaAltaSalidaException | FechaAltaSalidaPosteriorAFechaSalidaException
				| InscripcionYaRegistradaException | SuperaElMaximoDeTuristasException
				| FechaAltaSalidaTuristicaPosteriorAFechaInscripcion
				| AltaInscripcionPosteriorAFechaSalidaException e) {
			fail(e.getMessage());
		}

		assertThrows(InscripcionYaRegistradaException.class, () -> {
			cat.altaInscripcionSalidaTuristica(nombreSalida, nickname, 2, localDateNow.plusYears(5));
		});
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida() {
		String nickname = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida Turista nickname";
		String nombre = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida NOMBRE TURISTA";
		String apellido = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida APELLIDO TURISTA";
		String correo = "testAltaInscripcionSalidaTuristicaInscripcionConFechaInscripcionPosteriorAFechaAltaSalida TURISTA correo";
		String nacionalidad = "CHINA";
		LocalDate FNacimiento = localDateNow;
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
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, null, nacionalidad);
			cat.altaDepartamento(departamento, descripcion, departamento);
			cu.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, localDateNow, null,
					nombreProveedor, nombreProveedor);
			cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
					ciudad, localDateNow, null, muestraCategorias);
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, localDateTimeNow, localDateNow, lugar,
					cantMaxTuristas, null);
		} catch (UsuarioYaRegistradoException | DeparamentoYaRegistradoException
				| ActividadTuristicaYaRegistradaException | SalidaYaRegistradaException
				| FechaAltaActividadPosteriorAFechaAltaSalidaException
				| FechaAltaSalidaPosteriorAFechaSalidaException e) {
			fail(e.getMessage());
		}

		assertThrows(FechaAltaSalidaTuristicaPosteriorAFechaInscripcion.class, () -> {
			cat.altaInscripcionSalidaTuristica(nombreSalida, nickname, 2, localDateNow.minusDays(1));
		});
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada() {

		String nickname = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada Turista nickname";
		String nombre = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada NOMBRE TURISTA";
		String apellido = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada APELLIDO TURISTA";
		String correo = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada TURISTA correo";
		String nacionalidad = "CHINA";
		LocalDate FNacimiento = localDateNow;

		String nombreProveedor = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada prov";
		String departamento = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada deptoTest";
		String nombreActividad = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada actividad";
		String descripcion = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow;
		String nombreSalida = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada salida";
		LocalDate f = localDateNow;
		LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
		LocalDate fechaAltaSalida = f;
		String lugar = "lugar";
		int cantMaxTuristas = 3;

		try {
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, null, nacionalidad);
			cat.altaDepartamento(departamento, descripcion, departamento);
			cu.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta, null,
					nombreProveedor, nombreProveedor);
			cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
					ciudad, fechaAlta, null, muestraCategorias);
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
			cat.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, localDateNow.plusYears(5));
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		assertThrows(SuperaElMaximoDeTuristasException.class, () -> {
			cat.altaInscripcionSalidaTuristica(nombreSalida, nickname + "2", 2, localDateNow.plusYears(5));
		});
	}

	@Test
	public void testAltaSalidaTuristicaOK() {
		assertTrue(cat != null);

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
			LocalDate f = localDateNow;
			LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
			LocalDate fechaAltaSalida = f;
			String lugar = "lugar";
			int cantMaxTuristas = 10;

			try {
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}
			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}

			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
						ciudad, fechaAlta, null, muestraCategorias);
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}

			try {
				cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
						cantMaxTuristas, null);
			} catch (SalidaYaRegistradaException e) {
				fail(e.getMessage());
			} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException e) {
				fail(e.getMessage());
			} catch (FechaAltaSalidaPosteriorAFechaSalidaException e) {
				fail(e.getMessage());
			}

			// assertTrue(cat.existeSalidaTuristica(nombreSalida));

			DTSalidaTuristica sal = cat.obtenerDTSalidaTuristica(nombreSalida);
			assertTrue(sal != null);

			assertEquals(nombreSalida, sal.getNombre());
			assertEquals(fechaHoraSalida, sal.getFechaHoraSalida());
			assertEquals(fechaAlta, sal.getFechaAlta());
			assertEquals(lugar, sal.getLugarSalida());
			assertEquals(cantMaxTuristas, sal.getCantMaxTuristas());

			DTSalidaTuristicaDetalle salDetalle = cat.obtenerDTSalidaTuristicaDetalle(nombreSalida);
			assertTrue(salDetalle.getInscripciones().isEmpty());
		}
	}

	@Test
	public void testAltaSalidaTuristicaRepetida() {
		assertTrue(cat != null);

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
			LocalDate f = localDateNow;
			LocalDateTime fechaHoraSalida = localDateTimeNow.plusMonths(1);
			LocalDate fechaAltaSalida = f;
			String lugar = "lugar";
			int cantMaxTuristas = 10;

			try {
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}
			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
						nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}

			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
						ciudad, fechaAlta, null, muestraCategorias);
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}
			if (i == 1) {
				assertThrows(SalidaYaRegistradaException.class, () -> {
					cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
							cantMaxTuristas, null);
				});
			} else {
				try {
					cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
							cantMaxTuristas, null);
				} catch (SalidaYaRegistradaException e) {
					fail(e.getMessage());
				} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException e) {
					fail(e.getMessage());
				} catch (FechaAltaSalidaPosteriorAFechaSalidaException e) {
					fail(e.getMessage());
				}
			}

		}
	}

	@Test
	public void testAltaSalidaTuristicaFewchaAltaActividadPosteriorASalida() {
		assertTrue(cat != null);
		String nombreProveedor = "Proveedor testAltaSalidaTuristicaAltaPosteriorASalida";
		String departamento = "Departamento testAltaSalidaTuristicaAltaPosteriorASalida";
		String nombreActividad = "Actividad testAltaSalidaTuristicaAltaPosteriorASalida";
		String descripcion = "Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = localDateNow;

		String nombreSalida = "Salida testAltaSalidaTuristicaAltaPosteriorASalida";
		LocalDate f = localDateNow;
		LocalDateTime fechaHoraSalida = localDateTimeNow.minusMonths(1);
		LocalDate fechaAltaSalida = f;
		String lugar = "lugar";
		int cantMaxTuristas = 10;

		try {
			cat.altaDepartamento(departamento, descripcion, departamento);
		} catch (DeparamentoYaRegistradoException e) {
			// Esperable, no pasa nada.
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}
		try {
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
		} catch (UsuarioYaRegistradoException e) {
			// Esperable, no pasa nada.
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		try {
			cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
					ciudad, fechaAlta, null, muestraCategorias);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		assertThrows(FechaAltaSalidaPosteriorAFechaSalidaException.class, () -> {
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
		});
	}

	@Test
	public void testAltaSalidaTuristicaFechaAltaActividadPosteriorAFechaAltaSalidaException() {
		assertTrue(cat != null);
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
			cat.altaDepartamento(departamento, descripcion, departamento);
		} catch (DeparamentoYaRegistradoException e) {
			// Esperable, no pasa nada.
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}
		try {
			Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor,
					nombreProveedor, nombreProveedor, fechaAlta, null, nombreProveedor, nombreProveedor);
		} catch (UsuarioYaRegistradoException e) {
			// Esperable, no pasa nada.
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		try {
			cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo,
					ciudad, fechaAlta, null, muestraCategorias);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		assertThrows(FechaAltaActividadPosteriorAFechaAltaSalidaException.class, () -> {
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar,
					cantMaxTuristas, null);
		});
	}

}
