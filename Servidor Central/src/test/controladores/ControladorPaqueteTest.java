package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import logica.controladores.IControladorPaquete;

class ControladorPaqueteTest {
	private static IControladorPaquete cp;

	@BeforeAll
	static void preparacionPrevia() {
		cp = Fabrica.getInstancia().getIControladorPaquete();
	}

	@Test
	final void testAltaPaqueteOK() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testAltaPaqueteRepetido() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testObtenerDetallesPaquetes() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testObtenerIdPaquetes() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testAgregarActividadAPaqueteOK() {
		fail("Not yet implemented"); // TODO
	}
	
	@Test
	final void testAgregarActividadAPaqueteRepetida() {
		fail("Not yet implemented"); // TODO
	}

}
