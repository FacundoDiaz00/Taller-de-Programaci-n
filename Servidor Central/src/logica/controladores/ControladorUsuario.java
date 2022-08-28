package logica.controladores;
import excepciones.UsuarioYaRegistradoException;
import logica.manejadores.ManejadorUsuario;
import logica.entidades.Turista;
import java.time.LocalDate;
import java.util.ArrayList;
import logica.entidades.Proveedor;
import logica.entidades.SalidaTuristica;

import java.util.List;

import logica.datatypes.DTUsuario;
import logica.entidades.Usuario;


/**
 * @author Equipo taller prog 16
 */
public class ControladorUsuario implements IControladorUsuario {

	public ControladorUsuario() {
	}
	
	public Turista obtenerTurista(String nickTur){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		return (Turista) mu.getUsuarioPorNick(nickTur);
	}
	
	public Proveedor obtenerProveedor(String nickProv){
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		return (Proveedor) mu.getUsuarioPorNick(nickProv);
	}
    
	public List<String> obtenerIdUsuarios() {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        return new ArrayList<String>(ins.obtenerNickUsuarios());
    }

    @Override
	public ArrayList<String> obtenerIdProveedores() {
		ManejadorUsuario MU = ManejadorUsuario.getInstancia();
		List<Usuario> usuarios = MU.getUsuarios();
		ArrayList<String> res = new ArrayList<String>();
		for(Usuario user : usuarios){
			if(user instanceof Proveedor) {
				res.add(user.getNickname());
			}
		}
		return res;
	}

    @Override
    public List<String> obtenerIdTuristas() {
        ManejadorUsuario MU = ManejadorUsuario.getInstancia();
        List<Usuario> usuarios = MU.getUsuarios();
        ArrayList<String> res = new ArrayList<String>();
        for(Usuario user : usuarios){
            if(user instanceof Turista) {
                res.add(user.getNickname());
            }
        }
        return res;
    }

    @Override
    public void altaTurista(String nickname, String nombre, String apellido, String correo, LocalDate FNacimiento, String nacionalidad) throws UsuarioYaRegistradoException {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        if(mu.existeUsuario(nickname, correo)) {
            throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
        }

        Turista u = new Turista(nickname, nombre, apellido, correo,FNacimiento,nacionalidad);
        mu.addUsuario(u);
    }

    @Override
    public void altaProveedor(String nickname, String nombre, String apellido, String correo, String descripcion, String link, LocalDate FNacimiento) throws UsuarioYaRegistradoException{
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        if(mu.existeUsuario(nickname, correo)) {
            throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
        }
        Proveedor u = new Proveedor(nickname, nombre, apellido, correo,FNacimiento, descripcion, link);
        mu.addUsuario(u);
    }

    @Override
    public DTUsuario obtenerDTUsuarioDetalle(String nickname) {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario u = ins.getUsuarioPorNick(nickname);
        return u.obtenerDTUsuarioDetalle();
    }

    public void agregarSalida(SalidaTuristica st) {
    	//TODO: Implentarla  ¿Esta correcto que esto este aca? Mirando el digrama de comunicacion creo que va en el manejador de salida
    }
    
	@Override
	public DTUsuario obtenerDTUsuario(String nickname) {
		ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario u = ins.getUsuarioPorNick(nickname);
        return u.obtenerDTUsuario();
	}

	@Override
	public void modificarUsuario(DTUsuario datosNuevos) {
		ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario u_nick = ins.getUsuarioPorNick(datosNuevos.getNickname());
        Usuario u_correo = ins.getUsuarioPorCorreo(datosNuevos.getCorreo());
         
        if (u_nick == u_correo && u_nick != null) {
        	u_nick.setearDatos(datosNuevos);
        }        
	}



}
