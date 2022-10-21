package test.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.TurismoUyException;
import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTProveedorDetalle;
import logica.datatypes.DTTurista;
import logica.datatypes.DTTuristaDetalle;

class ControladorUsuarioTest {
    private static IControladorUsuario contrUsr = null;
    private static IControladorActividadTuristica contrActTur = null;
    private static LocalDate localDateNow;

    @BeforeAll
    static void preparacionPrevia() {
        contrUsr = Fabrica.getInstancia().getIControladorUsuario();
        contrActTur = Fabrica.getInstancia().getIControladorActividadTuristica();
        localDateNow = LocalDate.now();
    }

    // No es un test en sí
    static List<String> generarProveedores(int cant, String idTest) throws TurismoUyException {
        if (contrUsr == null)
            preparacionPrevia();

        assertTrue(contrUsr != null);

        List<String> nicknameProveedores = new ArrayList<>();

        for (int i = 0; i < cant; i++) {
            String nickname = "Proveedor " + idTest + " i=" + i;
            String nombre = "NOMBRE PROV";
            String apellido = "APELLIDO PROV";
            String correo = "Proveedor " + idTest + " i=" + i;
            String descripcion = "HOLA! DESCRIPCION";
            String link = "www.google.com.provedoor";
            LocalDate fNacimiento = localDateNow.minusYears(30);

            contrUsr.altaProveedor(nickname, nombre, apellido, correo, "1234", fNacimiento, null, descripcion, link);
            nicknameProveedores.add(nickname);
        }
        return nicknameProveedores;
    }

    // No es un test en sí
    static List<String> generarTuristas(int cant, String idTest) throws TurismoUyException {
        if (contrUsr == null)
            preparacionPrevia();

        assertTrue(contrUsr != null);

        List<String> nicknameTuristas = new ArrayList<>();

        for (int i = 0; i < cant; i++) {
            String nickname = "Turista " + idTest + " i=" + i;
            String nombre = "NOMBRE TURISTA";
            String apellido = "APELLIDO TURISTA";
            String correo = "TURISTA " + idTest + " i=" + i;
            String nacionalidad = "CHINA";
            LocalDate fNacimiento = localDateNow.minusYears(15);

            contrUsr.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
            nicknameTuristas.add(nickname);
        }
        return nicknameTuristas;
    }

    @Test
    void testObtenerIdUsuarios() throws TurismoUyException {
        assertTrue(contrUsr != null);

        String idTest = "testObtenerIdUsuarios";

        generarProveedores(50, idTest);
        generarTuristas(50, idTest);

        var ids = contrUsr.obtenerIdUsuarios();
        assertTrue(ids != null);

        int tamanioAntes = ids.size();

        // chequeo proveedores
        for (int i = 0; i < 50; i++) {
            String nickname = "Proveedor " + idTest + " i=" + i;

            // Cada prov deberían estar una única vez
            assertTrue(ids.remove(nickname));
            assertFalse(ids.remove(nickname));
        }

        // chequeo turistas
        for (int i = 0; i < 50; i++) {
            String nickname = "Turista " + idTest + " i=" + i;

            // Cada tur. deberían estar una única vez
            assertTrue(ids.remove(nickname));
            assertFalse(ids.remove(nickname));
        }

        assertEquals(tamanioAntes - 100, ids.size());
    }

    @Test
    void testObtenerIdProveedores() throws TurismoUyException {
        assertTrue(contrUsr != null);

        String idTest = "testObtenerIdProveedores";

        generarProveedores(100, idTest);

        var ids = contrUsr.obtenerIdProveedores();
        assertTrue(ids != null);

        int tamanioAntes = ids.size();

        // chequeo proveedores
        for (int i = 0; i < 100; i++) {
            String nickname = "Proveedor " + idTest + " i=" + i;

            // Cada prov deberían estar una única vez
            assertTrue(ids.remove(nickname));
            assertFalse(ids.remove(nickname));
        }

        assertEquals(tamanioAntes - 100, ids.size());
    }

    @Test
    void testObtenerIdTuristas() throws TurismoUyException {
        assertTrue(contrUsr != null);

        String idTest = "testObtenerIdTuristas";

        generarTuristas(100, idTest);

        var ids = contrUsr.obtenerIdTuristas();
        assertTrue(ids != null);

        int tamanioAntes = ids.size();

        // chequeo turistas
        for (int i = 0; i < 100; i++) {
            String nickname = "Turista " + idTest + " i=" + i;

            // Cada tur. deberían estar una única vez
            assertTrue(ids.remove(nickname));
            assertFalse(ids.remove(nickname));
        }

        assertEquals(tamanioAntes - 100, ids.size());
    }

