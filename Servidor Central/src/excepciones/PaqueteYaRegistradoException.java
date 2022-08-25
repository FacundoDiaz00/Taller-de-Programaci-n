package excepciones;

/**
 * Excepcion utilizada para indicar que un paquete ya existe en el sistema (nombre repetido)
 *
 *   @author Equipo taller prog 16

 */
public class PaqueteYaRegistradoException extends Exception{
	
	public PaqueteYaRegistradoException(String texto){
        super(texto);
	}
    
}
