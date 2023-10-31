package excepciones;

public class ModificacionUsuarioNoPermitida extends TurismoUyException {
	private static final long serialVersionUID = 1L;
	
	public ModificacionUsuarioNoPermitida(String message) {
		super(message);
	}
}
