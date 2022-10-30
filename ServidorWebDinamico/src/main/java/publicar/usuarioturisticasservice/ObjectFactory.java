
package publicar.usuarioturisticasservice;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicar.usuarioturisticasservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar.usuarioturisticasservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link Imagen }
     * 
     * @return
     *     the new instance of {@link Imagen }
     */
    public Imagen createImagen() {
        return new Imagen();
    }

    /**
     * Create an instance of {@link DtUsuarioCollection }
     * 
     * @return
     *     the new instance of {@link DtUsuarioCollection }
     */
    public DtUsuarioCollection createDtUsuarioCollection() {
        return new DtUsuarioCollection();
    }

}
