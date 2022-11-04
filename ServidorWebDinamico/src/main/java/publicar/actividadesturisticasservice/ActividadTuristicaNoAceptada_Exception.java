
package publicar.actividadesturisticasservice;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
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
     * @param cause
     * @param faultInfo
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
