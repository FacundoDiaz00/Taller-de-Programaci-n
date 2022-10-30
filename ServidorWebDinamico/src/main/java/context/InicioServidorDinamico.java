package context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InicioServidorDinamico implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // do stuff
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        //try {
            //Fabrica.getInstancia().getIControladorMaestro().generarDatosDePrueba();
            // Yo diria que esto se hace ahora en el servidor
        //} catch (TurismoUyException e) {
        //    e.printStackTrace();
        //}
    	System.out.print("Servidor iniciado");
    }
}
