package excepciones;

/**
 * Excepcion utilizada para indicar que ya existe una inscrpcion entre un
 * usuario y una salida dada
 *
 * @author Equipo taller prog 16
 * 
 */

public class InscripcionYaRegistradaException extends TurismoUyException {
	public InscripcionYaRegistradaException(String message) {
		super(message);
	}
}
