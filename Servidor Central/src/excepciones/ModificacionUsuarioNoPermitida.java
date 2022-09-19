package excepciones;

public class ModificacionUsuarioNoPermitida extends Exception {
	public ModificacionUsuarioNoPermitida(String message) {
		super(message);
	}
}
