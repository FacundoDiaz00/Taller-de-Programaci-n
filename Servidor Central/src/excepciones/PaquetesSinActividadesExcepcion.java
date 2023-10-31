package excepciones;

public class PaquetesSinActividadesExcepcion extends TurismoUyException{
	private static final long serialVersionUID = 1L;
	
    public PaquetesSinActividadesExcepcion(String message) {
        super(message);
    }
}
