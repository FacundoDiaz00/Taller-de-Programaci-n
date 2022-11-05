
package publicar.actividadesturisticasservice;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ActividadTuristicaNoAceptada", targetNamespace = "http://actividadesTuristicasService.publicar/")
public class ActividadTuristicaNoAceptada_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ActividadTuristicaNoAceptada faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ActividadTuristicaNoAceptada_Exception(String message, ActividadTuristicaNoAceptada faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ActividadTuristicaNoAceptada_Exception(String message, ActividadTuristicaNoAceptada faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: publicar.actividadesturisticasservice.ActividadTuristicaNoAceptada
     */
    public ActividadTuristicaNoAceptada getFaultInfo() {
        return faultInfo;
    }

}
