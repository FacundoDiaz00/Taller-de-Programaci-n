package logica.controladores;

import java.util.ArrayList;
import java.util.Collection;

import excepciones.UsuarioYaRegistradoException;
import logica.datatypes.DTUsuario;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorUsuario {
	List<String> obtenerIdUsuarios();

	DTUsuario obtenerDTUsuario(String nickname);
	DTUsuario obtenerDTUsuarioDetalle(String nickname);
	
	void altaTurista(String nickname, String nombre, String apellido, String correo,LocalDate FNacimiento, String nacionalidad) throws UsuarioYaRegistradoException;
	void altaProveedor(String nickname,String nombre,String apellido,String correo,String descripcion,String link, LocalDate FNacimiento) throws UsuarioYaRegistradoException;
		
	
	void modificarUsuario(DTUsuario datosNuevos);
}
