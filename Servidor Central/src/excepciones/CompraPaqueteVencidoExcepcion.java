package excepciones;

public class CompraPaqueteVencidoExcepcion extends TurismoUyException{
	private static final long serialVersionUID = 1L;
	
    public CompraPaqueteVencidoExcepcion(String message) {
        super(message);
    }
}
