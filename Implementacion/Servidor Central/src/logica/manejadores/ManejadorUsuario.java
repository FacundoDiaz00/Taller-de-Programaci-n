package logica.manejadores;

import java.util.Map;
import java.util.HashMap;

import logica.entidades.Usuario;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorUsuario {
    private static ManejadorUsuario instancia;

    private Map<String, Usuario> usuarios;
    
    private ManejadorUsuario() {
    	usuarios = new HashMap<String, Usuario>();
    }

    public static ManejadorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorUsuario();
        }
        return instancia;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void addUsuario(Usuario usuario) {
        usuarios.put(usuario.getNickname(), usuario);
    }

    public Usuario getUsuario(String nombre) {
        return usuarios.get(nombre);
    }
    
    public Boolean existeUsuario(String nickname, String correo) {
    	return usuarios.containsKey(nickname) || usuarios.get(nickname).getCorreo() == correo;
    }
    
}