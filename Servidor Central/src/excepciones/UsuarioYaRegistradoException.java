package excepciones;

/**
 * Exepcion utilizada para indicar que ya existe registrado en el sistema un
 * Usuario con el identificador seleccionado.
 *
 */
public class UsuarioYaRegistradoException extends TurismoUyException {
	public UsuarioYaRegistradoException(String texto) {
		super(texto);
	}

}
