package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.PaqueteYaRegistradoException;
import excepciones.TurismoUyException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaqueteDetalles;

class ControladorPaqueteTest {
	private static IControladorPaquete contrPaquete;

	private static List<String> muestraCategorias;

	private static LocalDate localDateNow;
	private static LocalDate localDateVieja;

	@BeforeAll
	static void preparacionPrevia() {
		contrPaquete = Fabrica.getInstancia().getIControladorPaquete();

		try {
			/*
			 * TODO descomentar cuando esté implementado
			 * cat.altaCategoria("EXTREMO"); cat.altaCategoria("ARTE");
			 * cat.altaCategoria("TRANQUILO");
			 */
		} catch (Exception exception) {
			// Nada, las categorias ya fueron agregadas
		}

		muestraCategorias = new ArrayList<String>();
		muestraCategorias.add("EXTREMO");
		muestraCategorias.add("ARTE");
		muestraCategorias.add("TRANQUILO");

		localDateNow = LocalDate.now();
		localDateVieja = LocalDate.of(2022, 1, 1);
	}

	// No es un test en sí.
	static void generarPaquetes(int cant, String idTest) throws PaqueteYaRegistradoException {
		if (contrPaquete == null)
			preparacionPrevia();

		assertTrue(contrPaquete != null);

		for (int i = 0; i < cant; i++) {
			String nombre = "Paquete " + idTest + " i=" + i;
			String descripcion = "Desc";
			int periodovalidez = 15;
			float descuento = (float) (i + 0.025);

			contrPaquete.altaPaquete(nombre, descripcion, periodovalidez, descuento, localDateVieja, null);
		}
	}

	@Test
	final void testAltaPaqueteOK() {
		try {
			generarPaquetes(100, "testAltaPaqueteOK");
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}
	}

