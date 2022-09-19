package logica.controladores;

import java.time.LocalDate;
import java.util.List;

import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.UsuarioYaRegistradoException;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorUsuario {
	/**
	 * Devuelve el nombre de todos los usuarios registrados en el sistema.
	 * 
	 * @return
	 */
	List<String> obtenerIdUsuarios();

	/**
	 * Devuelve los nombres de todos los proveedores registrados en el sistema
	 * 
	 * @return
	 */
	List<String> obtenerIdProveedores();

	/**
	 * Devuelve los nombres de todos los turistas registrados en el sistema
	 * 
	 * @return
	 */
	List<String> obtenerIdTuristas();

	/**
	 * Devuelve los datos del usuario especificado por parámetro.
	 * 
	 * @param nickname
	 * @return
	 */
	DTUsuario obtenerDTUsuario(String nickname);

	List<DTUsuario> obtenerDTUsuarios();

	/**
	 * Devuelve los detalles del usuario identificado por parámetro.
	 * 
	 * @param nickname
	 * @return
	 */
	DTUsuario obtenerDTUsuarioDetalle(String nickname);

	DTUsuario obtenerDTUsuarioDetallePrivado(String nickname);

	/**
	 * Crea un Turista con los parámetros enviados.
	 * 
	 * @param nickname
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @param FNacimiento
	 * @param nacionalidad
	 * @throws UsuarioYaRegistradoException
	 */
	void altaTurista(String nickname, String nombre, String apellido, String correo, LocalDate FNacimiento, Imagen img,
			String nacionalidad) throws UsuarioYaRegistradoException;

	/**
	 * Crea un Proveedor con los parámetros enviados.
	 * 
	 * @param nickname
	 * @param nombre
	 * @param apellido
	 * @param correo
	 * @param descripcion
	 * @param link
	 * @param FNacimiento
	 * @throws UsuarioYaRegistradoException
	 */
	void altaProveedor(String nickname, String nombre, String apellido, String correo, LocalDate FNacimiento,
			Imagen img, String descripcion, String link) throws UsuarioYaRegistradoException;

	/**
	 * Modifica los datos del usuario, no se puede modificar el correo ni el
	 * nickname.
	 * 
	 * @param datosNuevos
	 * @throws ModificacionUsuarioNoPermitida
	 */
	void modificarUsuario(DTUsuario datosNuevos) throws ModificacionUsuarioNoPermitida;

}
