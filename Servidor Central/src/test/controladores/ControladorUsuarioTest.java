package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	void testObtenerIdUsuarios() {
		assertTrue(cu != null);
		
	}

	@Test
	void testObtenerIdProveedores() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testObtenerIdTuristas() {
		fail("Not yet implemented"); // TODO
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
