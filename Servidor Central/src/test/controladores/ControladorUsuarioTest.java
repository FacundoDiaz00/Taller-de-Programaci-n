package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.TurismoUyException;
import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTProveedorDetalle;
import logica.datatypes.DTTurista;
import logica.datatypes.DTTuristaDetalle;

class ControladorUsuarioTest {
	private static IControladorUsuario cu = null;
	private static IControladorActividadTuristica cat = null;
	private static LocalDate localDateNow;

	@BeforeAll
	static void preparacionPrevia() {
		cu = Fabrica.getInstancia().getIControladorUsuario();
		cat = Fabrica.getInstancia().getIControladorActividadTuristica();
		localDateNow = LocalDate.now();
	}

	// No es un test en sí
	static void generarProveedores(int cant, String id) throws UsuarioYaRegistradoException {
		if (cu == null)
			preparacionPrevia();

		assertTrue(cu != null);

		for (int i = 0; i < cant; i++) {
			String nickname = "Proveedor " + id + " i=" + i;
			String nombre = "NOMBRE PROV";
			String apellido = "APELLIDO PROV";
			String correo = "Proveedor " + id + " i=" + i;
			String descripcion = "HOLA! DESCRIPCION";
			String link = "www.google.com.provedoor";
			LocalDate FNacimiento = localDateNow.minusYears(30);

			cu.altaProveedor(nickname, nombre, apellido, correo, FNacimiento, null, descripcion, link);
		}
	}

