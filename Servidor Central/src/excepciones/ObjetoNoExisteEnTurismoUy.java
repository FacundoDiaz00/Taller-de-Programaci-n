package excepciones;

public class ObjetoNoExisteEnTurismoUy extends TurismoUyException {
	private static final long serialVersionUID = 1L;

	private String claseNombre;

	public ObjetoNoExisteEnTurismoUy(Class<?> clase) {
		super("Se intentó acceder a un " + clase.getSimpleName() + " inexistente.");
		this.claseNombre = clase.getSimpleName();
	}

	public String getClaseObjetoFaltante() {
		return claseNombre;
	}

}
