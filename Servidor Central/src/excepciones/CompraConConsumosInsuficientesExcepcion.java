package excepciones;

public class CompraConConsumosInsuficientesExcepcion extends TurismoUyException{
	
	private static final long serialVersionUID = 1L;
	
    public CompraConConsumosInsuficientesExcepcion(String message) {
        super(message);
    }
}
