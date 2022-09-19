package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.UsuarioYaRegistradoException;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
import logica.entidades.Proveedor;
import logica.entidades.Turista;
import logica.entidades.Usuario;
import logica.manejadores.ManejadorUsuario;

/**
 * @author Equipo taller prog 16
 */
public class ControladorUsuario implements IControladorUsuario {

	public ControladorUsuario() {
	}

	public Turista obtenerTurista(String nickTur) {
		ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
		return (Turista) manUsr.getUsuarioPorNick(nickTur);
	}

	public Proveedor obtenerProveedor(String nickProv) {
		ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
		return (Proveedor) manUsr.getUsuarioPorNick(nickProv);
	}

	public List<String> obtenerIdUsuarios() {
		ManejadorUsuario ins = ManejadorUsuario.getInstancia();
		return new ArrayList<String>(ins.obtenerNickUsuarios());
	}

	@Override
	public ArrayList<String> obtenerIdProveedores() {
		ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
		List<Usuario> usuarios = manUsr.getUsuarios();
		ArrayList<String> res = new ArrayList<String>();
		for (Usuario user : usuarios) {
			if (user instanceof Proveedor) {
				res.add(user.getNickname());
			}
		}
		return res;
	}

	@Override
	public List<String> obtenerIdTuristas() {
		ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
		List<Usuario> usuarios = manUsr.getUsuarios();
		ArrayList<String> res = new ArrayList<String>();
		for (Usuario user : usuarios) {
			if (user instanceof Turista) {
				res.add(user.getNickname());
			}
		}
		return res;
	}

	@Override
	public void altaTurista(String nickname, String nombre, String apellido, String correo, LocalDate FNacimiento,
			Imagen img, String nacionalidad) throws UsuarioYaRegistradoException {
		ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
		if (manUsr.existeUsuario(nickname, correo)) {
			throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
		}

		Turista turista = new Turista(nickname, nombre, apellido, correo, FNacimiento, img, nacionalidad);
		manUsr.addUsuario(turista);
	}

	@Override
	public void altaProveedor(String nickname, String nombre, String apellido, String correo, LocalDate FNacimiento,
			Imagen img, String descripcion, String link) throws UsuarioYaRegistradoException {
		ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
		if (manUsr.existeUsuario(nickname, correo)) {
			throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
		}
		Proveedor proveedor = new Proveedor(nickname, nombre, apellido, correo, FNacimiento, img, descripcion, link);
		manUsr.addUsuario(proveedor);
	}

	@Override
	public DTUsuario obtenerDTUsuarioDetalle(String nickname) {
		ManejadorUsuario ins = ManejadorUsuario.getInstancia();
		Usuario usuario = ins.getUsuarioPorNick(nickname);
		return usuario.obtenerDTUsuarioDetalle();
	}

	@Override
	public DTUsuario obtenerDTUsuarioDetallePrivado(String nickname) {
		ManejadorUsuario ins = ManejadorUsuario.getInstancia();
		Usuario usuario = ins.getUsuarioPorNick(nickname);
		return usuario.obtenerDTUsuarioDetallePrivado();
	}

	@Override
	public DTUsuario obtenerDTUsuario(String nickname) {
		ManejadorUsuario ins = ManejadorUsuario.getInstancia();
		Usuario usuario = ins.getUsuarioPorNick(nickname);
		return usuario.obtenerDTUsuario();
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
			throw new ModificacionUsuarioNoPermitida(
					"No coincide el nickname con el correo de este usuario. Estos dos valores no debe ser modificados.");
		}
	}

}
