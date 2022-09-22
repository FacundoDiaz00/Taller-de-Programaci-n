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
			//Todo hay veces que esto en el tiempo se ejecuta mas de una vez
			Fabrica.getInstancia().getIControladorMaestro().generarDatosDePrueba();
			System.out.println("Datos de prueba cargados");
		} catch (TurismoUyException e) {
			e.printStackTrace();
		}
	}
}


