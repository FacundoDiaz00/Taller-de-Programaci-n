package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.CompraYaRegistradaException;
import excepciones.PaqueteYaRegistradoException;
import excepciones.PaquetesSinActividadesExcepcion;
import excepciones.TurismoUyException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaqueteDetalles;
import logica.datatypes.DTTuristaDetallePrivado;
import logica.datatypes.EstadoActividadTuristica;

class ControladorPaqueteTest {
    private static IControladorPaquete contrPaquete;
    private static IControladorUsuario contrUsuario;
    private static IControladorActividadTuristica contrActividad;

    private static List<String> muestraCategorias;

    private static LocalDate localDateNow;
    private static LocalDate localDateVieja;

    @BeforeAll
    static void preparacionPrevia() {
        contrPaquete = Fabrica.getInstancia().getIControladorPaquete();
        contrUsuario = Fabrica.getInstancia().getIControladorUsuario();
        contrActividad = Fabrica.getInstancia().getIControladorActividadTuristica();

        localDateNow = LocalDate.now();
        localDateVieja = LocalDate.of(2022, 1, 1);
    }

    // No es un test en sí.
    static List<String> generarPaquetes(int cant, String idTest) throws TurismoUyException {
        if (contrPaquete == null)
            preparacionPrevia();

        assertTrue(contrPaquete != null);
        List<String> nombrePaquetes = new ArrayList<>();

        for (int i = 0; i < cant; i++) {
            String nombre = "Paquete " + idTest + " i=" + i;
            String descripcion = "Desc";
            int periodovalidez = 15;
            float descuento = (float) (i + 0.50);

            contrPaquete.altaPaquete(nombre, descripcion, periodovalidez, descuento, localDateVieja, null);
            nombrePaquetes.add(nombre);
        }
        return nombrePaquetes;
    }

    @Test
    final void testAltaPaqueteOK() throws TurismoUyException {
        generarPaquetes(100, "testAltaPaqueteOK");
    }

    @Test
    final void testAltaPaqueteRepetido() throws TurismoUyException {
        assertTrue(contrPaquete != null);

        generarPaquetes(1, "testAltaPaqueteRepetido");

        assertThrows(PaqueteYaRegistradoException.class, () -> {
            generarPaquetes(1, "testAltaPaqueteRepetido");
        });
    }

    @Test
    final void testObtenerIdPaquetes() throws TurismoUyException {
        String idTest = "testObtenerIdPaquetes";
        int cant = 100;

        generarPaquetes(cant, idTest);

        var ids = contrPaquete.obtenerIdPaquetes();

        assertTrue(ids != null);

        int tamanioAntes = ids.size();

        // chequeo turistas
        for (int i = 0; i < cant; i++) {
            String nombre = "Paquete " + idTest + " i=" + i;

            // Cada tur. deberían estar una única vez
            assertTrue(ids.remove(nombre));
            assertFalse(ids.remove(nombre));
        }

        assertEquals(tamanioAntes - cant, ids.size());

    }

