package logica.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import logica.entidades.Usuario;


import logica.manejadores.ManejadorUsuario;

/**
 * @author Equipo taller prog 16
 */
public class ControladorUsuario implements IControladorUsuario {
    public ArrayList<String> obtenerIdUsuarios() {
        // TODO implement here
        return new ArrayList<>();
    }

	public Collection<String> obtenerIDProveedores() {
		ManejadorUsuario MU = ManejadorUsuario.getInstancia();
		Map<String, Usuario> Usuarios = MU.getUsuarios();
		Collection<String> res;
		
		return res;
	}

}
