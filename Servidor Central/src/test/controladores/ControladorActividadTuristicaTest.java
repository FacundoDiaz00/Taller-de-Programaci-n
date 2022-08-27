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
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;
import logica.manejadores.ManejadorActividadTuristica;
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
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";
			
			try {
				cat.altaDepartamento(nom, descr, url);			
			} catch(Exception e) {
				fail(e.getMessage());
			}
		}
		
    }
	
	@Test
	public void testAltaDepartamentoRepetido() {
		assertTrue(cat != null);
		
		for (int i = 0; i < 100; i++) {
			String nom = "Dep testAltaDepartamentoRepetido" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";
			try {
				cat.altaDepartamento(nom, descr, url);
			} catch (Exception e) {
				fail(e.getMessage());
			};
			
			// Repito y debería tirar la excepcion
			assertThrows(DeparamentoYaRegistradoException.class, ()->{
				cat.altaDepartamento(nom, descr, url);
			});
		}
    }

	@Test
	public void testObtenerIdDepartamentos(){
		assertTrue(cat != null);
		
		
		for (int i = 0; i < 100; i++) {
			String nom = "Dep testObtenerIdDepartamentos" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";
			
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
		assertTrue(cat != null);
		
		for (int i = 0; i < 100; i++) {
			
			String nombreProveedor = "Proveedor testAltaActividadTuristicaOK i=" + (i % 10);
			String departamento = "Departamento testAltaActividadTuristicaOK i=" + (i % 10);
			String nombreActividad = "Actividad testAltaActividadTuristicaOK i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = LocalDate.now();
			
			try {
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch (Exception e) {
				fail(e.getMessage());
			}
			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch (Exception e) {
				fail(e.getMessage());
			}
			
			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);	
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			assertTrue(cat.existeActividadTuristica(nombreActividad));
						
			DTActividadTuristicaDetalle act = cat.obtenerDetallesActividadTuristica(nombreActividad);
			assertTrue(act != null);
			
			assertEquals(nombreActividad, act.getNombre());
			assertEquals(descripcion, act.getDescripcion());
			assertEquals(duracion, act.getDuracion());
			assertEquals(costo, act.getCostoPorTurista());
			assertEquals(ciudad, act.getCuidad());
			assertEquals(fechaAlta, act.getFechaAlta());
			
			assertTrue(act.getPaquetes().isEmpty());
			assertTrue(act.getSalidas().isEmpty());
		}
	}
	
	@Test
	public void testAltaActividadTuristicaRepetida() {
		assertTrue(cat != null);
		
		for (int i = 0; i < 100; i++) {
			
			String nombreProveedor = "Proveedor testAltaActividadTuristicaRepetida i=" + (i % 10);
			String departamento = "Departamento testAltaActividadTuristicaRepetida i=" + (i % 10);
			String nombreActividad = "Actividad testAltaActividadTuristicaRepetida i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = LocalDate.now();
			
			try {
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch(Exception e) {
				fail(e.getMessage());
			}
			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);	
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			// Repito y debería tirar la excepcion
			assertThrows(ActividadTuristicaYaRegistradaException.class, ()->{
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);	
			});
			
			assertTrue(cat.existeActividadTuristica(nombreActividad));
			
			DTActividadTuristicaDetalle act = cat.obtenerDetallesActividadTuristica(nombreActividad);
			assertTrue(act != null);
			
			assertEquals(nombreActividad, act.getNombre());
			assertEquals(descripcion, act.getDescripcion());
			assertEquals(duracion, act.getDuracion());
			assertEquals(costo, act.getCostoPorTurista());
			assertEquals(ciudad, act.getCuidad());
			assertEquals(fechaAlta, act.getFechaAlta());
			
			assertTrue(act.getPaquetes().isEmpty());
			assertTrue(act.getSalidas().isEmpty());
		}
	}
	
	@Test
	public void testObtenerIdActividadesTuristicas(){
		assertTrue(cat != null);
		
		for (int i = 0; i < 100; i++) {
			String nombreProveedor = "Proveedor testObtenerIdActividadesTuristicas i=" + (i % 10);
			String departamento = "Departamento testObtenerIdActividadesTuristicas i=" + (i % 10);
			String nombreActividad = "Actividad testObtenerIdActividadesTuristicas i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = LocalDate.now();
			
			try {
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);	
			} catch(Exception e) {
				fail(e.getMessage());
			}		
			
			var ids_loop = cat.obtenerIdActividadesTuristicas(departamento);
						
			// Ambos departamentos deberían estar una única vez
			assertTrue(ids_loop.remove(nombreActividad));
			
			assertFalse(ids_loop.remove(nombreActividad));
		}
	}
		
	@Test
    public void testObtenerDetallesActividadTuristica() {
		assertTrue(cat != null);
		
		for (int i = 0; i < 100; i++) {
			String nombreProveedor = "Proveedor testObtenerDetallesActividadTuristica i=" + (i % 10);
			String departamento = "Departamento testObtenerDetallesActividadTuristica i=" + (i % 10);
			String nombreActividad = "Actividad testObtenerDetallesActividadTuristica i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = LocalDate.now();
			
			try {
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			try {
				Fabrica.getInstancia().getIControladorUsuario().altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta);
			} catch (UsuarioYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			try {
				cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);	
			} catch(Exception e) {
				fail(e.getMessage());
			}
			
			DTActividadTuristicaDetalle act = cat.obtenerDetallesActividadTuristica(nombreActividad);
			assertTrue(act != null);
			
			assertEquals(nombreActividad, act.getNombre());
			assertEquals(descripcion, act.getDescripcion());
			assertEquals(duracion, act.getDuracion());
			assertEquals(costo, act.getCostoPorTurista());
			assertEquals(ciudad, act.getCuidad());
			assertEquals(fechaAlta, act.getFechaAlta());
			
			
			// TODO agregar paquetes y que esto verifique que se devuelven
			assertTrue(act.getPaquetes().isEmpty());
			assertTrue(act.getSalidas().isEmpty());
		}
    }

	@Test
	public void teasExisteActividadTuristica() {
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