    @Test
    final void testObtenerDetallesPaquetes() throws TurismoUyException {
        String idTest = "testObtenerDetallesPaquetes";

        assertTrue(contrPaquete != null);

        // 10 paquetes
        generarPaquetes(10, idTest);

        // 100 proveedores
        ControladorUsuarioTest.generarProveedores(100, idTest);

        // 100 departamentos
        ControladorActividadTuristicaTest.generarDepartamentos(100, idTest);

        // 100 actividades
        ControladorActividadTuristicaTest.generarActividades(100, idTest);

        // reparto 10 actividades a cada paquete
        for (int i = 0; i < 10; i++) {
            String nombrePaq = "Paquete " + idTest + " i=" + i;
            for (int j = 0; j < 10; j++) {
                String nombreActString = "Actividad " + idTest + " i=" + (i * 10 + j);
                contrPaquete.agregarActividadAPaquete(nombreActString, nombrePaq);
            }
        }

        var paqList = contrPaquete.obtenerDTPaquetesDetalles();
        assertTrue(paqList != null);

        // Para cada paquete busco si aparece correctamente
        for (int i = 0; i < 10; i++) {
            String nombrePaq = "Paquete " + idTest + " i=" + i;
            String descripcion = "Desc";
            int periodovalidez = 15;
            float descuento = (float) (i + 0.025);

            boolean existePaq = false;
            for (DTPaqueteDetalles paq : paqList) {
                if (paq.getNombre().equals(nombrePaq)) {
                    existePaq = true;

                    assertEquals(descripcion, paq.getDescrpicion());
                    assertEquals(periodovalidez, paq.getValidez());
                    // Uso esto por los posibles errores al comparar float y
                    // doubles
                    assertTrue(Math.abs(descuento - paq.getDescuento()) < 1);

                    // Para cada actividad del paquete, busco si aparece
                    // correctamente
                    var actList = paq.getActividades().values();
                    for (int j = 0; j < 10; j++) {
                        String nombreActString = "Actividad " + idTest + " i=" + (i * 10 + j);
                        String nickProveedor = "Proveedor " + idTest + " i=" + (i * 10 + j);
                        String descripcionAct = "Desc";
                        int duracion = 10;
                        float costo = (float) 10.85;
                        String ciudad = "Ciudad";

                        boolean existeAct = false;
                        for (DTActividadTuristica act : actList) {
                            if (act.getNombre().equals(nombreActString)) {
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
    final void testAgregarActividadAPaqueteOK() throws TurismoUyException {
        String idTest = "testAgregarActividadAPaqueteOK";

        String nombreDep = "Departamento " + idTest + " i=" + 0;
        String nombrePaq = "Paquete " + idTest + " i=" + 0;
        String nickProv = "Proveedor " + idTest + " i=" + 0;

        // 1 proveedor
        ControladorUsuarioTest.generarProveedores(1, idTest);
        // 1 departamento
        ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
        // 1 paquete
        generarPaquetes(1, idTest);

        // 80 actividades en un mismo departamento, de un mismo provedor
        var controladorAct = Fabrica.getInstancia().getIControladorActividadTuristica();
        for (int i = 0; i < 80; i++) {
            String nombreActividad = "Actividad " + idTest + " i=" + i;
            String descripcion = "Desc";
            int duracion = 10;
            float costo = (float) 10.85;
            String ciudad = "Ciudad";

            controladorAct.altaActividadTuristica(nickProv, nombreDep, nombreActividad, descripcion, duracion, costo,
                    ciudad, localDateNow, null, muestraCategorias, null);

            contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);
        }

    }

    @Test
    final void testAgregarActividadAPaqueteRepetida() throws TurismoUyException {
        String idTest = "testAgregarActividadAPaqueteRepetida";

        String nombreDep = "Departamento " + idTest + " i=" + 0;
        String nombrePaq = "Paquete " + idTest + " i=" + 0;
        String nickProv = "Proveedor " + idTest + " i=" + 0;

        // 1 proveedor
        ControladorUsuarioTest.generarProveedores(1, idTest);
        // 1 departamento
        ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
        // 1 paquete
        generarPaquetes(1, idTest);

        // 80 actividades en un mismo departamento, de un mismo provedor
        var controladorAct = Fabrica.getInstancia().getIControladorActividadTuristica();
        for (int i = 0; i < 80; i++) {
            String nombreActividad = "Actividad " + idTest + " i=" + i;
            String descripcion = "Desc";
            int duracion = 10;
            float costo = (float) 10.85;
            String ciudad = "Ciudad";

            controladorAct.altaActividadTuristica(nickProv, nombreDep, nombreActividad, descripcion, duracion, costo,
                    ciudad, localDateNow, null, muestraCategorias, null);

            contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);

            assertThrows(ActividadTuristicaYaRegistradaException.class, () -> {
                contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);
            });
        }
    }

    @Test
    final void testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete() throws TurismoUyException {
        String idTest = "testObtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete";

        String nombreDep = "Departamento " + idTest + " i=" + 0;
        String nombrePaq = "Paquete " + idTest + " i=" + 0;
        String nickProvBase = "Proveedor " + idTest + " i=";

        // 4 proveedores
        ControladorUsuarioTest.generarProveedores(4, idTest);
        // 1 departamento
        ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
        // 1 paquete
        generarPaquetes(1, idTest);

        // 80 actividades en un mismo departamento, 20 por cada proveedor
        var controladorAct = Fabrica.getInstancia().getIControladorActividadTuristica();
        for (int i = 0; i < 80; i++) {
            String nickProveedor = nickProvBase + (i % 4);
            String nombreActividad = "Actividad " + idTest + " i=" + i;
            String descripcion = "Desc";
            int duracion = 10;
            float costo = (float) 10.85;
            String ciudad = "Ciudad";

            controladorAct.altaActividadTuristica(nickProveedor, nombreDep, nombreActividad, descripcion, duracion,
                    costo, ciudad, localDateNow, null, muestraCategorias, null);

			// 10 actividades de cada provedor en un pquete
			controladorAct.cambiarEstadoDeActividadTuristica(nombreActividad, EstadoActividadTuristica.ACEPTADA);
			if (i % 2 == 0) {
				contrPaquete.agregarActividadAPaquete(nombreActividad, nombrePaq);
			}
		}

        // El resto NO deberían estar en el paquete
        var actList = contrPaquete.obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(nombreDep, nombrePaq);

        for (int i = 0; i < 80; i++) {
            String nombreActividad = "Actividad " + idTest + " i=" + i;
            if (i % 2 == 0) {
                assertFalse(actList.contains(nombreActividad));
            } else {
                assertTrue(actList.contains(nombreActividad));
            }
        }

    }

    @Test
    final void testComprarPaqueteOK() throws TurismoUyException {
        String idTest = "testComprarPaqueteOK";

        List<String> nomPaquetes = generarPaquetes(1, idTest);
        ControladorActividadTuristicaTest.generarDepartamentos(2, idTest);
        ControladorUsuarioTest.generarProveedores(2, idTest);
        List<String> nomTuristas = ControladorUsuarioTest.generarTuristas(2, idTest);
        List<String> nomActividades = ControladorActividadTuristicaTest.generarActividades(2, idTest);

        contrActividad.cambiarEstadoDeActividadTuristica(nomActividades.get(0), EstadoActividadTuristica.ACEPTADA);
        contrActividad.cambiarEstadoDeActividadTuristica(nomActividades.get(1), EstadoActividadTuristica.ACEPTADA);
        contrPaquete.agregarActividadAPaquete(nomActividades.get(0), nomPaquetes.get(0));
        contrPaquete.agregarActividadAPaquete(nomActividades.get(1), nomPaquetes.get(0));

        contrPaquete.comprarPaquete(nomTuristas.get(0), nomPaquetes.get(0), 2, null);
        contrPaquete.comprarPaquete(nomTuristas.get(1), nomPaquetes.get(0), 5, null);

        DTTuristaDetallePrivado dtTuristaDetallePrivado = (DTTuristaDetallePrivado) contrUsuario
                .obtenerDTUsuarioDetallePrivado(nomTuristas.get(0));
        DTTuristaDetallePrivado dtTuristaDetallePrivado2 = (DTTuristaDetallePrivado) contrUsuario
                .obtenerDTUsuarioDetallePrivado(nomTuristas.get(1));

        assertEquals(1, dtTuristaDetallePrivado.getCompras().size());
        assertEquals(nomPaquetes.get(0), dtTuristaDetallePrivado.getCompras().get(0).getPaquete().getNombre());
        assertEquals(2, dtTuristaDetallePrivado.getCompras().get(0).getCantTuristas());
        assertEquals((int) (43.183 * 100), (int) (dtTuristaDetallePrivado.getCompras().get(0).getCosto() * 100)); // calcule
                                                                                                                  // por
                                                                                                                  // afuera
                                                                                                                  // cuando
                                                                                                                  // deberia
                                                                                                                  // ser
                                                                                                                  // el
                                                                                                                  // costo
        assertEquals(localDateNow, dtTuristaDetallePrivado.getCompras().get(0).getFechaCompra());
        assertEquals(localDateNow.plusDays(15), dtTuristaDetallePrivado.getCompras().get(0).getVencimiento());

        assertEquals(1, dtTuristaDetallePrivado2.getCompras().size());
        assertEquals((int) (107.957 * 100), (int) (dtTuristaDetallePrivado2.getCompras().get(0).getCosto() * 100)); // calcule
                                                                                                                    // por
                                                                                                                    // afuera
                                                                                                                    // cuando
                                                                                                                    // deberia
                                                                                                                    // ser
                                                                                                                    // el
                                                                                                                    // costo
    }

    @Test
    final void testCompraRepetida() throws TurismoUyException {
        String idTest = "testCompraRepetida";

        List<String> nomPaquetes = generarPaquetes(1, idTest);
        ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
        ControladorUsuarioTest.generarProveedores(1, idTest);
        List<String> nomTuristas = ControladorUsuarioTest.generarTuristas(1, idTest);
        List<String> nomActividades = ControladorActividadTuristicaTest.generarActividades(1, idTest);

        contrActividad.cambiarEstadoDeActividadTuristica(nomActividades.get(0), EstadoActividadTuristica.ACEPTADA);
        contrPaquete.agregarActividadAPaquete(nomActividades.get(0), nomPaquetes.get(0));

        contrPaquete.comprarPaquete(nomTuristas.get(0), nomPaquetes.get(0), 2, null);

        assertThrows(CompraYaRegistradaException.class,
                () -> contrPaquete.comprarPaquete(nomTuristas.get(0), nomPaquetes.get(0), 3, null));

    }

    @Test
    final void testCompraConPaqueteSinActividades() throws TurismoUyException {
        String idTest = "testCompraConPaqueteSinActividades";

        List<String> nomPaquetes = generarPaquetes(1, idTest);
        List<String> nomTuristas = ControladorUsuarioTest.generarTuristas(1, idTest);

        assertThrows(PaquetesSinActividadesExcepcion.class,
                () -> contrPaquete.comprarPaquete(nomTuristas.get(0), nomPaquetes.get(0), 3, null));

    }

    @Test
    final void obtenerIdPaquetesSinComprarOK() throws TurismoUyException {
        String idTest = "obtenerIdPaquetesSinComprarOK";
        List<String> nombrePaquetes = generarPaquetes(2, idTest);
        ControladorActividadTuristicaTest.generarDepartamentos(1, idTest);
        ControladorUsuarioTest.generarProveedores(1, idTest);
        List<String> nombreTurista = ControladorUsuarioTest.generarTuristas(1, idTest);
        List<String> nombreActividades = ControladorActividadTuristicaTest.generarActividades(1, idTest);

        contrPaquete.agregarActividadAPaquete(nombreActividades.get(0), nombrePaquetes.get(0));
        contrPaquete.agregarActividadAPaquete(nombreActividades.get(0), nombrePaquetes.get(1));

        List<String> paquetesNoComprados = contrPaquete.obtenerIdPaquetesSinComprar();

        assertEquals(true, paquetesNoComprados.contains(nombrePaquetes.get(0)));
        assertEquals(true, paquetesNoComprados.contains(nombrePaquetes.get(1)));

        contrPaquete.comprarPaquete(nombreTurista.get(0), nombrePaquetes.get(0), 1, null);
        paquetesNoComprados = contrPaquete.obtenerIdPaquetesSinComprar();
        assertEquals(false, paquetesNoComprados.contains(nombrePaquetes.get(0)));
        assertEquals(true, paquetesNoComprados.contains(nombrePaquetes.get(1)));

        contrPaquete.comprarPaquete(nombreTurista.get(0), nombrePaquetes.get(1), 1, null);
        paquetesNoComprados = contrPaquete.obtenerIdPaquetesSinComprar();
        assertEquals(false, paquetesNoComprados.contains(nombrePaquetes.get(0)));
        assertEquals(false, paquetesNoComprados.contains(nombrePaquetes.get(1)));
    }

    @Test
    final void obtenerDTPaqueteDetalleOK() throws TurismoUyException {
        String idTest = "obtenerDTPaqueteDetalleOK";

        List<String> nombreTuristias = ControladorUsuarioTest.generarTuristas(1, idTest);
        ControladorUsuarioTest.generarProveedores(2, idTest);
        ControladorActividadTuristicaTest.generarDepartamentos(2, idTest);
        List<String> nombreActividades = ControladorActividadTuristicaTest.generarActividades(2, idTest);
        List<String> nombrePaquetes = ControladorPaqueteTest.generarPaquetes(1, idTest);

        contrPaquete.agregarActividadAPaquete(nombreActividades.get(0), nombrePaquetes.get(0));
        contrPaquete.agregarActividadAPaquete(nombreActividades.get(1), nombrePaquetes.get(0));

        contrPaquete.comprarPaquete(nombreTuristias.get(0), nombrePaquetes.get(0), 2, null);

        DTPaqueteDetalles dtPaqueteDetalles = contrPaquete.obtenerDTPaqueteDetalle(nombrePaquetes.get(0));

        assertEquals(nombrePaquetes.get(0), dtPaqueteDetalles.getNombre());
        assertEquals(15, dtPaqueteDetalles.getValidez());
        assertEquals(2, dtPaqueteDetalles.getActividades().size());
        assertEquals(2, dtPaqueteDetalles.getActividades().size());
        assertEquals(true, dtPaqueteDetalles.getActividades().containsKey(nombreActividades.get(0)));
        assertEquals(nombreActividades.get(0),
                dtPaqueteDetalles.getActividades().get(nombreActividades.get(0)).getNombre());
        assertEquals(true, dtPaqueteDetalles.getActividades().containsKey(nombreActividades.get(1)));
        assertEquals(nombreActividades.get(1),
                dtPaqueteDetalles.getActividades().get(nombreActividades.get(1)).getNombre());

        assertEquals(1, dtPaqueteDetalles.getCompras().size());
        assertEquals(localDateNow.plusDays(15), dtPaqueteDetalles.getCompras().get(0).getVencimiento());

    }

}
