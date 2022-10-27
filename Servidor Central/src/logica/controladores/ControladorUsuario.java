package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import excepciones.ContraseniaInvalidaException;
import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.ObjetoNoExisteEnTurismoUy;
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

    public Turista obtenerTurista(String nickTur) throws ObjetoNoExisteEnTurismoUy {
        ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
        return (Turista) manUsr.getUsuarioPorNick(nickTur);
    }

    public Proveedor obtenerProveedor(String nickProv) throws ObjetoNoExisteEnTurismoUy {
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
    public void altaTurista(String nickname, String nombre, String apellido, String correo, String contra,
            LocalDate FNacimiento, Imagen img, String nacionalidad) throws UsuarioYaRegistradoException {
        ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
        if (manUsr.existeUsuario(nickname, correo)) {
            throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
        }

        Turista turista = new Turista(nickname, nombre, apellido, correo, contra, FNacimiento, img, nacionalidad);
        manUsr.addUsuario(turista);
    }

    @Override
    public void altaProveedor(String nickname, String nombre, String apellido, String correo, String contra,
            LocalDate FNacimiento, Imagen img, String descripcion, String link) throws UsuarioYaRegistradoException {
        ManejadorUsuario manUsr = ManejadorUsuario.getInstancia();
        if (manUsr.existeUsuario(nickname, correo)) {
            throw new UsuarioYaRegistradoException("El usuario " + nickname + " ya esta registrado");
        }
        Proveedor proveedor = new Proveedor(nickname, nombre, apellido, correo, contra, FNacimiento, img, descripcion,
                link);
        manUsr.addUsuario(proveedor);
    }

    @Override
    public DTUsuario obtenerDTUsuarioDetalle(String nickname) throws ObjetoNoExisteEnTurismoUy {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario usuario = ins.getUsuarioPorNick(nickname);
        return usuario.obtenerDTUsuarioDetalle();
    }

    @Override
    public DTUsuario obtenerDTUsuarioDetallePrivado(String nickname) throws ObjetoNoExisteEnTurismoUy {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario usuario = ins.getUsuarioPorNick(nickname);
        return usuario.obtenerDTUsuarioDetallePrivado();
    }

    @Override
    public DTUsuario obtenerDTUsuarioPorNickname(String nickname, String contrasenia)
            throws ObjetoNoExisteEnTurismoUy, ContraseniaInvalidaException {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario usuario = ins.getUsuarioPorNick(nickname);
        if (usuario.usuarioValido(contrasenia)) {
            return usuario.obtenerDTUsuario();
        } else {
            throw new ContraseniaInvalidaException("Se intento acceder a un usuario con la contrasenia incorrecta");
        }
    }

    public DTUsuario obtenerDTUsuarioPorEmail(String correo, String contrasenia)
            throws ObjetoNoExisteEnTurismoUy, ContraseniaInvalidaException {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario usuario = ins.getUsuarioPorCorreo(correo);
        if (usuario.usuarioValido(contrasenia)) {
            return usuario.obtenerDTUsuario();
        } else {
            throw new ContraseniaInvalidaException("Se intento acceder a un usuario con la contrasenia incorrecta");
        }
    }

    public DTUsuario obtenerDTUsuario(String nickname) throws ObjetoNoExisteEnTurismoUy {
        ManejadorUsuario ins = ManejadorUsuario.getInstancia();
        Usuario usuario = ins.getUsuarioPorNick(nickname);
        return usuario.obtenerDTUsuario();
    }

    @Override
    public List<DTUsuario> obtenerDTUsuarios() {
        var ret = new ArrayList<DTUsuario>();
        ManejadorUsuario.getInstancia().getUsuarios().forEach((Usuario u) -> ret.add(u.obtenerDTUsuario()));
        return ret;
    }

    @Override
    public void modificarUsuario(DTUsuario datosNuevos, String contrasenia, boolean borrarImg)
            throws ModificacionUsuarioNoPermitida, ObjetoNoExisteEnTurismoUy {

        ManejadorUsuario ins = ManejadorUsuario.getInstancia();

        try {
            Usuario u_nick = ins.getUsuarioPorNick(datosNuevos.getNickname());
            Usuario u_correo = ins.getUsuarioPorCorreo(datosNuevos.getCorreo());
            if (u_nick != null && u_correo != null && u_nick.equals(u_correo)) {
                u_nick.setearDatos(datosNuevos);
                if (contrasenia != null)
                	u_nick.setContrasenia(contrasenia);
                
                if (borrarImg) {
                	u_nick.setImagen(null);
                }
                
                
                
            } else {
                throw new ModificacionUsuarioNoPermitida(
                        "No coincide el nickname con el correo de este usuario. Estos dos valores no debe ser modificados.");
            }
        } catch (ObjetoNoExisteEnTurismoUy except) {
            throw new ModificacionUsuarioNoPermitida(
                    "No hay usuarios con ese nick o correo. Estos dos valores no debe ser modificados.");
        }

    }

	@Override
	public void seguirODejarDeSeguirUsuario(String nickSeguidor, String nickSeguido) throws ObjetoNoExisteEnTurismoUy {
		Usuario seguidorUsuario = ManejadorUsuario.getInstancia().getUsuarioPorNick(nickSeguidor);
		Usuario seguidoUsuario = ManejadorUsuario.getInstancia().getUsuarioPorNick(nickSeguido);
		
		seguidoUsuario.agregarOBorrarSeguidor(seguidorUsuario);
		seguidorUsuario.agregarOBorrarDeSeguidos(seguidoUsuario);
	}

	@Override
	public void agregarOEliminarActividadDeFavoritos(String nickTurista, String nombreAct)
			throws ObjetoNoExisteEnTurismoUy {
		Turista turista = (Turista) ManejadorUsuario.getInstancia().getUsuarioPorNick(nickTurista);
		turista.agregarOEliminarActividadDeFavoritos(nombreAct);	
	}

	@Override
	public boolean perteneceAFavoritosDeTurista(String nickTurista, String nombreAct) throws ObjetoNoExisteEnTurismoUy {
		Turista turista = (Turista) ManejadorUsuario.getInstancia().getUsuarioPorNick(nickTurista);
		return turista.estaEnActividadesFavoritas(nombreAct);
	}

}
