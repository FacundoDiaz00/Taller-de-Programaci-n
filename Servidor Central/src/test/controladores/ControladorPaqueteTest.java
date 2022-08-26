package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.DeparamentoYaRegistradoException;
import excepciones.PaqueteYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorPaquete;
import logica.entidades.Departamento;
import logica.entidades.Paquete;
import logica.manejadores.ManejadorDepartamento;
import logica.manejadores.ManejadorPaquete;

class ControladorPaqueteTest {
	private static IControladorPaquete cp;

	@BeforeAll
	static void preparacionPrevia() {
		cp = Fabrica.getInstancia().getIControladorPaquete();
	}

	@Test
	final void testAltaPaqueteOK() {
		assertTrue(cp != null);
		
		for (int i = 0; i < 100; i++) {
			String nombre = "Actividad testAltaPaqueteOK" + i; 
			String descripcion = "Desc: " + i;
			int periodovalidez = 1*2;
			float descuento = (float) i;
			
			try {
				cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);			
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			ManejadorPaquete mp = ManejadorPaquete.getInstancia();        
			assertTrue(mp != null);
			
			assertTrue(mp.existePaquete(nombre));
			
			Paquete paq = mp.getPaquete(nombre);
			assertTrue(paq != null);
			
			assertEquals(nombre, paq.getNombre());
			assertEquals(descripcion, paq.getDescrpicion());
			assertEquals(periodovalidez, paq.getValidez());
			assertTrue(Math.abs(descuento - paq.getDescuento()) < 1);
			
			var ids = cp.obtenerIdPaquetes();
			assertTrue(ids.contains(nombre));			
		}
		
	}
	
	@Test
	final void testAltaPaqueteRepetido() {
		assertTrue(cp != null);
		
		for (int i = 0; i < 100; i++) {
			String nombre = "Actividad testAltaPaqueteRepetido" + i; 
			String descripcion = "Desc: " + i;
			int periodovalidez = 1*2;
			float descuento = (float) i;
			try {
				cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);			
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			assertThrows(PaqueteYaRegistradoException.class, ()->{
				cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);	
			});	
			
			
			var ids = cp.obtenerIdPaquetes();
			assertTrue(ids.contains(nombre));
		}
		
		
	}

	@Test
	final void testObtenerDetallesPaquetes() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testObtenerIdPaquetes() {
		assertTrue(cp != null);
		
		for (int i = 0; i < 100; i++) {
			String nombre = "Muchas Actividades i =" + i; 
			String descripcion = "Todo lo que necesitas i =" + i; 
			int periodovalidez = i*2;
			float descuento = (float) i;
			
			try {
				cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);	
			} catch (Exception e) {
				fail(e.getMessage());
			};
			
			assertThrows(PaqueteYaRegistradoException.class, ()->{
				cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);	
			});	
			
			
			var id_loop = cp.obtenerIdPaquetes();
			
			// Los paquetes deberían estar una única vez
			assertTrue(id_loop.remove(nombre));
			assertFalse(id_loop.remove(nombre));
		}
		
		var ids = cp.obtenerIdPaquetes();
		for (int i = 0; i < 100; i++)  {
			String nombre = "Muchas Actividades i =" + i; 
			// Los paquetes deberían estar una única vez
			assertTrue(ids.remove(nombre));
			assertFalse(ids.remove(nombre));
		}
		
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
