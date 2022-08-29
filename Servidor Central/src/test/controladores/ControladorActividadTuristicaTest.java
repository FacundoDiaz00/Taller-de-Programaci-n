package test.controladores;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import excepciones.*;
import logica.controladores.IControladorUsuario;
import logica.controladores.IControladorPaquete;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;

class ControladorActividadTuristicaTest {
	private static IControladorActividadTuristica cat;
	private static IControladorUsuario cu;
	private static IControladorPaquete cp;

	private static LocalDate nowLocalDate;
	private static LocalDateTime nowLocalDateTime;
	
	@BeforeAll
	static void preparacionPrevia() {
		cat = Fabrica.getInstancia().getIControladorActividadTuristica();
		cu = Fabrica.getInstancia().getIControladorUsuario();
		cp = Fabrica.getInstancia().getIControladorPaquete();
		
		nowLocalDate = LocalDate.now();
		nowLocalDateTime = LocalDateTime.now();
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
			LocalDate fechaAlta = nowLocalDate;
			
			cat.altaActividadTuristica(nickProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);	
		}
	}
	
	// No es un test en sí
	static void generarSalidas(int cant, String id) throws Exception {
		preparacionPrevia();
		assertTrue(cat != null);
		
		for (int i = 0; i < cant; i++) {
			String nombreActividad = "Actividad " + id + " i=" + i;
			String nombreSalida = "Salida " + id + " i=" + i;
			String ciudad = "Ciudad salida";
			LocalDate fechaAlta = nowLocalDate;
			LocalDateTime fechaYHoraSalida = nowLocalDateTime.plusDays(40);
			int cantMaxTur = 10;
			
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaYHoraSalida, fechaAlta, ciudad, cantMaxTur);
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
		String descripcion = "Desc";

		//paquetes:
		String nombre = "Paquete";
		int periodovalidez = 15;
		float descuento = (float) (1);
		try {
			cp.altaPaquete(nombre, descripcion, periodovalidez, descuento, LocalDate.of(2022,1,1));
		}catch (Exception e){
			fail(e.getMessage());
		}

		for (int i = 0; i < 100; i++) {
			String nombreProveedor = "Proveedor testObtenerDetallesActividadTuristica i=" + (i % 10);
			String departamento = "Departamento testObtenerDetallesActividadTuristica i=" + (i % 10);
			String nombreActividad = "Actividad testObtenerDetallesActividadTuristica i=" + i;
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
			try {
				cp.agregarActividadAPaquete(nombreActividad,"Paquete");
			}catch (Exception e){
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
			assertFalse(act.getPaquetes().isEmpty());

			assertTrue(act.getSalidas().isEmpty());
			assertTrue(act.getPaquetes().containsKey("Paquete"));

			try {
				cat.altaDepartamento(departamento, descripcion, departamento);
			} catch (DeparamentoYaRegistradoException e) {
				// Esperable, no pasa nada.
			} catch(Exception e) {
				fail(e.getMessage());
			}


		}
    }


	@Test
	public void testObtenerDTSalidasTuristicas() {
		String id = "testObtenerDTSalidasTuristicas";
		
		try {
			ControladorUsuarioTest.generarProveedores(100, id);
			ControladorActividadTuristicaTest.generarDepartamentos(100, id);
			generarActividades(100, id);
			generarSalidas(100, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 100; i++) {
			String nombreActividad = "Actividad " + id + " i=" + i;
			String nombreSalida = "Salida " + id + " i=" + i;
			String ciudad = "Ciudad salida";
			LocalDate fechaAlta = nowLocalDate;
			LocalDateTime fechaYHoraSalida = nowLocalDateTime.plusDays(40);
			int cantMaxTur = 10;
			
			
			var salidas = cat.obtenerDTSalidasTuristicas(nombreActividad);
			
			assertEquals(1, salidas.size());
			
			for (var sal: salidas) {
				assertEquals(nombreSalida, sal.getNombre());
				assertEquals(ciudad, sal.getLugarSalida());
				assertEquals(fechaAlta, sal.getFechaAlta());
				assertEquals(fechaYHoraSalida, sal.getFechaHoraSalida());
				assertEquals(cantMaxTur, sal.getCantMaxTuristas());
			}
		}
	}

	@Test
	public void testAltaInscripcionSalidaTuristicaOK() {
		}
	
	@Test
	public void testAltaInscripcionSalidaTuristicaRepetida() {


		String nickname = "testAltaInscripcionSalidaTuristicaRepetida Turista nickname";
		String nombre = "testAltaInscripcionSalidaTuristicaRepetida NOMBRE TURISTA";
		String apellido = "testAltaInscripcionSalidaTuristicaRepetida APELLIDO TURISTA";
		String correo = "testAltaInscripcionSalidaTuristicaRepetida TURISTA correo";
		String nacionalidad = "CHINA";
		LocalDate FNacimiento = LocalDate.now();

		String nombreProveedor = "testAltaInscripcionSalidaTuristicaRepetida prov";
		String departamento = "testAltaInscripcionSalidaTuristicaRepetida deptoTest";
		String nombreActividad = "testAltaInscripcionSalidaTuristicaRepetida actividad";
		String descripcion = "testAltaInscripcionSalidaTuristicaRepetida Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = LocalDate.now();
		String nombreSalida = "testAltaInscripcionSalidaTuristicaRepetida salida";
		LocalDate f = LocalDate.now();
		LocalDateTime fechaHoraSalida = LocalDateTime.now().plusMonths(1);
		LocalDate fechaAltaSalida = f;
		String lugar = "lugar";
		int cantMaxTuristas = 10;

		try {
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, nacionalidad);
			cat.altaDepartamento(departamento, descripcion, departamento);
			cu.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta);
			cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar, cantMaxTuristas);
			cat.altaInscripcionSalidaTuristica(nombreSalida,nickname,1,LocalDate.now().plusYears(5));
		}catch (Exception e){
			fail(e.getMessage());
		}

		assertThrows(InscripcionYaRegistradaException.class, () -> {
			cat.altaInscripcionSalidaTuristica(nombreSalida,nickname,2,LocalDate.now().plusYears(5));
		});
	}


	@Test
	public void testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada() {


		String nickname = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada Turista nickname";
		String nombre = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada NOMBRE TURISTA";
		String apellido = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada APELLIDO TURISTA";
		String correo = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada TURISTA correo";
		String nacionalidad = "CHINA";
		LocalDate FNacimiento = LocalDate.now();

		String nombreProveedor = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada prov";
		String departamento = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada deptoTest";
		String nombreActividad = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada actividad";
		String descripcion = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = LocalDate.now();
		String nombreSalida = "testAltaInscripcionSalidaTuristicaInscripcionConCapacidadSuperada salida";
		LocalDate f = LocalDate.now();
		LocalDateTime fechaHoraSalida = LocalDateTime.now().plusMonths(1);
		LocalDate fechaAltaSalida = f;
		String lugar = "lugar";
		int cantMaxTuristas = 3;

		try {
			cu.altaTurista(nickname, nombre, apellido, correo, FNacimiento, nacionalidad);
			cu.altaTurista(nickname + "2", nombre, apellido, correo + 2, FNacimiento, nacionalidad);
			cat.altaDepartamento(departamento, descripcion, departamento);
			cu.altaProveedor(nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, nombreProveedor, fechaAlta);
			cat.altaActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar, cantMaxTuristas);
			cat.altaInscripcionSalidaTuristica(nombreSalida,nickname,2,LocalDate.now().plusYears(5));
		}catch (Exception e){
			fail(e.getMessage());
		}

		assertThrows(SuperaElMaximoDeTuristasException.class , ()->{
			cat.altaInscripcionSalidaTuristica(nombreSalida,nickname + "2",2,LocalDate.now().plusYears(5));
		});

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
			
	
			String nombreSalida = "Salida testAltaSalidaTuristicaOK" + i;
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
	
	@Test
	public void testAltaSalidaTuristicaRepetida() {
		assertTrue(cat != null);

		for (int i = 0; i < 2; i++) {

			String nombreProveedor = "Proveedor testAltaSalidaTuristicaRepetida i=" + (i % 10);
			String departamento = "Departamento testAltaSalidaTuristicaRepetida i=" + (i % 10);
			String nombreActividad = "Actividad testAltaSalidaTuristicaRepetida i=" + i;
			String descripcion = "Desc";
			int duracion = 10;
			float costo = (float) 10;
			String ciudad = "Ciudad";
			LocalDate fechaAlta = LocalDate.now();


			String nombreSalida = "Salida testAltaSalidaTuristicaRepetida";
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
			if(i == 1){
				assertThrows(SalidaYaRegistradaException.class, ()->{
					cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar, cantMaxTuristas);
				});
			} else {
				try {
					cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar, cantMaxTuristas);
				}catch(SalidaYaRegistradaException e) {
					fail(e.getMessage());
				} catch (FechaAltaActividadPosteriorAFechaAltaSalidaException e) {
					fail(e.getMessage());
				} catch (FechaAltaSalidaPosteriorAFechaSalidaException e) {
					fail(e.getMessage());
				}
			}

		}
	}

	@Test
	public void testAltaSalidaTuristicaFewchaAltaActividadPosteriorASalida() {
		assertTrue(cat != null);
		String nombreProveedor = "Proveedor testAltaSalidaTuristicaAltaPosteriorASalida";
		String departamento = "Departamento testAltaSalidaTuristicaAltaPosteriorASalida";
		String nombreActividad = "Actividad testAltaSalidaTuristicaAltaPosteriorASalida";
		String descripcion = "Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = LocalDate.now();


		String nombreSalida = "Salida testAltaSalidaTuristicaAltaPosteriorASalida";
		LocalDate f = LocalDate.now();
		LocalDateTime fechaHoraSalida = LocalDateTime.now().minusMonths(1);
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

		assertThrows(FechaAltaSalidaPosteriorAFechaSalidaException.class, ()->{
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar, cantMaxTuristas);
		});
	}

	@Test
	public void testAltaSalidaTuristicaFechaAltaActividadPosteriorAFechaAltaSalidaException() {
		assertTrue(cat != null);
		String nombreProveedor = "Proveedor testAltaSalidaTuristicaFechaAltaSalidaPosteriorAFechaSalida";
		String departamento = "Departamento testAltaSalidaTuristicaFechaAltaSalidaPosteriorAFechaSalida";
		String nombreActividad = "Actividad testAltaSalidaTuristicaFechaAltaSalidaPosteriorAFechaSalida";
		String descripcion = "Desc";
		int duracion = 10;
		float costo = (float) 10;
		String ciudad = "Ciudad";
		LocalDate fechaAlta = LocalDate.now().plusMonths(1);


		String nombreSalida = "Salida testAltaSalidaTuristicaFechaAltaSalidaPosteriorAFechaSalida";
		LocalDateTime fechaHoraSalida = LocalDateTime.now();
		LocalDate fechaAltaSalida = fechaHoraSalida.toLocalDate();
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

		assertThrows(FechaAltaActividadPosteriorAFechaAltaSalidaException.class, ()->{
			cat.altaSalidaTuristica(nombreActividad, nombreSalida, fechaHoraSalida, fechaAltaSalida, lugar, cantMaxTuristas);
		});
	}

}
