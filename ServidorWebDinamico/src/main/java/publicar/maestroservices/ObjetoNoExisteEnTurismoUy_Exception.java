
package publicar.maestroservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ObjetoNoExisteEnTurismoUy", targetNamespace = "http://maestroServices.publicar/")
public class ObjetoNoExisteEnTurismoUy_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ObjetoNoExisteEnTurismoUy faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ObjetoNoExisteEnTurismoUy_Exception(String message, ObjetoNoExisteEnTurismoUy faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ObjetoNoExisteEnTurismoUy_Exception(String message, ObjetoNoExisteEnTurismoUy faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: publicar.maestroservices.ObjetoNoExisteEnTurismoUy
     */
    public ObjetoNoExisteEnTurismoUy getFaultInfo() {
        return faultInfo;
    }

}
