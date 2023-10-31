package excepciones;

public class CategoriaYaRegistradaException extends TurismoUyException {
    private static final long serialVersionUID = 1L;

    public CategoriaYaRegistradaException(String texto) {
        super(texto);
    }
}
