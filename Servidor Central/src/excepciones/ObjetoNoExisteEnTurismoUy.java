package excepciones;

public class ObjetoNoExisteEnTurismoUy extends TurismoUyException {
	private static final long serialVersionUID = 1L;

	public ObjetoNoExisteEnTurismoUy(Class<?> clase) {
		super("Se intentó acceder a un " + clase.getName() + " inexistente.");
	}

}
