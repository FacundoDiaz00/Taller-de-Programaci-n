package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.PaqueteYaRegistradoException;
import excepciones.TurismoUyException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaqueteDetalles;

class ControladorPaqueteTest {
	private static IControladorPaquete contrPaquete;
	private static IControladorUsuario contrUsuario;
	private static IControladorActividadTuristica contrActividad;

	private static List<String> muestraCategorias;

	private static LocalDate localDateNow;
	private static LocalDate localDateVieja;

	@BeforeAll
	static void preparacionPrevia() {
		contrPaquete = Fabrica.getInstancia().getIControladorPaquete();
		contrUsuario = Fabrica.getInstancia().getIControladorUsuario();
		contrActividad = Fabrica.getInstancia().getIControladorActividadTuristica();

		localDateNow = LocalDate.now();
		localDateVieja = LocalDate.of(2022, 1, 1);
	}

	// No es un test en sí.
	static List<String> generarPaquetes(int cant, String idTest) throws TurismoUyException {
		if (contrPaquete == null)
			preparacionPrevia();

		assertTrue(contrPaquete != null);
		List<String> nombrePaquetes = new ArrayList<>();

		for (int i = 0; i < cant; i++) {
			String nombre = "Paquete " + idTest + " i=" + i;
			String descripcion = "Desc";
			int periodovalidez = 15;
			float descuento = (float) (i + 0.025);

			contrPaquete.altaPaquete(nombre, descripcion, periodovalidez, descuento, localDateVieja, null);
			nombrePaquetes.add(nombre);
		}
		return nombrePaquetes;
	}

	static void generarTurista(String id) throws TurismoUyException{
		String nicknameTurista = id + "nickname-turista";
		String nombreTurista = id + "nombre-turista";
		String apellidoTurista = id + "apellido-turista";
		String correoTurista = id + "correo-turista";
		String password = "123";
		String nacionalidad = "uy";

		contrUsuario.altaTurista(nicknameTurista, nombreTurista, apellidoTurista, correoTurista, password, localDateNow, null, nacionalidad);
	}

	static void generarPaquete(String id, int valides) throws TurismoUyException{
		String nombrePaquete = id + "nombre-paquete";
		String descripcionPaquete = id + "descripcion-paquete";
		float descuento = 10;

		contrPaquete.altaPaquete(nombrePaquete, descripcionPaquete, valides, descuento, localDateNow,null);
	}

	static void generarProveedor(String id) throws TurismoUyException{
		String nicknameProveedor = id + "nickname-proveedor";
		String nombreProveedor = id + "nombre-proveedor";
		String apellidoProveedor = id + "apellido-proveedor";
		String correoProveedor = id + "correo-proveedor";
		String password = "123";
		String descripcionProveedor = id + "descripcion-proveedor";
		String urlProveedor = id + "url-proveedor";

		contrUsuario.altaProveedor(nicknameProveedor, nombreProveedor, apellidoProveedor, correoProveedor, password, localDateNow, null,descripcionProveedor, urlProveedor );
	}

	static void generarActividad(String id, String departamento, String nicknameProveedor) throws TurismoUyException{
		String nombreActividad = id + "nombre-actividad";
		String descripcionActividad = id + "descripcion-actividad";
		int duracion = 2;
		float costo = (float) 2.0;
		String cuidad = id + "cuidad-actividad";

		contrActividad.altaActividadTuristica(nicknameProveedor, departamento,nombreActividad, descripcionActividad,duracion,costo, cuidad, localDateNow.minusDays(5), null , new ArrayList<>());
	}



	@Test
	final void testAltaPaqueteOK() throws TurismoUyException {
		generarPaquetes(100, "testAltaPaqueteOK");
	}

	@Test
	final void testAltaPaqueteRepetido() throws TurismoUyException {
		assertTrue(contrPaquete != null);

		generarPaquetes(1, "testAltaPaqueteRepetido");

		assertThrows(PaqueteYaRegistradoException.class, () -> {
			generarPaquetes(1, "testAltaPaqueteRepetido");
		});
	}

	@Test
	final void testObtenerIdPaquetes() throws TurismoUyException {
		String idTest = "testObtenerIdPaquetes";
		int cant = 100;

		generarPaquetes(cant, idTest);

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
	final void testObtenerDetallesPaquetes() throws TurismoUyException {
		String idTest = "testObtenerDetallesPaquetes";

		assertTrue(contrPaquete != null);

		// 10 paquetes
		generarPaquetes(10, idTest);

		// 100 proveedores
		ControladorUsuarioTest.generarProveedores(100, idTest);

		// 100 departamentos
		ControladorActividadTuristicaTest.generarDepartamentos(100, idTest);

		// 100 actividades
		ControladorActividadTuristicaTest.generarActividades(100, idTest);

		// reparto 10 actividades a cada paquete
		for (int i = 0; i < 10; i++) {
			String nombrePaq = "Paquete " + idTest + " i=" + i;
			for (int j = 0; j < 10; j++) {
				String nombreActString = "Actividad " + idTest + " i=" + (i * 10 + j);
				contrPaquete.agregarActividadAPaquete(nombreActString, nombrePaq);
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
	final void testAgregarActividadAPaqueteOK() throws TurismoUyException {
		String idTest = "testAgregarActividadAPaqueteOK";

		String nombreDep = "Departamento " + idTest + " i=" + 0;
		String nombrePaq = "Paquete " + idTest + " i=" + 0;
		String nickProv = "Proveedor " + idTest + " i=" + 0;

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

			controladorAct.altaActividadTuristica(nickProv, nombreDep, nombreActividad, descripcion, duracion, costo,
					ciudad, localDateNow, null, muestraCategorias);

			contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);
		}

	}

	@Test
	final void testAgregarActividadAPaqueteRepetida() throws TurismoUyException {
		String idTest = "testAgregarActividadAPaqueteRepetida";

		String nombreDep = "Departamento " + idTest + " i=" + 0;
		String nombrePaq = "Paquete " + idTest + " i=" + 0;
		String nickProv = "Proveedor " + idTest + " i=" + 0;

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

			controladorAct.altaActividadTuristica(nickProv, nombreDep, nombreActividad, descripcion, duracion, costo,
					ciudad, localDateNow, null, muestraCategorias);

			contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);

			assertThrows(ActividadTuristicaYaRegistradaException.class, () -> {
				contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);
			});
		}
	}

	@Test
	final void testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete() throws TurismoUyException {
		String idTest = "testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete";

		String nombreDep = "Departamento " + idTest + " i=" + 0;
		String nombrePaq = "Paquete " + idTest + " i=" + 0;
		String nickProvBase = "Proveedor " + idTest + " i=";

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

	@Test
	final void testComprarPaqueteOK() throws TurismoUyException{

		String idTest = "testComprarPaqueteOK";


		List<String> nomPaquetes = generarPaquetes(1, idTest);
		ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
		ControladorUsuarioTest.generarProveedores(1, idTest);
		List<String> nomTuristas = ControladorUsuarioTest.generarTuristas(1, idTest);
		List<String> nomActividades = ControladorActividadTuristicaTest.generarActividades(1, idTest);

		contrActividad.aceptarORechazarActividadTuristica(nomActividades.get(0), true);
		contrPaquete.agregarActividadAPaquete(nomActividades.get(0), nomPaquetes.get(0));

		



	}

}
