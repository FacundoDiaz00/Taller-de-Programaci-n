package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.TurismoUyException;
import logica.controladores.ControladorMaestro;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorMaestro;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedorDetallePrivado;
import logica.datatypes.DTTuristaDetallePrivado;

class ControladorMaestroTest {
    private static IControladorMaestro contrMaestro = null;

    @BeforeAll
    static void preparacionPrevia() {
        contrMaestro = Fabrica.getInstancia().getIControladorMaestro();
    }

    @Test
    void testGenerarDosVecesDatosDePrueba() throws TurismoUyException {
        contrMaestro.generarDatosDePrueba();

        IControladorActividadTuristica icat = Fabrica.getInstancia().getIControladorActividadTuristica();
        IControladorUsuario iuser = Fabrica.getInstancia().getIControladorUsuario();
        IControladorPaquete ipack = Fabrica.getInstancia().getIControladorPaquete();

        // Test de carga de categorias
        var categorias = icat.obtenerIdCategorias();
        for (String cat : ControladorMaestro.datosCategorias) {
            assertTrue(categorias.contains(cat));
        }

        // Test de carga de departamentos
        var departamentos = icat.obtenerIdDepartamentos();
        for (String[] dep : ControladorMaestro.datosDepartamentos) {
            assertTrue(departamentos.contains(dep[0]));
        }

        // Cargo usuarios
        for (int i = 0; i < ControladorMaestro.datosUsuarios.length; i++) {
            String[] usr = ControladorMaestro.datosUsuarios[i];
            var fechaNac = ControladorMaestro.fechasNacUsuarios[i];

            if (usr[6] == null) {
                var turista = (DTTuristaDetallePrivado) iuser.obtenerDTUsuarioDetallePrivado(usr[0]);
                // Cargo Turistas
                assertEquals(usr[0], turista.getNickname());
                assertEquals(usr[1], turista.getNombre());
                assertEquals(usr[2], turista.getApellido());
                assertEquals(usr[3], turista.getCorreo());
                assertEquals(usr[5], turista.getNacionalidad());
                assertEquals(LocalDate.of(fechaNac[2], fechaNac[1], fechaNac[0]), turista.getFechaNac());
                assertEquals("/usuarios/" + usr[0] + ".png", turista.getImg().getPath());
            } else {
                var proveedor = (DTProveedorDetallePrivado) iuser.obtenerDTUsuarioDetallePrivado(usr[0]);
                // Cargo Turistas
                assertEquals(usr[0], proveedor.getNickname());
                assertEquals(usr[1], proveedor.getNombre());
                assertEquals(usr[2], proveedor.getApellido());
                assertEquals(usr[3], proveedor.getCorreo());
                assertEquals(LocalDate.of(fechaNac[2], fechaNac[1], fechaNac[0]), proveedor.getFechaNac());
                assertEquals("/usuarios/" + usr[0] + ".png", proveedor.getImg().getPath());
                assertEquals(usr[6], proveedor.getDescrpicionGeneral());
                assertEquals(usr[7], proveedor.getLink());
            }
        }

        // Altas de actividades
        for (int i = 0; i < ControladorMaestro.datosActividadesStrings.length; i++) {
            String[] datosAct = ControladorMaestro.datosActividadesStrings[i];
            int[] datosInt = ControladorMaestro.datosActividadesInteger[i];
            String[] categoriasDatos = ControladorMaestro.actividadesCategorias[i];

            var actividad = icat.obtenerDTActividadTuristicaDetalle(datosAct[0]);
            assertEquals(datosAct[0], actividad.getNombre());
            assertEquals(datosAct[4], actividad.getNicknameProveedor());
            assertEquals(datosAct[3], actividad.getDepartamento());
            assertEquals(datosAct[1], actividad.getDescripcion());
            assertEquals(datosInt[0], actividad.getDuracion());
            assertEquals(datosInt[1], actividad.getCostoPorTurista());
            assertEquals(datosAct[2], actividad.getCuidad());
            assertEquals(LocalDate.of(datosInt[4], datosInt[3], datosInt[2]), actividad.getFechaAlta());
            assertEquals("/actividades/" + datosAct[0] + ".png", actividad.getImg().getPath());

            var categoriasAct = actividad.getCategorias();
            Arrays.asList(categoriasDatos).forEach((String cat) -> assertTrue(categoriasAct.contains(cat)));
        }

        // Altas de salidas
        for (int i = 0; i < ControladorMaestro.datosSalidasStrings.length; i++) {
            var salStr = ControladorMaestro.datosSalidasStrings[i];
            var salInt = ControladorMaestro.datosSalidasInteger[i];
            String img = null;

            if (ControladorMaestro.salidaTieneImg[i])
                img = "/salidas/" + salStr[1] + ".png";

            var salida = icat.obtenerDTSalidaTuristica(salStr[1]);

            assertEquals(salStr[0], salida.getActividad());
            assertEquals(salStr[1], salida.getNombre());
            assertEquals(LocalDateTime.of(salInt[2], salInt[1], salInt[0], salInt[3], 0), salida.getFechaHoraSalida());
            assertEquals(LocalDate.of(salInt[7], salInt[6], salInt[5]), salida.getFechaAlta());
            assertEquals(salStr[2], salida.getLugarSalida());
            assertEquals(salInt[4], salida.getCantMaxTuristas());

            if (img != null)
                assertEquals(img, salida.getImg().getPath());

            assertThrows(TurismoUyException.class,
                    () -> icat.altaSalidaTuristica(salStr[0], salStr[1],
                            LocalDateTime.of(salInt[2], salInt[1], salInt[0], salInt[3], 0),
                            LocalDate.of(salInt[7], salInt[6], salInt[5]), salStr[2], salInt[4], null));
        }

        // Altas de paquetes
        for (int i = 0; i < ControladorMaestro.datosPaquetesString.length; i++) {
            String[] datosPaq = ControladorMaestro.datosPaquetesString[i];
            int[] datosPaqInt = ControladorMaestro.datosPaquetesInteger[i];

            var paquete = ipack.obtenerDTPaqueteDetalle(datosPaq[0]);

            assertEquals(datosPaq[0], paquete.getNombre());
            assertEquals(datosPaq[1], paquete.getDescrpicion());
            assertEquals(datosPaqInt[0], paquete.getValidez());
            assertEquals(datosPaqInt[1], paquete.getDescuento());
            assertEquals(LocalDate.of(datosPaqInt[4], datosPaqInt[3], datosPaqInt[2]), paquete.getFechaRegistro());
            assertEquals("/paquetes/" + datosPaq[0] + ".png", paquete.getImg().getPath());
        }

        // Agregar Actividad a Paquete
        for (int i = 0; i < ControladorMaestro.asigPaqueteAct.length; i++) {
            var actividad = icat.obtenerDTActividadTuristicaDetalle(ControladorMaestro.asigPaqueteAct[i][1]);
            var paquete = ipack.obtenerDTPaqueteDetalle(ControladorMaestro.asigPaqueteAct[i][0]);

            var paqDentroDeActividad = actividad.getPaquete(ControladorMaestro.asigPaqueteAct[i][0]);
            var actDentroDePaquete = paquete.getActividades().get(ControladorMaestro.asigPaqueteAct[i][1]);

            assertEquals(actividad.getNombre(), actDentroDePaquete.getNombre());
            assertEquals(actividad.getCostoPorTurista(), actDentroDePaquete.getCostoPorTurista());
            assertEquals(actividad.getCuidad(), actDentroDePaquete.getCuidad());
            assertEquals(actividad.getFechaAlta(), actDentroDePaquete.getFechaAlta());
            assertEquals(actividad.getDescripcion(), actDentroDePaquete.getDescripcion());

            assertEquals(paquete.getNombre(), paqDentroDeActividad.getNombre());
            assertEquals(paquete.getCategorias(), paqDentroDeActividad.getCategorias());
            assertEquals(paquete.getDescrpicion(), paqDentroDeActividad.getDescrpicion());
            assertEquals(paquete.getFechaRegistro(), paqDentroDeActividad.getFechaRegistro());

        }

        // Compras de paquetes
        for (int i = 0; i < ControladorMaestro.asigPaqueteTurista.length; i++) {
            var datos = ControladorMaestro.datosCompras[i];
            var asignacion = ControladorMaestro.asigPaqueteTurista[i];

            var turista = (DTTuristaDetallePrivado) iuser.obtenerDTUsuarioDetallePrivado(asignacion[0]);
            var compras = turista.getCompras();

            boolean aparece = false;
            for (var compra : compras) {
                if (compra.getPaquete().getNombre().equals(asignacion[1])) {
                    aparece = true;
                    assertEquals(LocalDate.of(datos[3], datos[2], datos[1]), compra.getFechaCompra());
                    assertEquals(datos[0], compra.getCantTuristas());
                }
            }
            assertTrue(aparece);
        }

        // Inscripciones
        for (int i = 0; i < ControladorMaestro.datosInscripcion.length; i++) {
            var datosStr = ControladorMaestro.datosInscripcion[i];
            var datosInt = ControladorMaestro.datosIntegerInscripcion[i];
            assertThrows(TurismoUyException.class,
                    () -> icat.altaInscripcionSalidaTuristica(datosStr[0], datosStr[1], datosInt[0],
                            LocalDate.of(datosInt[4], datosInt[3], datosInt[2]), datosStr[2]));
        }
    }

}
