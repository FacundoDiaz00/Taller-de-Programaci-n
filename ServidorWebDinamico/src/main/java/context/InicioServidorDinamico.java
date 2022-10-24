package context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import excepciones.TurismoUyException;
import logica.controladores.Fabrica;

public class InicioServidorDinamico implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // do stuff
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            // FIXME hay veces que esto en el tiempo se ejecuta mas de una vez, por ahora no
            // afecta pq está bien hecho el control de errores
            Fabrica.getInstancia().getIControladorMaestro().generarDatosDePrueba();
            System.out.println("Datos de prueba cargados");
        } catch (TurismoUyException e) {
            e.printStackTrace();
        }
    }
}
