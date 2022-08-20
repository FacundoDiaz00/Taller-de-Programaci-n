package logica.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import logica.entidades.Proveedor;
import logica.entidades.Usuario;


import logica.manejadores.ManejadorUsuario;

/**
 * @author Equipo taller prog 16
 */
public class ControladorUsuario implements IControladorUsuario {
	public ControladorUsuario() {
	}
	
    public ArrayList<String> obtenerIdUsuarios() {
        // TODO implement here
        return new ArrayList<>();
    }

	public ArrayList<String> obtenerIdProveedores() {
		ManejadorUsuario MU = ManejadorUsuario.getInstancia();
		Map<String, Usuario> usuarios = MU.getUsuarios();
		ArrayList<String> res = new ArrayList<String>();
		
		Iterator<String> it = usuarios.keySet().iterator();
		while(it.hasNext()){
			String clave = it.next();
			Usuario usr = usuarios.get(clave);
			//casteamos a usr como proveedor:
			if(usr instanceof Proveedor) {
				res.add(usr.getNombre());
			}
		}
		return res;
	}

}
