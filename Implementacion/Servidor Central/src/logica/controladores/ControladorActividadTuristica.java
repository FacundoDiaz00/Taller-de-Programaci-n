package logica.controladores;

import java.util.Collection;

/**
 * @author Equipo taller prog 16
 */

public class ControladorActividadTuristica implements IControladorActividadTuristica {
	public ControladorActividadTuristica() {
	}
	
	public Collection<String> obtenerIdProveedores() {
		ControladorUsuario CU;
		return CU.obtenerIDProveedores();	
	}
}
