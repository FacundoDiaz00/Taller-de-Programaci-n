package logica.controladores;

import logica.datatypes.DTUsuario;

import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorUsuario {
	public List<String> obtenerIdUsuarios();

	public DTUsuario obtenerDTUsuario(String nickname);
}
