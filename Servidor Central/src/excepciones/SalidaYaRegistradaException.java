package excepciones;

/**
 * Excepcion utilizada para indicar que una salida ya existe en el sistema
 * (nombre repetido)
 *
 * @author Equipo taller prog 16
 * 
 */
public class SalidaYaRegistradaException extends TurismoUyException {
	public SalidaYaRegistradaException(String texto) {
		super(texto);
	}

}
