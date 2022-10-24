package excepciones;

public class NoExisteConsumoParaLaActividadExcepcion extends TurismoUyException{
	private static final long serialVersionUID = 1L;
	
    public NoExisteConsumoParaLaActividadExcepcion(String message) {
        super(message);
    }
}
