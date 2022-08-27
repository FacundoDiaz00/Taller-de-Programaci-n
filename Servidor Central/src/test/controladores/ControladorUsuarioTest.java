package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
		
		var ids = cu.obtenerIdUsuarios();
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
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	void testAltaTuristaRepetido() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAltaProveedorOK() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAltaProveedorRepetido() {
		fail("Not yet implemented"); // TODO
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
