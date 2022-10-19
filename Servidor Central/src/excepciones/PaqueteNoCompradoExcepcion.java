package excepciones;

public class PaqueteNoCompradoExcepcion extends TurismoUyException{
	private static final long serialVersionUID = 1L;
	
    public PaqueteNoCompradoExcepcion(String message) {
        super(message);
    }
}
