package excepciones;

public class ModificacionUsuarioNoPermitida extends TurismoUyException {
	public ModificacionUsuarioNoPermitida(String message) {
		super(message);
	}
}
