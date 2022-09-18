package logica.controladores;
import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.UsuarioYaRegistradoException;
import logica.manejadores.ManejadorUsuario;
import logica.entidades.Turista;
import java.time.LocalDate;
import java.util.ArrayList;
import logica.entidades.Proveedor;
import logica.entidades.SalidaTuristica;

import java.util.List;

import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
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
    public void altaTurista(String nickname, String nombre, String apellido, String correo, LocalDate FNacimiento, Imagen img, String nacionalidad) throws UsuarioYaRegistradoException {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        if(mu.existeUsuario(nickname, correo)) {
            throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
        }

        Turista u = new Turista(nickname, nombre, apellido, correo,FNacimiento, img,nacionalidad);
        mu.addUsuario(u);
    }

    @Override
    public void altaProveedor(String nickname, String nombre, String apellido, String correo, LocalDate FNacimiento, Imagen img, String descripcion, String link) throws UsuarioYaRegistradoException{
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        if(mu.existeUsuario(nickname, correo)) {
            throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
        }
        Proveedor u = new Proveedor(nickname, nombre, apellido, correo,FNacimiento, img, descripcion, link);
        mu.addUsuario(u);
    }

    @Override
    public DTUsuario obtenerDTUsuarioDetalle(String nickname) {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario u = ins.getUsuarioPorNick(nickname);
        return u.obtenerDTUsuarioDetalle();
    }
    
    @Override
    public DTUsuario obtenerDTUsuarioDetallePrivado(String nickname) {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario u = ins.getUsuarioPorNick(nickname);
        return u.obtenerDTUsuarioDetallePrivado();
    }
    
	@Override
	public DTUsuario obtenerDTUsuario(String nickname) {
		ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario u = ins.getUsuarioPorNick(nickname);
        return u.obtenerDTUsuario();
	}
	
	public List<DTUsuario> obtenerDTUsuarios() {
		// TODO
		return null;
	}

	@Override
	public void modificarUsuario(DTUsuario datosNuevos) throws ModificacionUsuarioNoPermitida {
				
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();

        Usuario u_nick = ins.getUsuarioPorNick(datosNuevos.getNickname());
        Usuario u_correo = ins.getUsuarioPorCorreo(datosNuevos.getCorreo());
         
        if (u_nick != null && u_correo != null && u_nick.equals(u_correo)) {
        	u_nick.setearDatos(datosNuevos);
        } else {
            throw new ModificacionUsuarioNoPermitida("No coincide el nickname con el correo de este usuario. Estos dos valores no debe ser modificados.");
        }
	}



}
