
package publicar.paqueteturisticasservice;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "CompraYaRegistradaException", targetNamespace = "http://paqueteTuristicasService.publicar/")
public class CompraYaRegistradaException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private CompraYaRegistradaException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public CompraYaRegistradaException_Exception(String message, CompraYaRegistradaException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public CompraYaRegistradaException_Exception(String message, CompraYaRegistradaException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: publicar.paqueteturisticasservice.CompraYaRegistradaException
     */
    public CompraYaRegistradaException getFaultInfo() {
        return faultInfo;
    }

}
