package test.controladores;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.TurismoUyException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorMaestro;
import logica.controladores.IControladorPaquete;
import logica.controladores.IControladorUsuario;

class ControladorMaestroTest {
    private static IControladorMaestro contrMaestro = null;
    private static IControladorActividadTuristica icat = null;
    private static IControladorUsuario iuser = null;
    private static IControladorPaquete ipack = null;

    @BeforeAll
    static void preparacionPrevia() {
        contrMaestro = Fabrica.getInstancia().getIControladorMaestro();
        icat = Fabrica.getInstancia().getIControladorActividadTuristica();
        iuser = Fabrica.getInstancia().getIControladorUsuario();
        ipack = Fabrica.getInstancia().getIControladorPaquete();
    }

    @Test
    void testObtenerIdUsuarios() throws TurismoUyException {
        assertTrue(contrMaestro != null);

        contrMaestro.generarDatosDePrueba();

        assertThrows(TurismoUyException.class, () -> contrMaestro.generarDatosDePrueba());
    }

}
