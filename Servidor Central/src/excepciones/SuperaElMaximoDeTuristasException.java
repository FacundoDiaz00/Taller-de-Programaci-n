package excepciones;

/**
 * Excepcion utilizada para indicar que ya existe una inscrpcion entre un
 * usuario y una salida dada
 *
 * @author Equipo taller prog 16
 * 
 */
public class SuperaElMaximoDeTuristasException extends Exception {
	public SuperaElMaximoDeTuristasException(String message) {
		super(message);
	}
}
