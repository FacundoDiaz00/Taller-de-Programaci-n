package serversContext;

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
			Fabrica.getInstancia().getIControladorMaestro().generarDatosDePrueba();
		} catch (TurismoUyException e) {
			e.printStackTrace();
		}
	}
}


