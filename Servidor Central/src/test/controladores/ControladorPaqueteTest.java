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
import logica.datatypes.DTActividadTuristica;
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

	// No es un test en sí.
	static void generarPaquetes(int cant, String id) throws Exception {
		preparacionPrevia();
		assertTrue(cp != null);
		
		for (int i = 0; i < cant; i++) {
			String nombre = "Paquete " + id + " i=" + i; 
			String descripcion = "Desc";
			int periodovalidez = 15;
			float descuento = (float) (i + 0.025);
			
			cp.altaPaquete(nombre, descripcion, periodovalidez, descuento);
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
		assertTrue(cp != null);
		
		try {
			generarPaquetes(100, "testObtenerIdPaquetes");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		
		var ids = cp.obtenerIdPaquetes();
		for (int i = 0; i < 100; i++)  {
			String nombre = "Paquete testObtenerIdPaquetes i=" + i; 
			// Los paquetes deberían estar una única vez
			assertTrue(ids.remove(nombre));
			assertFalse(ids.remove(nombre));
		}
		
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
			for  (int j = 0; j < 10; j++) {
				String nombreActString = "Actividad " + id + " i=" + (i*10 + j);
				cp.agregarActividadAPaquete(nombreActString, nombrePaq);
			}			
		}
		
		var paq_list = cp.obtenerDetallesPaquetes();
		assertTrue(paq_list != null);
		
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
					// Uso esto por los posibles errores al comparar float y doubles
					assertTrue(Math.abs(descuento - paq.getDescuento()) < 1);
					
					var act_list = paq.getActividades().values();
					for  (int j = 0; j < 10; j++) {
						String nombreActString = "Actividad " + id + " i=" + (i*10 + j);
						String nickProveedor = "Proveedor " + id + " i=" + (i*10 + j);
						String descripcionAct = "Desc";
						int duracion = 10;
						float costo = (float) 10.85;
						String ciudad = "Ciudad";
						
						boolean existeAct = false;
						for (DTActividadTuristica act : act_list) {
							if(act.getNombre().equals(nombreActString)) {
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
