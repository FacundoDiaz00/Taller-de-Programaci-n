package logica.controladores;

import logica.datatypes.DTUsuario;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorUsuario {
	public List<String> obtenerIdUsuarios();

	public DTUsuario obtenerDTUsuario(String nickname);
	
	public boolean altaTurista(String nickname, String nombre, String apellido, String correo,LocalDate FNacimiento, String nacionalidad);
	
	public boolean altaProveedor(String nickname,String nombre,String apellido,String correo,String descripcion,String link, LocalDate FNacimiento);
}
