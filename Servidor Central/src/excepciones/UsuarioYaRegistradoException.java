package excepciones;

import java.lang.reflect.Executable;

/**
 * Exepcion utilizada para indicar que ya existe registrado en el sistema un Usuario con el identificador seleccionado.
 *
 */
public class UsuarioYaRegistradoException extends Exception {
    public UsuarioYaRegistradoException(String texto){
        super(texto);
    }

}
