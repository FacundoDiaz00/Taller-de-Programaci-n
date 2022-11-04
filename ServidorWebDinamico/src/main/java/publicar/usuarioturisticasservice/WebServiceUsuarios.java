
package publicar.usuarioturisticasservice;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.FaultAction;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebService(name = "WebServiceUsuarios", targetNamespace = "http://usuarioTuristicasService.publicar/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebServiceUsuarios {


    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.usuarioturisticasservice.DtUsuarioDetallePorTipo
     * @throws ObjetoNoExisteEnTurismoUy_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuarioDetalleRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuarioDetalleResponse", fault = {
        @FaultAction(className = ObjetoNoExisteEnTurismoUy_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuarioDetalle/Fault/ObjetoNoExisteEnTurismoUy")
    })
    public DtUsuarioDetallePorTipo obtenerDTUsuarioDetalle(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ObjetoNoExisteEnTurismoUy_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.usuarioturisticasservice.DtUsuarioDetallePrivadoPorTipo
     * @throws ObjetoNoExisteEnTurismoUy_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuarioDetallePrivadoRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuarioDetallePrivadoResponse", fault = {
        @FaultAction(className = ObjetoNoExisteEnTurismoUy_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuarioDetallePrivado/Fault/ObjetoNoExisteEnTurismoUy")
    })
    public DtUsuarioDetallePrivadoPorTipo obtenerDTUsuarioDetallePrivado(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ObjetoNoExisteEnTurismoUy_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns publicar.usuarioturisticasservice.DtUsuarioPorTipo
     * @throws ContraseniaInvalidaException_Exception
     * @throws ObjetoNoExisteEnTurismoUy_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDtUsuarioPorEmailRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDtUsuarioPorEmailResponse", fault = {
        @FaultAction(className = ObjetoNoExisteEnTurismoUy_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDtUsuarioPorEmail/Fault/ObjetoNoExisteEnTurismoUy"),
        @FaultAction(className = ContraseniaInvalidaException_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDtUsuarioPorEmail/Fault/ContraseniaInvalidaException")
    })
    public DtUsuarioPorTipo obtenerDtUsuarioPorEmail(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws ContraseniaInvalidaException_Exception, ObjetoNoExisteEnTurismoUy_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @return
     *     returns publicar.usuarioturisticasservice.DtUsuarioPorTipo
     * @throws ContraseniaInvalidaException_Exception
     * @throws ObjetoNoExisteEnTurismoUy_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDtUsuarioPorNicknameRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDtUsuarioPorNicknameResponse", fault = {
        @FaultAction(className = ObjetoNoExisteEnTurismoUy_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDtUsuarioPorNickname/Fault/ObjetoNoExisteEnTurismoUy"),
        @FaultAction(className = ContraseniaInvalidaException_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDtUsuarioPorNickname/Fault/ContraseniaInvalidaException")
    })
    public DtUsuarioPorTipo obtenerDtUsuarioPorNickname(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws ContraseniaInvalidaException_Exception, ObjetoNoExisteEnTurismoUy_Exception
    ;

    /**
     * 
     * @return
     *     returns publicar.usuarioturisticasservice.DtUsuarioSeparadosPorTipoCollection
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuariosRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuariosResponse")
    public DtUsuarioSeparadosPorTipoCollection obtenerDTUsuarios();

    /**
     * 
     * @param arg0
     * @return
     *     returns publicar.usuarioturisticasservice.DtUsuarioPorTipo
     * @throws ObjetoNoExisteEnTurismoUy_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuarioRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuarioResponse", fault = {
        @FaultAction(className = ObjetoNoExisteEnTurismoUy_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/obtenerDTUsuario/Fault/ObjetoNoExisteEnTurismoUy")
    })
    public DtUsuarioPorTipo obtenerDTUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws ObjetoNoExisteEnTurismoUy_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @param arg8
     * @throws ErrorAlProcesar_Exception
     * @throws UsuarioYaRegistradoException_Exception
     */
    @WebMethod
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/altaTuristaRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/altaTuristaResponse", fault = {
        @FaultAction(className = UsuarioYaRegistradoException_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/altaTurista/Fault/UsuarioYaRegistradoException"),
        @FaultAction(className = ErrorAlProcesar_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/altaTurista/Fault/ErrorAlProcesar")
    })
    public void altaTurista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        byte[] arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String arg8)
        throws ErrorAlProcesar_Exception, UsuarioYaRegistradoException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @param arg3
     * @param arg4
     * @param arg5
     * @param arg6
     * @param arg7
     * @param arg8
     * @param arg9
     * @throws ErrorAlProcesar_Exception
     * @throws UsuarioYaRegistradoException_Exception
     */
    @WebMethod
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/altaProveedorRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/altaProveedorResponse", fault = {
        @FaultAction(className = UsuarioYaRegistradoException_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/altaProveedor/Fault/UsuarioYaRegistradoException"),
        @FaultAction(className = ErrorAlProcesar_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/altaProveedor/Fault/ErrorAlProcesar")
    })
    public void altaProveedor(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        byte[] arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String arg8,
        @WebParam(name = "arg9", partName = "arg9")
        String arg9)
        throws ErrorAlProcesar_Exception, UsuarioYaRegistradoException_Exception
    ;

    /**
     * 
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws ModificacionUsuarioNoPermitida_Exception
     * @throws ObjetoNoExisteEnTurismoUy_Exception
     */
    @WebMethod
    @Action(input = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/modificarUsuarioRequest", output = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/modificarUsuarioResponse", fault = {
        @FaultAction(className = ModificacionUsuarioNoPermitida_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/modificarUsuario/Fault/ModificacionUsuarioNoPermitida"),
        @FaultAction(className = ObjetoNoExisteEnTurismoUy_Exception.class, value = "http://usuarioTuristicasService.publicar/WebServiceUsuarios/modificarUsuario/Fault/ObjetoNoExisteEnTurismoUy")
    })
    public void modificarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        DtUsuarioPorTipo arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        boolean arg2)
        throws ModificacionUsuarioNoPermitida_Exception, ObjetoNoExisteEnTurismoUy_Exception
    ;

}
