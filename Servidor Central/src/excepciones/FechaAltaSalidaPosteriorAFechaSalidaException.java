package excepciones;

/**
 * Excepcion utilizada para indicar que la fecha ingresada es invalida
 *
 * @author Equipo taller prog 16
 * 
 */
public class FechaAltaSalidaPosteriorAFechaSalidaException extends TurismoUyException {
	public FechaAltaSalidaPosteriorAFechaSalidaException(String message) {
		super(message);
	}

}
