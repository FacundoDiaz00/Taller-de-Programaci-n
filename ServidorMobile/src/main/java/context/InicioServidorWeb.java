package context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import configuraciones.Cargador;

public class InicioServidorWeb implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // do stuff
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    	boolean resultado = Cargador.cargarPropiedades();
    	if (!resultado) {
    		System.exit(0);
    	}
    }
}