    @Test
    void testAltaTuristaOK() throws TurismoUyException {
        generarTuristas(100, "testAltaTuristaOK");
    }

    @Test
    void testAltaTuristaRepetido() throws TurismoUyException {
        String idTest = "testAltaTuristaRepetido";

        generarTuristas(50, idTest);

        assertThrows(UsuarioYaRegistradoException.class, () -> {
            generarTuristas(1, idTest);
        });

        String nickname = "Turista CON NICK NUEVO " + idTest;
        String nombre = "NOMBRE TURISTA";
        String apellido = "APELLIDO TURISTA";
        String correo = "TURISTA " + idTest + " i=" + 0;
        String nacionalidad = "CHINA";
        LocalDate fNacimiento = LocalDate.now().minusYears(15);

        assertThrows(UsuarioYaRegistradoException.class, () -> {
            contrUsr.altaTurista(nickname, nombre, apellido, correo, "1234", fNacimiento, null, nacionalidad);
        });
    }

    @Test
    void testAltaProveedorOK() throws TurismoUyException {
        generarProveedores(100, "testAltaProveedorOK");
    }

    @Test
    void testAltaProveedorRepetido() throws TurismoUyException {
        String idTest = "testAltaProveedorRepetido";

        generarProveedores(50, idTest);

        assertThrows(UsuarioYaRegistradoException.class, () -> {
            generarProveedores(1, idTest);
        });

        String nickname = "Proveedor CON NICK NUEVO " + idTest;
        String nombre = "NOMBRE PROV";
        String apellido = "APELLIDO PROV";

        // Mismo correo que el generador:
        String correo = "Proveedor " + idTest + " i=" + 0;
        String descripcion = "HOLA! DESCRIPCION";
        String link = "www.google.com.provedoor";
        LocalDate fNacimiento = LocalDate.now().minusYears(30);

        assertThrows(UsuarioYaRegistradoException.class, () -> {
            contrUsr.altaProveedor(nickname, nombre, apellido, correo, "1234", fNacimiento, null, descripcion, link);
        });
    }

    @Test
    void testObtenerDTUsuarioDetalle() throws TurismoUyException {
        String idTest = "testObtenerDTUsuarioDetalle";

        generarProveedores(50, idTest);
        generarTuristas(50, idTest);
        ControladorActividadTuristicaTest.generarDepartamentos(50, idTest);
        ControladorActividadTuristicaTest.generarActividades(50, idTest);
        ControladorActividadTuristicaTest.generarSalidas(50, idTest);

        // A los primeros 40 les asigno una sola salida
        for (int i = 0; i < 40; i++) {
            String nombreSalida = "Salida " + idTest + " i=" + i;
            String nickname = "Turista " + idTest + " i=" + i;
            contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, LocalDate.now(), null);
        }

        // A los ultimos 10 les asigno una todas las salidas
        for (int i = 40; i < 50; i++) {
            String nickname = "Turista " + idTest + " i=" + i;
            for (int j = 0; j < 40; j++) {
                String nombreSalida = "Salida " + idTest + " i=" + j;
                contrActTur.altaInscripcionSalidaTuristica(nombreSalida, nickname, 1, LocalDate.now(), null);
            }
        }

        // Verifico para los proveedores
        for (int i = 0; i < 50; i++) {
            String nickname = "Proveedor " + idTest + " i=" + i;
            var dtdet = contrUsr.obtenerDTUsuarioDetalle(nickname);
            String nombre = "NOMBRE PROV";
            String apellido = "APELLIDO PROV";
            String correo = "Proveedor " + idTest + " i=" + i;
            String descripcion = "HOLA! DESCRIPCION";
            String link = "www.google.com.provedoor";
            LocalDate fNacimiento = localDateNow.minusYears(30);
            String nombreSalida = "Salida " + idTest + " i=" + i;
            String nombreActividad = "Actividad " + idTest + " i=" + i;

            assertThrows(ClassCastException.class, () -> {

                @SuppressWarnings("unused")
                var dtErrorCasteado = (DTTuristaDetalle) dtdet;
            });

            var dtCasteado = (DTProveedorDetalle) dtdet;

            assertEquals(nickname, dtCasteado.getNickname());
            assertEquals(nombre, dtCasteado.getNombre());
            assertEquals(apellido, dtCasteado.getApellido());
            assertEquals(correo, dtCasteado.getCorreo());
            assertEquals(descripcion, dtCasteado.getDescrpicionGeneral());
            assertEquals(link, dtCasteado.getLink());
            assertEquals(fNacimiento, dtCasteado.getFechaNac());

            assertEquals(1, dtCasteado.getActividades().size());
            var nombreActObtenida = dtCasteado.getActividades().get(0).getNombre();
            assertEquals(nombreActividad, nombreActObtenida);

            var salidasAsociadasObtenidas = dtCasteado.getActividades().get(0).getSalidas().keySet().toArray();
            assertEquals(1, salidasAsociadasObtenidas.length);
            assertEquals(nombreSalida, salidasAsociadasObtenidas[0]);
        }

