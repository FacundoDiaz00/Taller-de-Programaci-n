package logica.controladores;

import java.time.LocalDate;
import java.util.List;

import excepciones.ContraseniaInvalidaException;
import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.ObjetoNoExisteEnTurismoUy;
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
     * @throws ObjetoNoExisteEnTurismoUy
     * @throws ContraseniaInvalidaException
     */
    DTUsuario obtenerDTUsuarioPorNickname(String nickname, String contrasenia)
            throws ObjetoNoExisteEnTurismoUy, ContraseniaInvalidaException;

    DTUsuario obtenerDTUsuarioPorEmail(String correo, String contrasenia)
            throws ObjetoNoExisteEnTurismoUy, ContraseniaInvalidaException;

    List<DTUsuario> obtenerDTUsuarios();

    /**
     * Devuelve los detalles del usuario identificado por parámetro.
     * 
     * @param nickname
     * @return
     * @throws ObjetoNoExisteEnTurismoUy
     */
    DTUsuario obtenerDTUsuarioDetalle(String nickname) throws ObjetoNoExisteEnTurismoUy;

    public DTUsuario obtenerDTUsuario(String nickname) throws ObjetoNoExisteEnTurismoUy;

    DTUsuario obtenerDTUsuarioDetallePrivado(String nickname) throws ObjetoNoExisteEnTurismoUy;

    /**
     * Crea un Turista con los parámetros enviados.
     * 
     * @param nickname
     * @param nombre
     * @param apellido
     * @param correo
     * @param
     * @param contra
     * @param FNacimiento
     * @param nacionalidad
     * @throws UsuarioYaRegistradoException
     */
    void altaTurista(String nickname, String nombre, String apellido, String correo, String contra,
            LocalDate FNacimiento, Imagen img,
            String nacionalidad) throws UsuarioYaRegistradoException;

    /**
     * Crea un Proveedor con los parámetros enviados.
     * 
     * @param nickname
     * @param nombre
     * @param apellido
     * @param correo
     * @param contra
     * @param descripcion
     * @param link
     * @param FNacimiento
     * @throws UsuarioYaRegistradoException
     */
    void altaProveedor(String nickname, String nombre, String apellido, String correo, String contra,
            LocalDate FNacimiento,
            Imagen img, String descripcion, String link) throws UsuarioYaRegistradoException;

    /**
     * Modifica los datos del usuario, no se puede modificar el correo ni el
     * nickname.
     * 
     * @param datosNuevos
     * @throws ModificacionUsuarioNoPermitida
     * @throws ObjetoNoExisteEnTurismoUy
     */


	void modificarUsuario(DTUsuario datosNuevos, String contrasenia, Boolean borrarImg)
			throws ModificacionUsuarioNoPermitida, ObjetoNoExisteEnTurismoUy;

}
