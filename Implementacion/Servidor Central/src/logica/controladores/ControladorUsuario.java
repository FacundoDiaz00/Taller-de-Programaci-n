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
	
	public Turista obtenerTurista(String nomTur){
		return null;
	}
	
	public Proveedor obtenerProveedor(String nomProv){
		return null;
	}
    
	public List<String> obtenerIdUsuarios() {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        return new ArrayList<String>(ins.obtenerIdUsuarios());
    }
	
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
    public void altaTurista(String nickname, String nombre, String apellido, String correo, LocalDate FNacimiento, String nacionalidad) throws UsuarioYaRegistradoException {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        if(mu.existeUsuario(nickname, correo)) {
            throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
        }

        Turista u = new Turista(nickname, nombre, apellido, correo,FNacimiento,nacionalidad);
        mu.addUsuario(u);
    }

    public void altaProveedor(String nickname, String nombre, String apellido, String correo, String descripcion, String link, LocalDate FNacimiento) throws UsuarioYaRegistradoException{
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        if(mu.existeUsuario(nickname, correo)) {
            throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
        }
        Proveedor u = new Proveedor(nickname, nombre, apellido, correo,FNacimiento, descripcion, link);
        mu.addUsuario(u);
    }

    public DTUsuario obtenerDTUsuarioDetalle(String nickname) {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario u = ins.getUsuario(nickname);
        return u.getDTUsuarioDetalle();
    }

    public void agregarSalida(SalidaTuristica st) {
    	//TODO: Implentarla
    }


}