	@Test
	final void testAltaPaqueteRepetido() {
		assertTrue(contrPaquete != null);

		try {
			generarPaquetes(1, "testAltaPaqueteRepetido");
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		assertThrows(PaqueteYaRegistradoException.class, () -> {
			generarPaquetes(1, "testAltaPaqueteRepetido");
		});
	}

	@Test
	final void testObtenerIdPaquetes() {
		String idTest = "testObtenerIdPaquetes";
		int cant = 100;

		try {
			generarPaquetes(cant, idTest);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		var ids = contrPaquete.obtenerIdPaquetes();

		assertTrue(ids != null);

		int tamanioAntes = ids.size();

		// chequeo turistas
		for (int i = 0; i < cant; i++) {
			String nombre = "Paquete " + idTest + " i=" + i;

			// Cada tur. deberían estar una única vez
			assertTrue(ids.remove(nombre));
			assertFalse(ids.remove(nombre));
		}

		assertEquals(tamanioAntes - cant, ids.size());

	}

	@Test
	final void testObtenerDetallesPaquetes() {
		String idTest = "testObtenerDetallesPaquetes";

		assertTrue(contrPaquete != null);

		// 10 paquetes
		try {
			generarPaquetes(10, idTest);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		// 100 proveedores
		try {
			ControladorUsuarioTest.generarProveedores(100, idTest);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		// 100 departamentos
		try {
			ControladorActividadTuristicaTest.generarDepartamentos(100, idTest);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		// 100 actividades
		try {
			ControladorActividadTuristicaTest.generarActividades(100, idTest);
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		// reparto 10 actividades a cada paquete
		for (int i = 0; i < 10; i++) {
			String nombrePaq = "Paquete " + idTest + " i=" + i;
			for (int j = 0; j < 10; j++) {
				String nombreActString = "Actividad " + idTest + " i=" + (i * 10 + j);
				try {
					contrPaquete.agregarActividadAPaquete(nombreActString, nombrePaq);
				} catch (TurismoUyException exception) {
					fail(exception.getMessage());
				}
			}
		}

		var paqList = contrPaquete.obtenerDTPaquetesDetalles();
		assertTrue(paqList != null);

		// Para cada paquete busco si aparece correctamente
		for (int i = 0; i < 10; i++) {
			String nombrePaq = "Paquete " + idTest + " i=" + i;
			String descripcion = "Desc";
			int periodovalidez = 15;
			float descuento = (float) (i + 0.025);

			boolean existePaq = false;
			for (DTPaqueteDetalles paq : paqList) {
				if (paq.getNombre().equals(nombrePaq)) {
					existePaq = true;

					assertEquals(descripcion, paq.getDescrpicion());
					assertEquals(periodovalidez, paq.getValidez());
					// Uso esto por los posibles errores al comparar float y
					// doubles
					assertTrue(Math.abs(descuento - paq.getDescuento()) < 1);

					// Para cada actividad del paquete, busco si aparece
					// correctamente
					var actList = paq.getActividades().values();
					for (int j = 0; j < 10; j++) {
						String nombreActString = "Actividad " + idTest + " i=" + (i * 10 + j);
						String nickProveedor = "Proveedor " + idTest + " i=" + (i * 10 + j);
						String descripcionAct = "Desc";
						int duracion = 10;
						float costo = (float) 10.85;
						String ciudad = "Ciudad";

						boolean existeAct = false;
						for (DTActividadTuristica act : actList) {
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
		String idTest = "testAgregarActividadAPaqueteOK";

		String nombreDep = "Departamento " + idTest + " i=" + 0;
		String nombrePaq = "Paquete " + idTest + " i=" + 0;
		String nickProv = "Proveedor " + idTest + " i=" + 0;

		try {
			// 1 proveedor
			ControladorUsuarioTest.generarProveedores(1, idTest);
			// 1 departamento
			ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
			// 1 paquete
			generarPaquetes(1, idTest);

			// 80 actividades en un mismo departamento, de un mismo provedor
			var controladorAct = Fabrica.getInstancia().getIControladorActividadTuristica();
			for (int i = 0; i < 80; i++) {
				String nombreActividad = "Actividad " + idTest + " i=" + i;
				String descripcion = "Desc";
				int duracion = 10;
				float costo = (float) 10.85;
				String ciudad = "Ciudad";

				controladorAct.altaActividadTuristica(nickProv, nombreDep, nombreActividad, descripcion, duracion,
						costo, ciudad, localDateNow, null, muestraCategorias);

				contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);
			}
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}
	}

	@Test
	final void testAgregarActividadAPaqueteRepetida() {
		String idTest = "testAgregarActividadAPaqueteRepetida";

		String nombreDep = "Departamento " + idTest + " i=" + 0;
		String nombrePaq = "Paquete " + idTest + " i=" + 0;
		String nickProv = "Proveedor " + idTest + " i=" + 0;

		try {
			// 1 proveedor
			ControladorUsuarioTest.generarProveedores(1, idTest);
			// 1 departamento
			ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
			// 1 paquete
			generarPaquetes(1, idTest);

			// 80 actividades en un mismo departamento, de un mismo provedor
			var controladorAct = Fabrica.getInstancia().getIControladorActividadTuristica();
			for (int i = 0; i < 80; i++) {
				String nombreActividad = "Actividad " + idTest + " i=" + i;
				String descripcion = "Desc";
				int duracion = 10;
				float costo = (float) 10.85;
				String ciudad = "Ciudad";

				controladorAct.altaActividadTuristica(nickProv, nombreDep, nombreActividad, descripcion, duracion,
						costo, ciudad, localDateNow, null, muestraCategorias);

				contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);

				assertThrows(ActividadTuristicaYaRegistradaException.class, () -> {
					contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);
				});
			}
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}
	}

	@Test
	final void testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete() throws ObjetoNoExisteEnTurismoUy {
		String idTest = "testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete";

		String nombreDep = "Departamento " + idTest + " i=" + 0;
		String nombrePaq = "Paquete " + idTest + " i=" + 0;
		String nickProvBase = "Proveedor " + idTest + " i=";

		try {
			// 4 proveedores
			ControladorUsuarioTest.generarProveedores(4, idTest);
			// 1 departamento
			ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
			// 1 paquete
			generarPaquetes(1, idTest);

			// 80 actividades en un mismo departamento, 20 por cada proveedor
			var controladorAct = Fabrica.getInstancia().getIControladorActividadTuristica();
			for (int i = 0; i < 80; i++) {
				String nickProveedor = nickProvBase + (i % 4);
				String nombreActividad = "Actividad " + idTest + " i=" + i;
				String descripcion = "Desc";
				int duracion = 10;
				float costo = (float) 10.85;
				String ciudad = "Ciudad";

				controladorAct.altaActividadTuristica(nickProveedor, nombreDep, nombreActividad, descripcion, duracion,
						costo, ciudad, localDateNow, null, muestraCategorias);

				// 10 actividades de cada provedor en un pquete
				if (i % 2 == 0) {
					contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);
				}
			}
		} catch (TurismoUyException exception) {
			fail(exception.getMessage());
		}

		// El resto NO deberían estar en el paquete
		var actList = contrPaquete.obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(nombreDep, nombrePaq);

		for (int i = 0; i < 80; i++) {
			String nombreActividad = "Actividad " + idTest + " i=" + i;
			if (i % 2 == 0) {
				assertFalse(actList.contains(nombreActividad));
			} else {
				assertTrue(actList.contains(nombreActividad));
			}
		}

	}

}