	// No es un test en sí
	static void generarTuristas(int cant, String id) throws UsuarioYaRegistradoException {
		if (cu == null)
			preparacionPrevia();

		assertTrue(cu != null);

		for (int i = 0; i < cant; i++) {
			String nickname = "Turista " + id + " i=" + i;
			String nombre = "NOMBRE TURISTA";
			String apellido = "APELLIDO TURISTA";
			String correo = "TURISTA " + id + " i=" + i;
			String nacionalidad = "CHINA";
			LocalDate FNacimiento = localDateNow.minusYears(15);

			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, null, nacionalidad);
		}
	}

	@Test
	void testObtenerIdUsuarios() {
		assertTrue(cu != null);

		String id = "testObtenerIdUsuarios";

		try {
			generarProveedores(50, id);
			generarTuristas(50, id);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		var ids = cu.obtenerIdUsuarios();
		assertTrue(ids != null);

		int tamanioAntes = ids.size();

		// chequeo proveedores
		for (int i = 0; i < 50; i++) {
			String nickname = "Proveedor " + id + " i=" + i;

			// Cada prov deberían estar una única vez
			assertTrue(ids.remove(nickname));
			assertFalse(ids.remove(nickname));
		}

		// chequeo turistas
		for (int i = 0; i < 50; i++) {
			String nickname = "Turista " + id + " i=" + i;

			// Cada tur. deberían estar una única vez
			assertTrue(ids.remove(nickname));
			assertFalse(ids.remove(nickname));
		}

		assertEquals(tamanioAntes - 100, ids.size());
	}

	@Test
	void testObtenerIdProveedores() {
		assertTrue(cu != null);

		String id = "testObtenerIdProveedores";

		try {
			generarProveedores(100, id);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		var ids = cu.obtenerIdProveedores();
		assertTrue(ids != null);

		int tamanioAntes = ids.size();

		// chequeo proveedores
		for (int i = 0; i < 100; i++) {
			String nickname = "Proveedor " + id + " i=" + i;

			// Cada prov deberían estar una única vez
			assertTrue(ids.remove(nickname));
			assertFalse(ids.remove(nickname));
		}

		assertEquals(tamanioAntes - 100, ids.size());
	}

	@Test
	void testObtenerIdTuristas() {
		assertTrue(cu != null);

		String id = "testObtenerIdTuristas";

		try {
			generarTuristas(100, id);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		var ids = cu.obtenerIdTuristas();
		assertTrue(ids != null);

		int tamanioAntes = ids.size();

		// chequeo turistas
		for (int i = 0; i < 100; i++) {
			String nickname = "Turista " + id + " i=" + i;

			// Cada tur. deberían estar una única vez
			assertTrue(ids.remove(nickname));
			assertFalse(ids.remove(nickname));
		}

		assertEquals(tamanioAntes - 100, ids.size());
	}

	@Test
	void testAltaTuristaOK() {
		try {
			generarTuristas(100, "testAltaTuristaOK");
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testAltaTuristaRepetido() {
		String id = "testAltaTuristaRepetido";

		try {
			generarTuristas(50, id);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		assertThrows(UsuarioYaRegistradoException.class, () -> {
			generarTuristas(1, id);
		});

		String nickname = "Turista CON NICK NUEVO " + id;
		String nombre = "NOMBRE TURISTA";
		String apellido = "APELLIDO TURISTA";
		String correo = "TURISTA " + id + " i=" + 0;
		String nacionalidad = "CHINA";
		LocalDate FNacimiento = LocalDate.now().minusYears(15);

		assertThrows(UsuarioYaRegistradoException.class, () -> {
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, null, nacionalidad);
		});
	}

	@Test
	void testAltaProveedorOK() {
		try {
			generarProveedores(100, "testAltaProveedorOK");
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testAltaProveedorRepetido() {
		String id = "testAltaProveedorRepetido";

		try {
			generarProveedores(50, id);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		assertThrows(UsuarioYaRegistradoException.class, () -> {
			generarProveedores(1, id);
		});

		String nickname = "Proveedor CON NICK NUEVO " + id;
		String nombre = "NOMBRE PROV";
		String apellido = "APELLIDO PROV";

		// Mismo correo que el generador:
		String correo = "Proveedor " + id + " i=" + 0;
		String descripcion = "HOLA! DESCRIPCION";
		String link = "www.google.com.provedoor";
		LocalDate FNacimiento = LocalDate.now().minusYears(30);

		assertThrows(UsuarioYaRegistradoException.class, () -> {
			cu.altaProveedor(nickname, nombre, apellido, correo, FNacimiento, null, descripcion, link);
		});
	}

	@Test
	void testObtenerDTUsuarioDetalle() {
		String id = "testObtenerDTUsuarioDetalle";

		try {
			generarProveedores(50, id);
			generarTuristas(50, id);
			ControladorActividadTuristicaTest.generarDepartamentos(50, id);
			ControladorActividadTuristicaTest.generarActividades(50, id);
			ControladorActividadTuristicaTest.generarSalidas(50, id);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		// A los primeros 40 les asigno una sola salida
		for (int i = 0; i < 40; i++) {
			String nombreSalida = "Salida " + id + " i=" + i;
			String nickname = "Turista " + id + " i=" + i;
			try {
				cat.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, LocalDate.now().plusYears(5));
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}
		}

		// A los ultimos 10 les asigno una todas las salidas
		for (int i = 40; i < 50; i++) {
			String nickname = "Turista " + id + " i=" + i;
			for (int j = 0; j < 40; j++) {
				String nombreSalida = "Salida " + id + " i=" + j;
				try {
					cat.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, LocalDate.now().plusYears(5));
				} catch (TurismoUyException e) {
					fail(e.getMessage());
				}
			}
		}

		// Verifico para los proveedores
		for (int i = 0; i < 50; i++) {
			String nickname = "Proveedor " + id + " i=" + i;
			var dtdet = cu.obtenerDTUsuarioDetalle(nickname);
			String nombre = "NOMBRE PROV";
			String apellido = "APELLIDO PROV";
			String correo = "Proveedor " + id + " i=" + i;
			String descripcion = "HOLA! DESCRIPCION";
			String link = "www.google.com.provedoor";
			LocalDate FNacimiento = localDateNow.minusYears(30);
			String nombreSalida = "Salida " + id + " i=" + i;
			String nombreActividad = "Actividad " + id + " i=" + i;

			assertThrows(ClassCastException.class, () -> {
				var dtErrorCasteado = (DTTuristaDetalle) dtdet;
			});

			var dtCasteado = (DTProveedorDetalle) dtdet;

			assertEquals(nickname, dtCasteado.getNickname());
			assertEquals(nombre, dtCasteado.getNombre());
			assertEquals(apellido, dtCasteado.getApellido());
			assertEquals(correo, dtCasteado.getCorreo());
			assertEquals(descripcion, dtCasteado.getDescrpicionGeneral());
			assertEquals(link, dtCasteado.getLink());
			assertEquals(FNacimiento, dtCasteado.getFechaNac());

			assertEquals(1, dtCasteado.getActividades().size());
			var nombreActObtenida = dtCasteado.getActividades().get(0).getNombre();
			assertEquals(nombreActividad, nombreActObtenida);

			var salidasAsociadasObtenidas = dtCasteado.getActividades().get(0).getSalidas().keySet().toArray();
			assertEquals(1, salidasAsociadasObtenidas.length);
			assertEquals(nombreSalida, salidasAsociadasObtenidas[0]);
		}

		// Verifico para los turistas
		for (int i = 0; i < 50; i++) {
			String nickname = "Turista " + id + " i=" + i;
			String nombre = "NOMBRE TURISTA";
			String apellido = "APELLIDO TURISTA";
			String correo = "TURISTA " + id + " i=" + i;
			String nacionalidad = "CHINA";
			LocalDate FNacimiento = localDateNow.minusYears(15);
			String nombreSalida = "Salida " + id + " i=" + i;

			var dtdet = cu.obtenerDTUsuarioDetalle(nickname);

			assertThrows(ClassCastException.class, () -> {
				var dtErrorCasteado = (DTProveedorDetalle) dtdet;
			});

			var dtCasteado = (DTTuristaDetalle) dtdet;

			assertEquals(nickname, dtCasteado.getNickname());
			assertEquals(nombre, dtCasteado.getNombre());
			assertEquals(apellido, dtCasteado.getApellido());
			assertEquals(correo, dtCasteado.getCorreo());
			assertEquals(nacionalidad, dtCasteado.getNacionalidad());
			assertEquals(FNacimiento, dtCasteado.getFechaNac());

			if (i < 40) {
				assertEquals(1, dtCasteado.getInscripciones().size());
				var nombreSalObtenida = dtCasteado.getInscripciones().get(0);
				assertEquals(nombreSalida, nombreSalObtenida);
			} else {
				assertEquals(40, dtCasteado.getInscripciones().size());
			}

		}
	}

	@Test
	void testObtenerDTUsuario() {
		String id = "testObtenerDTUsuario";

		try {
			generarProveedores(50, id);
			generarTuristas(50, id);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		// Verifico para los proveedores
		for (int i = 0; i < 50; i++) {
			String nickname = "Proveedor " + id + " i=" + i;
			String nombre = "NOMBRE PROV";
			String apellido = "APELLIDO PROV";
			String correo = "Proveedor " + id + " i=" + i;
			String descripcion = "HOLA! DESCRIPCION";
			String link = "www.google.com.provedoor";
			LocalDate FNacimiento = localDateNow.minusYears(30);

			var dt = cu.obtenerDTUsuario(nickname);

			assertThrows(ClassCastException.class, () -> {
				var dtErrorCasteado = (DTTurista) dt;
			});

			var dtCasteado = (DTProveedor) dt;

			assertEquals(nickname, dtCasteado.getNickname());
			assertEquals(nombre, dtCasteado.getNombre());
			assertEquals(apellido, dtCasteado.getApellido());
			assertEquals(correo, dtCasteado.getCorreo());
			assertEquals(descripcion, dtCasteado.getDescrpicionGeneral());
			assertEquals(link, dtCasteado.getLink());
			assertEquals(FNacimiento, dtCasteado.getFechaNac());
		}

		// Verifico para los turistas
		for (int i = 0; i < 50; i++) {
			String nickname = "Turista " + id + " i=" + i;
			String nombre = "NOMBRE TURISTA";
			String apellido = "APELLIDO TURISTA";
			String correo = "TURISTA " + id + " i=" + i;
			String nacionalidad = "CHINA";
			LocalDate FNacimiento = localDateNow.minusYears(15);

			var dt = cu.obtenerDTUsuario(nickname);

			assertThrows(ClassCastException.class, () -> {
				var dtErrorCasteado = (DTProveedor) dt;
			});

			var dtCasteado = (DTTurista) dt;

			assertEquals(nickname, dtCasteado.getNickname());
			assertEquals(nombre, dtCasteado.getNombre());
			assertEquals(apellido, dtCasteado.getApellido());
			assertEquals(correo, dtCasteado.getCorreo());
			assertEquals(nacionalidad, dtCasteado.getNacionalidad());
			assertEquals(FNacimiento, dtCasteado.getFechaNac());
		}

		// Caso en el que no se cumplen las pre-condiciones
		assertThrows(Exception.class, () -> {
			cu.obtenerDTUsuario("NICK QUE OBVIAMENTE NO ESTA EN EL SISTEMA");
		});
	}

	@Test
	void testModificarUsuarioCaso() {
		String id = "testModificarUsuario";

		try {
			generarProveedores(50, id);
			generarTuristas(50, id);
		} catch (TurismoUyException e) {
			fail(e.getMessage());
		}

		for (int i = 0; i < 50; i++) {
			String nicknameTur = "Turista " + id + " i=" + i;
			String nicknameProv = "Proveedor " + id + " i=" + i;

			DTTurista dttur = (DTTurista) cu.obtenerDTUsuario(nicknameTur);
			DTProveedor dtprov = (DTProveedor) cu.obtenerDTUsuario(nicknameProv);

			// Si no modifico nada:
			try {
				cu.modificarUsuario(dttur);
				cu.modificarUsuario(dtprov);
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}

			assertEquals(dttur, (DTTurista) cu.obtenerDTUsuario(nicknameTur));
			assertEquals(dtprov, (DTProveedor) cu.obtenerDTUsuario(nicknameProv));

			// Caso típico:
			String nuevoString = "NUEVO DATO";

			DTTurista dtTurNuevo = new DTTurista(dttur.getNickname(), dttur.getNombre() + nuevoString,
					dttur.getApellido() + nuevoString, dttur.getCorreo(), dttur.getFechaNac().plusDays(1), null,
					dttur.getNacionalidad() + nuevoString);
			DTProveedor dtProvNuevo = new DTProveedor(dtprov.getNickname(), dtprov.getNombre() + nuevoString,
					dtprov.getApellido() + nuevoString, dtprov.getCorreo(), dtprov.getFechaNac().plusDays(1), null,
					dtprov.getDescrpicionGeneral() + nuevoString, dtprov.getLink() + nuevoString);

			try {
				cu.modificarUsuario(dtTurNuevo);
				cu.modificarUsuario(dtProvNuevo);
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}

			dttur = (DTTurista) cu.obtenerDTUsuario(dtTurNuevo.getNickname());
			dtprov = (DTProveedor) cu.obtenerDTUsuario(dtProvNuevo.getNickname());
			assertEquals(dtTurNuevo, dttur);
			assertEquals(dtProvNuevo, dtprov);

			// Caso en el que se modifica el correo
			DTTurista dtTurNuevo2 = new DTTurista(dttur.getNickname(), dttur.getNombre(), dttur.getApellido(),
					dttur.getCorreo() + nuevoString, dttur.getFechaNac(), null, dttur.getNacionalidad());
			DTProveedor dtProvNuevo2 = new DTProveedor(dtprov.getNickname(), dtprov.getNombre(), dtprov.getApellido(),
					dtprov.getCorreo() + nuevoString, dtprov.getFechaNac(), null, dtprov.getDescrpicionGeneral(),
					dtprov.getLink());

			assertThrows(ModificacionUsuarioNoPermitida.class, () -> {
				cu.modificarUsuario(dtTurNuevo2);
			});

			assertThrows(ModificacionUsuarioNoPermitida.class, () -> {
				cu.modificarUsuario(dtProvNuevo2);
			});

			try {
				cu.modificarUsuario(dtTurNuevo);
				cu.modificarUsuario(dtProvNuevo);
			} catch (TurismoUyException e) {
				fail(e.getMessage());
			}

			dttur = (DTTurista) cu.obtenerDTUsuario(dtTurNuevo.getNickname());
			dtprov = (DTProveedor) cu.obtenerDTUsuario(dtProvNuevo.getNickname());
			assertNotEquals(dtTurNuevo2, dttur);
			assertNotEquals(dtProvNuevo2, dtprov);

			// Caso en el que se modifica el nick:
			DTTurista dtTurNuevo3 = new DTTurista(dttur.getNickname() + nuevoString, dttur.getNombre(),
					dttur.getApellido(), dttur.getCorreo(), dttur.getFechaNac(), null, dttur.getNacionalidad());
			DTProveedor dtProvNuevo3 = new DTProveedor(dtprov.getNickname() + nuevoString, dtprov.getNombre(),
					dtprov.getApellido(), dtprov.getCorreo(), dtprov.getFechaNac(), null,
					dtprov.getDescrpicionGeneral(), dtprov.getLink());

			assertThrows(ModificacionUsuarioNoPermitida.class, () -> {
				cu.modificarUsuario(dtTurNuevo3);
			});

			assertThrows(ModificacionUsuarioNoPermitida.class, () -> {
				cu.modificarUsuario(dtProvNuevo3);
			});

			dttur = (DTTurista) cu.obtenerDTUsuario(dttur.getNickname());
			dtprov = (DTProveedor) cu.obtenerDTUsuario(dtprov.getNickname());
			assertNotEquals(dtTurNuevo3, dttur);
			assertNotEquals(dtProvNuevo3, dtprov);

			// Todo falta verificar el cambio de nickname o correo
		}
	}

}
