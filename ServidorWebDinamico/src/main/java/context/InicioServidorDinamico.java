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
    	System.out.print("Servidor iniciado");
    }
}
