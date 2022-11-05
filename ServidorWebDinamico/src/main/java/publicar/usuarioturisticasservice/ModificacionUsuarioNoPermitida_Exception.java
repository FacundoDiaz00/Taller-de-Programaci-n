
package publicar.usuarioturisticasservice;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ModificacionUsuarioNoPermitida", targetNamespace = "http://usuarioTuristicasService.publicar/")
public class ModificacionUsuarioNoPermitida_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ModificacionUsuarioNoPermitida faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ModificacionUsuarioNoPermitida_Exception(String message, ModificacionUsuarioNoPermitida faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ModificacionUsuarioNoPermitida_Exception(String message, ModificacionUsuarioNoPermitida faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: publicar.usuarioturisticasservice.ModificacionUsuarioNoPermitida
     */
    public ModificacionUsuarioNoPermitida getFaultInfo() {
        return faultInfo;
    }

}
