package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.DeparamentoYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.entidades.Departamento;
import logica.manejadores.ManejadorDepartamento;

class ControladorActividadTuristicaTest {
	private static IControladorActividadTuristica cat;
	
	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstancia();
		cat = fabrica.getIControladorActividadTuristica();
	}

	@Test
	public void testAltaDepartamentoOK() {
		assertTrue(cat != null);
		
		String nom = "Canelones";
		String descr = "Departamento al este de montevideo. Tiene costa.";
		String url = "https://www.canelones-departamento.org.uy/inicio.html";
		
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
	
	@Test
	public void testAltaDepartamentoRepetido() {
		assertTrue(cat != null);
		
		String nom = "Montevideo";
		String descr = "Departamento al oeste de canelones. Tiene costa y ramblas lindas.";
		String url = "https://www.motevideo-departamento.org.uy/inicio.html";
		
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

	@Test
	public void testObtenerIdDepartamentos(){
		assertTrue(cat != null);
		
		String nom1 = "Rocha";
		String descr1= "Departamento de playas";
		String url1 = "";
		
		String nom2 = "Maldonado";
		String descr2 = "Departamento para Argentinos y Brasileros";
		String url2 = "No tiene";
		
		try {
			cat.altaDepartamento(nom1, descr1, url1);
		} catch (Exception e) {
			fail(e.getMessage());
		};
		
		assertThrows(DeparamentoYaRegistradoException.class, ()->{
			cat.altaDepartamento(nom1, descr1, url1);
		});	
		
		
		try {
			cat.altaDepartamento(nom2, descr2, url2);
		} catch (Exception e) {
			fail(e.getMessage());
		};
		

		assertThrows(DeparamentoYaRegistradoException.class, ()->{
			cat.altaDepartamento(nom2, descr2, url2);
		});	
		
		
		var ids = cat.obtenerIdDepartamentos();
		
		// Ambos departamentos deberían estar
		assertTrue(ids.contains(nom1));
		assertTrue(ids.contains(nom2));
		
		// Ambos departamentos deberían estar una única vez
		assertTrue(ids.remove(nom1));
		assertTrue(ids.remove(nom2));
		
		assertFalse(ids.remove(nom1));
		assertFalse(ids.remove(nom2));	
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
