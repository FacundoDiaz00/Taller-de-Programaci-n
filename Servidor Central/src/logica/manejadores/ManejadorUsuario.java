package logica.manejadores;

import java.util.*;

import logica.entidades.Usuario;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorUsuario {
    private static ManejadorUsuario instancia;

    private Map<String, Usuario> usuariosPorNick;
    private Map<String, Usuario> usuariosPorCorreo;
    
    private ManejadorUsuario() {
    	usuariosPorCorreo = new HashMap<String, Usuario>();
        usuariosPorNick = new HashMap<String, Usuario>();
    }

    public static ManejadorUsuario getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorUsuario();
        }
        return instancia;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<Usuario>(usuariosPorNick.values());
    }

    public Set<String> obtenerCorreoUsuarios(){
        return usuariosPorCorreo.keySet();
    }

    public Set<String> obtenerNickUsuarios(){
        return usuariosPorNick.keySet();
    }

    public void addUsuario(Usuario usuario) {
        usuariosPorNick.put(usuario.getNickname(), usuario);
        usuariosPorCorreo.put(usuario.getCorreo(), usuario);
    }

    public Usuario getUsuarioPorNick(String nickname) {
        return usuariosPorNick.get(nickname);
    }
    public Usuario getUsuarioPorCorreo(String correo) {
        return usuariosPorCorreo.get(correo);
    }


    public boolean existeUsuario(String nickname, String correo) {
    	return usuariosPorNick.containsKey(nickname) || usuariosPorCorreo.containsKey(correo);
    }
    
}
