package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import logica.controladores.IControladorUsuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.SalidaYaRegistradaException;
import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;

class ControladorActividadTuristicaTest {
	private static IControladorActividadTuristica cat;
	private static IControladorUsuario cu;
	
	@BeforeAll
	static void preparacionPrevia() {
		cat = Fabrica.getInstancia().getIControladorActividadTuristica();
		cu = Fabrica.getInstancia().getIControladorUsuario();
	}
	
	// No es un test en sí
	static void generarActividades(int cant, String id) throws Exception {
		preparacionPrevia();
		assertTrue(cat != null);
		
		for (int i = 0; i < cant; i++) {
			String nickProveedor = "Proveedor " + id + " i=" + i;
			String departamento = "Departamento " + id + " i=" + i;
			String nombreActividad = "Actividad " + id + " i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10.85;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = LocalDate.now();
			
			cat.altaActividadTuristica(nickProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);	
		}
	}
	
	// No es un test en sí
	static void generarDepartamentos(int cant, String id) throws Exception {
		preparacionPrevia();
		assertTrue(cat != null);
		
		for (int i = 0; i < cant; i++) {
			String nom = "Departamento " + id + " i=" + i;
			String descr = "Descripcion";
			String url = "https://www.canelones-departamento.org.uy/inicio.html";
			
			cat.altaDepartamento(nom, descr, url);
		}
	}

	@Test
	public void testAltaDepartamentoOK() {
		try {
			generarDepartamentos(100, "testAltaDepartamentoOK");			
		} catch(Exception e) {
			fail(e.getMessage());
		}
    }
	
	@Test
	public void testAltaDepartamentoRepetido() {
		assertTrue(cat != null);
		
		try {
			generarDepartamentos(1, "testAltaDepartamentoRepetido");			
		} catch(Exception e) {
			fail(e.getMessage());
		}
		
		// Repito y debería tirar la excepcion
		assertThrows(DeparamentoYaRegistradoException.class, ()->{
			generarDepartamentos(1, "testAltaDepartamentoRepetido");
		});
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
			
			//assertTrue(cat.existeActividadTuristica(nombreActividad));
			assertTrue(cat.obtenerIdActividadesTuristicas(departamento).contains(nombreActividad));

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
			
			//assertTrue(cat.existeActividadTuristica(nombreActividad));
			assertTrue(cat.obtenerIdActividadesTuristicas(departamento).contains(nombreActividad));

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
	
	@Test
	public void testAltaSalidaTuristicaOK() {
		assertTrue(cat != null);
		
		for (int i = 0; i < 100; i++) {
			
			String nombreProveedor = "Proveedor testAltaSalidaTuristicaOK i=" + (i % 10);
			String departamento = "Departamento testAltaSalidaTuristicaOK i=" + (i % 10);
			String nombreActividad = "Actividad testAltaSalidaTuristicaOK i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = LocalDate.now();
			
	
			String nombreSalida = "Salida testAltaSalidaTuristicaOK i=" + i;
			LocalDate f = LocalDate.now();
			LocalDateTime fechaHoraSalida = LocalDateTime.now().plusMonths(1);
			LocalDate fechaAltaSalida = f;
			String lugar = "lugar";
			int cantMaxTuristas = 10;
			
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
			
			try {
				cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar, cantMaxTuristas);
			}catch(SalidaYaRegistradaException e) {
				fail(e.getMessage());
			} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException e) {
				fail(e.getMessage());
			} catch (FechaAltaSalidaPosteriorAFechaSalidaException e) {
				fail(e.getMessage());
			}			
			
			//assertTrue(cat.existeSalidaTuristica(nombreSalida));
						
			DTSalidaTuristica sal = cat.obtenerDTSalidaTuristica(nombreSalida);
			assertTrue(sal != null);
			
			assertEquals(nombreSalida, sal.getNombre());
			assertEquals(fechaHoraSalida, sal.getFechaHoraSalida());
			assertEquals(fechaAlta, sal.getFechaAlta());
			assertEquals(lugar, sal.getLugarSalida());
			assertEquals(cantMaxTuristas, sal.getCantMaxTuristas());
			
			DTSalidaTuristicaDetalle salDetalle = cat.obtenerDTSalidaTuristicaDetalle(nombreSalida);
			assertTrue(salDetalle.getInscriptos().isEmpty());
		}
	}

}