        // Verifico para los turistas
        for (int i = 0; i < 50; i++) {
            String nickname = "Turista " + idTest + " i=" + i;
            String nombre = "NOMBRE TURISTA";
            String apellido = "APELLIDO TURISTA";
            String correo = "TURISTA " + idTest + " i=" + i;
            String nacionalidad = "CHINA";
            LocalDate fNacimiento = localDateNow.minusYears(15);
            String nombreSalida = "Salida " + idTest + " i=" + i;

            var dtdet = contrUsr.obtenerDTUsuarioDetalle(nickname);

            assertThrows(ClassCastException.class, () -> {
                @SuppressWarnings("unused")
                var dtErrorCasteado = (DTProveedorDetalle) dtdet;
            });

            var dtCasteado = (DTTuristaDetalle) dtdet;

            assertEquals(nickname, dtCasteado.getNickname());
            assertEquals(nombre, dtCasteado.getNombre());
            assertEquals(apellido, dtCasteado.getApellido());
            assertEquals(correo, dtCasteado.getCorreo());
            assertEquals(nacionalidad, dtCasteado.getNacionalidad());
            assertEquals(fNacimiento, dtCasteado.getFechaNac());

            if (i < 40) {
                assertEquals(1, dtCasteado.getInscripciones().size());
                var SalObtenida = dtCasteado.getInscripciones().get(0);
                assertEquals(nombreSalida, SalObtenida.getNombre());
            } else {
                assertEquals(40, dtCasteado.getInscripciones().size());
            }

        }
    }

    @Test
    void testObtenerDTUsuario() throws TurismoUyException {
        String idTest = "testObtenerDTUsuario";

        List<String> nombreProveedores = generarProveedores(50, idTest);
        List<String> nombreTuristas = generarTuristas(50, idTest);

        // Verifico para los proveedores
        for (int i = 0; i < 50; i++) {
            String nickname = nombreProveedores.get(i);
            String nombre = "NOMBRE PROV";
            String apellido = "APELLIDO PROV";
            String correo = "Proveedor " + idTest + " i=" + i;
            String descripcion = "HOLA! DESCRIPCION";
            String link = "www.google.com.provedoor";
            LocalDate fNacimiento = localDateNow.minusYears(30);

            var dtUsuario = contrUsr.obtenerDTUsuario(nickname);

            assertThrows(ClassCastException.class, () -> {
                @SuppressWarnings("unused")
                var dtErrorCasteado = (DTTurista) dtUsuario;
            });

            var dtCasteado = (DTProveedor) dtUsuario;

            assertEquals(nickname, dtCasteado.getNickname());
            assertEquals(nombre, dtCasteado.getNombre());
            assertEquals(apellido, dtCasteado.getApellido());
            assertEquals(correo, dtCasteado.getCorreo());
            assertEquals(descripcion, dtCasteado.getDescrpicionGeneral());
            assertEquals(link, dtCasteado.getLink());
            assertEquals(fNacimiento, dtCasteado.getFechaNac());
        }

        // Verifico para los turistas
        for (int i = 0; i < 50; i++) {
            String nickname = nombreTuristas.get(i);
            String nombre = "NOMBRE TURISTA";
            String apellido = "APELLIDO TURISTA";
            String correo = "TURISTA " + idTest + " i=" + i;
            String nacionalidad = "CHINA";
            LocalDate fNacimiento = localDateNow.minusYears(15);

            var dtUsuario = contrUsr.obtenerDTUsuario(nickname);

            assertThrows(ClassCastException.class, () -> {
                @SuppressWarnings("unused")
                var dtErrorCasteado = (DTProveedor) dtUsuario;
            });

            var dtCasteado = (DTTurista) dtUsuario;

            assertEquals(nickname, dtCasteado.getNickname());
            assertEquals(nombre, dtCasteado.getNombre());
            assertEquals(apellido, dtCasteado.getApellido());
            assertEquals(correo, dtCasteado.getCorreo());
            assertEquals(nacionalidad, dtCasteado.getNacionalidad());
            assertEquals(fNacimiento, dtCasteado.getFechaNac());
        }

        // Caso en el que no se cumplen las pre-condiciones
        assertThrows(Exception.class, () -> {
            contrUsr.obtenerDTUsuario("NICK QUE OBVIAMENTE NO ESTA EN EL SISTEMA");
        });
    }

    @Test
    void testModificarUsuarioCaso() throws TurismoUyException {
        String idTest = "testModificarUsuario";

        generarProveedores(50, idTest);
        generarTuristas(50, idTest);

        for (int i = 0; i < 50; i++) {
            String nicknameTur = "Turista " + idTest + " i=" + i;
            String nicknameProv = "Proveedor " + idTest + " i=" + i;

            DTTurista dttur = (DTTurista) contrUsr.obtenerDTUsuario(nicknameTur);
            DTProveedor dtprov = (DTProveedor) contrUsr.obtenerDTUsuario(nicknameProv);

            contrUsr.modificarUsuario(dttur);
            contrUsr.modificarUsuario(dtprov);

            assertEquals(dttur, (DTTurista) contrUsr.obtenerDTUsuario(nicknameTur));
            assertEquals(dtprov, (DTProveedor) contrUsr.obtenerDTUsuario(nicknameProv));

            // Caso típico:
            String nuevoString = "NUEVO DATO";

            DTTurista dtTurNuevo = new DTTurista(dttur.getNickname(), dttur.getNombre() + nuevoString,
                    dttur.getApellido() + nuevoString, dttur.getCorreo(), dttur.getFechaNac().plusDays(1), null,
                    dttur.getNacionalidad() + nuevoString);
            DTProveedor dtProvNuevo = new DTProveedor(dtprov.getNickname(), dtprov.getNombre() + nuevoString,
                    dtprov.getApellido() + nuevoString, dtprov.getCorreo(), dtprov.getFechaNac().plusDays(1), null,
                    dtprov.getDescrpicionGeneral() + nuevoString, dtprov.getLink() + nuevoString);

            contrUsr.modificarUsuario(dtTurNuevo);
            contrUsr.modificarUsuario(dtProvNuevo);

            dttur = (DTTurista) contrUsr.obtenerDTUsuario(dtTurNuevo.getNickname());
            dtprov = (DTProveedor) contrUsr.obtenerDTUsuario(dtProvNuevo.getNickname());
            assertEquals(dtTurNuevo, dttur);
            assertEquals(dtProvNuevo, dtprov);

            // Caso en el que se modifica el correo
            DTTurista dtTurNuevo2 = new DTTurista(dttur.getNickname(), dttur.getNombre(), dttur.getApellido(),
                    dttur.getCorreo() + nuevoString, dttur.getFechaNac(), null, dttur.getNacionalidad());
            DTProveedor dtProvNuevo2 = new DTProveedor(dtprov.getNickname(), dtprov.getNombre(), dtprov.getApellido(),
                    dtprov.getCorreo() + nuevoString, dtprov.getFechaNac(), null, dtprov.getDescrpicionGeneral(),
                    dtprov.getLink());

            assertThrows(ModificacionUsuarioNoPermitida.class, () -> {
                contrUsr.modificarUsuario(dtTurNuevo2);
            });

            assertThrows(ModificacionUsuarioNoPermitida.class, () -> {
                contrUsr.modificarUsuario(dtProvNuevo2);
            });

            contrUsr.modificarUsuario(dtTurNuevo);
            contrUsr.modificarUsuario(dtProvNuevo);

            dttur = (DTTurista) contrUsr.obtenerDTUsuario(dtTurNuevo.getNickname());
            dtprov = (DTProveedor) contrUsr.obtenerDTUsuario(dtProvNuevo.getNickname());
            assertNotEquals(dtTurNuevo2, dttur);
            assertNotEquals(dtProvNuevo2, dtprov);

            // Caso en el que se modifica el nick:
            DTTurista dtTurNuevo3 = new DTTurista(dttur.getNickname() + nuevoString, dttur.getNombre(),
                    dttur.getApellido(), dttur.getCorreo(), dttur.getFechaNac(), null, dttur.getNacionalidad());
            DTProveedor dtProvNuevo3 = new DTProveedor(dtprov.getNickname() + nuevoString, dtprov.getNombre(),
                    dtprov.getApellido(), dtprov.getCorreo(), dtprov.getFechaNac(), null,
                    dtprov.getDescrpicionGeneral(), dtprov.getLink());

            assertThrows(ModificacionUsuarioNoPermitida.class, () -> {
                contrUsr.modificarUsuario(dtTurNuevo3);
            });

            assertThrows(ModificacionUsuarioNoPermitida.class, () -> {
                contrUsr.modificarUsuario(dtProvNuevo3);
            });

            dttur = (DTTurista) contrUsr.obtenerDTUsuario(dttur.getNickname());
            dtprov = (DTProveedor) contrUsr.obtenerDTUsuario(dtprov.getNickname());
            assertNotEquals(dtTurNuevo3, dttur);
            assertNotEquals(dtProvNuevo3, dtprov);
        }
    }

}
