package excepciones;

/**
 * Excepcion utilizada para indicar que una Actividad turistica ya existe en el
 * sistema
 *
 * @author Equipo taller prog 16
 * 
 */
public class ActividadTuristicaNoAceptada extends TurismoUyException {
	private static final long serialVersionUID = 1L;

	public ActividadTuristicaNoAceptada(String message) {
		super(message);
	}
}
