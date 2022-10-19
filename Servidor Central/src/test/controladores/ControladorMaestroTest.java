package test.controladores;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.TurismoUyException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorMaestro;

class ControladorMaestroTest {
    private static IControladorMaestro contrMaestro = null;
    
    @BeforeAll
    static void preparacionPrevia() {
        contrMaestro = Fabrica.getInstancia().getIControladorMaestro();
    }

    @Test
    void testGenerarDosVecesDatosDePrueba() throws TurismoUyException {
        assertTrue(contrMaestro != null);

        contrMaestro.generarDatosDePrueba();

        assertThrows(TurismoUyException.class, () -> contrMaestro.generarDatosDePrueba());
    }

}
