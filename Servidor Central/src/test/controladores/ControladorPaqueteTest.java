package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import excepciones.PaqueteYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaqueteDetalles;

class ControladorPaqueteTest {
	private static IControladorPaquete cp = null;
	private static IControladorActividadTuristica cat = null;

	private static List<String> muestraCategorias;
	private static LocalDate localDateNow;
	private static LocalDateTime localDateTimeNow;

	private static LocalDate localDateVieja;
	private static LocalDate localDateMuyVieja;
	private static LocalDate localDateFuturo;

	@BeforeAll
	static void preparacionPrevia() {
		cp = Fabrica.getInstancia().getIControladorPaquete();
		cat = Fabrica.getInstancia().getIControladorActividadTuristica();

		try {
			/*
			 * TODO descomentar cuando esté implementado
			 * cat.altaCategoria("EXTREMO"); cat.altaCategoria("ARTE");
			 * cat.altaCategoria("TRANQUILO");
			 */
		} catch (Exception e) {
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

	// No es un test en sí.
	static void generarPaquetes(int cant, String id) throws Exception {
		if (cp == null)
			preparacionPrevia();

		assertTrue(cp != null);

		for (int i = 0; i < cant; i++) {
			String nombre = "Paquete " + id + " i=" + i;
			String descripcion = "Desc";
			int periodovalidez = 15;
			float descuento = (float) (i + 0.025);

			cp.altaPaquete(nombre, descripcion, periodovalidez, descuento, localDateVieja, null);
		}
	}

	@Test
	final void testAltaPaqueteOK() {
		try {
			generarPaquetes(100, "testAltaPaqueteOK");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	final void testAltaPaqueteRepetido() {
		assertTrue(cp != null);

		try {
			generarPaquetes(1, "testAltaPaqueteRepetido");
		} catch (Exception e) {
			fail(e.getMessage());
		}

		assertThrows(PaqueteYaRegistradoException.class, () -> {
			generarPaquetes(1, "testAltaPaqueteRepetido");
		});
	}

	@Test
	final void testObtenerIdPaquetes() {
		String id = "testObtenerIdPaquetes";
		int cant = 100;

		try {
			generarPaquetes(cant, id);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		var ids = cp.obtenerIdPaquetes();

		assertTrue(ids != null);

		int tamanioAntes = ids.size();

		// chequeo turistas
		for (int i = 0; i < cant; i++) {
			String nombre = "Paquete " + id + " i=" + i;

			// Cada tur. deberían estar una única vez
			assertTrue(ids.remove(nombre));
			assertFalse(ids.remove(nombre));
		}

		assertEquals(tamanioAntes - cant, ids.size());

	}

	@Test
	final void testObtenerDetallesPaquetes() {
		String id = "testObtenerDetallesPaquetes";

		assertTrue(cp != null);

		// 10 paquetes
		try {
			generarPaquetes(10, id);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// 100 proveedores
		try {
			ControladorUsuarioTest.generarProveedores(100, id);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// 100 departamentos
		try {
			ControladorActividadTuristicaTest.generarDepartamentos(100, id);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// 100 actividades
		try {
			ControladorActividadTuristicaTest.generarActividades(100, id);
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// reparto 10 actividades a cada paquete
		for (int i = 0; i < 10; i++) {
			String nombrePaq = "Paquete " + id + " i=" + i;
			for (int j = 0; j < 10; j++) {
				String nombreActString = "Actividad " + id + " i=" + (i * 10 + j);
				try {
					cp.agregarActividadAPaquete(nombreActString, nombrePaq);
				} catch (Exception e) {
					fail(e.getMessage());
				}
			}
		}

		var paq_list = cp.obtenerDTPaquetesDetalles();
		assertTrue(paq_list != null);

		// Para cada paquete busco si aparece correctamente
		for (int i = 0; i < 10; i++) {
			String nombrePaq = "Paquete " + id + " i=" + i;
			String descripcion = "Desc";
			int periodovalidez = 15;
			float descuento = (float) (i + 0.025);

			boolean existePaq = false;
			for (DTPaqueteDetalles paq : paq_list) {
				if (paq.getNombre().equals(nombrePaq)) {
					existePaq = true;

					assertEquals(descripcion, paq.getDescrpicion());
					assertEquals(periodovalidez, paq.getValidez());
					// Uso esto por los posibles errores al comparar float y
					// doubles
					assertTrue(Math.abs(descuento - paq.getDescuento()) < 1);

					// Para cada actividad del paquete, busco si aparece
					// correctamente
					var act_list = paq.getActividades().values();
					for (int j = 0; j < 10; j++) {
						String nombreActString = "Actividad " + id + " i=" + (i * 10 + j);
						String nickProveedor = "Proveedor " + id + " i=" + (i * 10 + j);
						String descripcionAct = "Desc";
						int duracion = 10;
						float costo = (float) 10.85;
						String ciudad = "Ciudad";

						boolean existeAct = false;
						for (DTActividadTuristica act : act_list) {
							if (act.getNombre().equals(nombreActString)) {
								existeAct = true;

								assertTrue(Math.abs(costo - act.getCostoPorTurista()) < 1);
								assertEquals(ciudad, act.getCuidad());
								assertEquals(duracion, act.getDuracion());
								assertEquals(descripcionAct, act.getDescripcion());
								assertEquals(descripcionAct, act.getDescripcion());
								assertEquals(nickProveedor, act.getNicknameProveedor());
							}
						}
						assertTrue(existeAct);
					}
				}
			}
			assertTrue(existePaq);
		}

	}

	@Test
	final void testAgregarActividadAPaqueteOK() {
		String id = "testAgregarActividadAPaqueteOK";

		String nombreDep = "Departamento " + id + " i=" + 0;
		String nombrePaq = "Paquete " + id + " i=" + 0;
		String nickProv = "Proveedor " + id + " i=" + 0;

		try {
			// 1 proveedor
			ControladorUsuarioTest.generarProveedores(1, id);
			// 1 departamento
			ControladorActividadTuristicaTest.generarDepartamentos(1, id);
			// 1 paquete
			generarPaquetes(1, id);

			// 80 actividades en un mismo departamento, de un mismo provedor
			var cat = Fabrica.getInstancia().getIControladorActividadTuristica();
			for (int i = 0; i < 80; i++) {
				String nombreActividad = "Actividad " + id + " i=" + i;
				String descripcion = "Desc";
				int duracion = 10;
				float costo = (float) 10.85;
				String ciudad = "Ciudad";

				cat.altaActividadTuristica(nickProv, nombreDep, nombreActividad, descripcion, duracion, costo, ciudad,
						localDateNow, null, muestraCategorias);

				cp.agregarActividadAPaquete(nombreActividad, nombrePaq);
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	final void testAgregarActividadAPaqueteRepetida() {
		String id = "testAgregarActividadAPaqueteRepetida";

		String nombreDep = "Departamento " + id + " i=" + 0;
		String nombrePaq = "Paquete " + id + " i=" + 0;
		String nickProv = "Proveedor " + id + " i=" + 0;

		try {
			// 1 proveedor
			ControladorUsuarioTest.generarProveedores(1, id);
			// 1 departamento
			ControladorActividadTuristicaTest.generarDepartamentos(1, id);
			// 1 paquete
			generarPaquetes(1, id);

			// 80 actividades en un mismo departamento, de un mismo provedor
			var cat = Fabrica.getInstancia().getIControladorActividadTuristica();
			for (int i = 0; i < 80; i++) {
				String nombreActividad = "Actividad " + id + " i=" + i;
				String descripcion = "Desc";
				int duracion = 10;
				float costo = (float) 10.85;
				String ciudad = "Ciudad";

				cat.altaActividadTuristica(nickProv, nombreDep, nombreActividad, descripcion, duracion, costo, ciudad,
						localDateNow, null, muestraCategorias);

				cp.agregarActividadAPaquete(nombreActividad, nombrePaq);

				assertThrows(ActividadTuristicaYaRegistradaException.class, () -> {
					cp.agregarActividadAPaquete(nombreActividad, nombrePaq);
				});
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	final void testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete() {
		String id = "testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete";

		String nombreDep = "Departamento " + id + " i=" + 0;
		String nombrePaq = "Paquete " + id + " i=" + 0;
		String nickProvBase = "Proveedor " + id + " i=";

		try {
			// 4 proveedores
			ControladorUsuarioTest.generarProveedores(4, id);
			// 1 departamento
			ControladorActividadTuristicaTest.generarDepartamentos(1, id);
			// 1 paquete
			generarPaquetes(1, id);

			// 80 actividades en un mismo departamento, 20 por cada proveedor
			var cat = Fabrica.getInstancia().getIControladorActividadTuristica();
			for (int i = 0; i < 80; i++) {
				String nickProveedor = nickProvBase + (i % 4);
				String nombreActividad = "Actividad " + id + " i=" + i;
				String descripcion = "Desc";
				int duracion = 10;
				float costo = (float) 10.85;
				String ciudad = "Ciudad";

				cat.altaActividadTuristica(nickProveedor, nombreDep, nombreActividad, descripcion, duracion, costo,
						ciudad, localDateNow, null, muestraCategorias);

				// 10 actividades de cada provedor en un pquete
				if (i % 2 == 0) {
					cp.agregarActividadAPaquete(nombreActividad, nombrePaq);
				}
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}

		// El resto NO deberían estar en el paquete
		var act_list = cp.obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(nombreDep, nombrePaq);

		for (int i = 0; i < 80; i++) {
			String nombreActividad = "Actividad " + id + " i=" + i;
			if (i % 2 == 0) {
				assertFalse(act_list.contains(nombreActividad));
			} else {
				assertTrue(act_list.contains(nombreActividad));
			}
		}

	}

}
