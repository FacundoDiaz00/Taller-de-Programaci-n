package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;

class ControladorUsuarioTest {
	private static IControladorUsuario cu;
	
	@BeforeAll
	static void preparacionPrevia() {
		cu = Fabrica.getInstancia().getIControladorUsuario();
	}
	
	// No es un test en sí
	static void generarProveedores(int cant, String id) throws Exception {
		preparacionPrevia();
		assertTrue(cu != null);
		
		for (int i = 0; i < cant; i++) {
			String nickname = "Proveedor " + id + " i=" + i;
			String nombre = "NOMBRE PROV";
			String apellido = "APELLIDO PROV";
			String correo = "Proveedor " + id + " i=" + i;
			String descripcion = "HOLA! DESCRIPCION";
			String link = "www.google.com.provedoor";
			LocalDate FNacimiento = LocalDate.now().minusYears(30);
			
			cu.altaProveedor(nickname, nombre, apellido, correo, descripcion, link, FNacimiento);
		}
	}
	
	// No es un test en sí
	static void generarTuristas(int cant, String id) throws Exception {
		preparacionPrevia();
		assertTrue(cu != null);
		
		for (int i = 0; i < cant; i++) {
			String nickname = "Turista " + id + " i=" + i;
			String nombre = "NOMBRE TURISTA";
			String apellido = "APELLIDO TURISTA";
			String correo = "TURISTA " + id + " i=" + i;
			String nacionalidad = "CHINA";
			LocalDate FNacimiento = LocalDate.now().minusYears(15);
			
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, nacionalidad);
		}
	}

	@Test
	void testObtenerIdUsuarios() {
		assertTrue(cu != null);
		
		String id = "testObtenerIdUsuarios";
		
		try {
			generarProveedores(50, id);
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		try {
			generarTuristas(50, id);
		} catch (Exception e) {
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
		
		assertEquals(tamanioAntes - 100 ,ids.size());
	}

	@Test
	void testObtenerIdProveedores() {
		assertTrue(cu != null);
		
		String id = "testObtenerIdProveedores";
		
		try {
			generarProveedores(100, id);
		} catch (Exception e) {
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
		
		assertEquals(tamanioAntes - 100 ,ids.size());
	}

	@Test
	void testObtenerIdTuristas() {
		assertTrue(cu != null);
		
		String id = "testObtenerIdTuristas";
		
		
		try {
			generarTuristas(100, id);
		} catch (Exception e) {
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
		
		assertEquals(tamanioAntes - 100 ,ids.size());
	}

	@Test
	void testAltaTuristaOK() {
		try {
			generarTuristas(100, "testAltaTuristaOK");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void testAltaTuristaRepetido() {
		String id = "testAltaTuristaRepetido";
		
		try {
			generarTuristas(50, id);
		} catch (Exception e) {
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
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, nacionalidad);
		});		
	}

	@Test
	void testAltaProveedorOK() {
		try {
			generarProveedores(100, "testAltaProveedorOK");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	void testAltaProveedorRepetido() {
		String id = "testAltaProveedorRepetido";
		
		try {
			generarProveedores(50, id);
		} catch (Exception e) {
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
			cu.altaProveedor(nickname, nombre, apellido, correo, descripcion, link, FNacimiento);
		});		
	}
	
	@Test
	void testObtenerDTUsuarioDetalle() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testObtenerDTUsuario() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	void testModificarUsuario() {
		fail("Not yet implemented"); // TODO
	}

}
