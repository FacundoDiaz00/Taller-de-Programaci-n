package excepciones;

/**
 * Exepcion utilizada para indicar que ya existe registrado en el sistema un
 * Usuario con el identificador seleccionado.
 *
 */
public class ContraseniaInvalidaException extends TurismoUyException {
	private static final long serialVersionUID = 1L;
	public ContraseniaInvalidaException(String texto) {
		super(texto);
	}

}
