package excepciones;

/**
 * Excepcion utilizada para indicar que una Actividad turistica ya existe en el
 * sistema
 *
 * @author Equipo taller prog 16
 * 
 */
public class ActividadTuristicaYaRegistradaException extends Exception {
	public ActividadTuristicaYaRegistradaException(String message) {
		super(message);
	}
}
