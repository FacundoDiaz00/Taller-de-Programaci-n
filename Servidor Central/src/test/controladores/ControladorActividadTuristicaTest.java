package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.DeparamentoYaRegistradoException;
import excepciones.PaqueteYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.entidades.Departamento;
import logica.manejadores.ManejadorDepartamento;

class ControladorActividadTuristicaTest {
	private static IControladorActividadTuristica cat;
	
	@BeforeAll
	static void preparacionPrevia() {
		cat = Fabrica.getInstancia().getIControladorActividadTuristica();
	}


	@Test
	public void testAltaDepartamentoOK() {
		assertTrue(cat != null);
		
		for (int i = 0; i < 100; i++) {
			
			String nom = "Dep testAltaDepartamentoOK" + i;
			String descr = "Departamento " + i;
			String url = "https://www.canelones-departamento.org.uy/inicio.html" + i;
			
			try {
				cat.altaDepartamento(nom, descr, url);			
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			ManejadorDepartamento md = ManejadorDepartamento.getInstancia();        
			assertTrue(md != null);
			
			assertTrue(md.exists(nom));
			
			Departamento dep = md.getDepartamento(nom);
			assertTrue(dep != null);
			
			assertEquals(nom, dep.getNombre());
			assertEquals(descr, dep.getDescripcion());
			assertEquals(url, dep.getUrl());    
			
			var ids = cat.obtenerIdDepartamentos();
			assertTrue(ids.contains(nom));
		}
		
    }
	
	@Test
	public void testAltaDepartamentoRepetido() {
		assertTrue(cat != null);
		
		for (int i = 0; i < 100; i++) {
			String nom = "Dep testAltaDepartamentoRepetido" + i;
			String descr = "Departamento " + i;
			String url = "https://www.canelones-departamento.org.uy/inicio.html" + i;
			try {
				cat.altaDepartamento(nom, descr, url);
			} catch (Exception e) {
				fail(e.getMessage());
			};
			
			// Repito y debería tirar la excepcion
			assertThrows(DeparamentoYaRegistradoException.class, ()->{
				cat.altaDepartamento(nom, descr, url);
			});	
			
			
			var ids = cat.obtenerIdDepartamentos();
			assertTrue(ids.contains(nom));
		}
    }

	@Test
	public void testObtenerIdDepartamentos(){
		assertTrue(cat != null);
		
		
		for (int i = 0; i < 100; i++) {
			String nom = "Dep testObtenerIdDepartamentos" + i;
			String descr = "Departamento " + i;
			String url = "https://www.canelones-departamento.org.uy/inicio.html" + i;
			
			try {
				cat.altaDepartamento(nom, descr, url);
			} catch (Exception e) {
				fail(e.getMessage());
			};			
			
			var ids_loop = cat.obtenerIdDepartamentos();
						
			// Ambos departamentos deberían estar una única vez
			assertTrue(ids_loop.remove(nom));
			
			assertFalse(ids_loop.remove(nom));
		}
		
		var ids = cat.obtenerIdDepartamentos();
		for (int i = 0; i < 100; i++)  {
			String nom = "Dep testObtenerIdDepartamentos" + i;
			// Los paquetes deberían estar una única vez
			assertTrue(ids.remove(nom));
			assertFalse(ids.remove(nom));
		}
	}
	
	@Test
	public void testAltaActividadTuristicaOK() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testAltaActividadTuristicaRepetida() {
		fail("Not yet implemented");
	}
	
	public void teasExisteActividadTuristica() {
		fail("Not yet implemented");
	}
    
	@Test
    public void testObtenerIdActividadesTuristicas(){
		fail("Not yet implemented");
    }
    

	@Test
    public void testObtenerDetallesActividadTuristica() {
		fail("Not yet implemented");
    }

	@Test
	public void testObtenerDTSalidasTuristicas() {
		fail("Not yet implemented");
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaOK() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testAltaInscripcionSalidaTuristicaRepetida() {
		fail("Not yet implemented");
	}

}
