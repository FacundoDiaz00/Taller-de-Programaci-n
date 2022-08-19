package logica.controladores;

import java.util.List;

import logica.datatypes.DTUsuario;
import logica.entidades.Usuario;
import logica.manejadores.ManejadorUsuario;

/**
 * @author Equipo taller prog 16
 */
public class ControladorUsuario implements IControladorUsuario {

    public List<String> obtenerIdUsuarios() {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        return ins.obtenerIdUsuarios();
    }

    public DTUsuario obtenerDTUsuario(String nickname) {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario u = ins.getUsuario(nickname);
        return u.getDTUsuario();
    }

}
