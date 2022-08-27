package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.PaqueteYaRegistradoException;
import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTPaqueteDetalles;
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
			String descripcion = "Desc";
			int periodovalidez = 1*2;
			float descuento = (float) i;
			
			try {
				cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);			
			} catch(Exception e) {
				fail(e.getMessage());
			}
		}
		
	}

	@Test
	final void testAltaPaqueteRepetido() {
		assertTrue(cp != null);
		
		for (int i = 0; i < 100; i++) {
			String nombre = "Actividad testAltaPaqueteRepetido" + i; 
			String descripcion = "Desc";
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
		}	
	}

	@Test
	final void testObtenerIdPaquetes() {
		assertTrue(cp != null);
		
		String base = "Muchas Actividades testObtenerIdPaquetes i=";
		
		for (int i = 0; i < 100; i++) {
			String nombre = base + i; 
			String descripcion = "Todo lo que necesitas"; 
			int periodovalidez = i*2;
			float descuento = (float) i;
			
			try {
				cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);	
			} catch (Exception e) {
				fail(e.getMessage());
			};	
			
			var id_loop = cp.obtenerIdPaquetes();
			
			// Los paquetes deberían estar una única vez
			assertTrue(id_loop.remove(nombre));
			assertFalse(id_loop.remove(nombre));
		}
		
		var ids = cp.obtenerIdPaquetes();
		for (int i = 0; i < 100; i++)  {
			String nombre = base + i; 
			// Los paquetes deberían estar una única vez
			assertTrue(ids.remove(nombre));
			assertFalse(ids.remove(nombre));
		}
		
	}

	@Test
	final void testObtenerDetallesPaquetes() {
		assertTrue(cp != null);
		
		for (int i = 0; i < 100; i++) {
			String nombre = "Actividad testObtenerDetallesPaquetes" + i; 
			String descripcion = "Desc";
			int periodovalidez = 1*2;
			float descuento = (float) i;
			
			try {
				cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);			
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			var paq_list = cp.obtenerDetallesPaquetes();
			assertTrue(paq_list != null);
			
			boolean existe = false;			
			for (DTPaqueteDetalles paq : paq_list) {
				if (paq.getNombre().equals(nombre)) {
					existe = true;
					
					assertEquals(descripcion, paq.getDescrpicion());
					assertEquals(periodovalidez, paq.getValidez());
					// Uso esto por los posibles errores al comparar float y doubles
					assertTrue(Math.abs(descuento - paq.getDescuento()) < 1);
					
					// TODO: se debe agregar actividades y verificar que aparezcan dentro de paq.
				}
			}
			assertTrue(existe);
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
