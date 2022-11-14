package publicar.maestroServices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import configuraciones.Cargador;
import excepciones.ObjetoNoExisteEnTurismoUy;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;
import logica.controladores.Fabrica;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaquete;
import logica.datatypes.DtResultadoBusqueda;
import logica.datatypes.Imagen;
import logica.datatypes.TipoOrdenacion;

@WebService @SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class WebServiceMaestro {

	private Endpoint endpoint = null;
	private Logger log;

	public WebServiceMaestro() {
		this.log = Logger.getLogger("logger");
	}

	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish(Cargador.getDireccionAHacerDeploy() + "/maestro", this);
		log.info("Servicios maestro publicado");
	}

	@WebMethod(exclude = true)
	public Endpoint getEndpoint() {
		return endpoint;
	}

	@WebMethod
	public byte[] getImg(String path) throws ObjetoNoExisteEnTurismoUy {
		log.info("Solicitud a 'getImg'");
		File imgFile = new File(Cargador.getDirectorioImagenes() + path);
		if (imgFile.exists()) {
			try {
				FileInputStream imgStream = new FileInputStream(imgFile);
				byte[] contenido = imgStream.readAllBytes();
				imgStream.close();
				return contenido;
			} catch (IOException e) {
				return new byte[0];
			}
		} else {
			throw new ObjetoNoExisteEnTurismoUy(Imagen.class);
		}
	}

	@WebMethod
	public DtResultadoBusqueda buscar(String filtro, String departamentoFiltro, String categoriaFiltrada,
			TipoOrdenacion tipoOrdenacion) {
		if (filtro != null && filtro.trim().length() == 0) {
			filtro = null;
		}
		if (departamentoFiltro != null && departamentoFiltro.trim().length() == 0) {
			departamentoFiltro = null;
		}
		if (categoriaFiltrada != null && categoriaFiltrada.trim().length() == 0) {
			categoriaFiltrada = null;
		}
		List<DTActividadTuristica> filtrarPorActividad = Fabrica.getInstancia().getIControladorActividadTuristica()
				.filtrarActividades(filtro, departamentoFiltro, categoriaFiltrada, tipoOrdenacion);
		List<DTPaquete> filtrarPaquete = Fabrica.getInstancia().getIControladorPaquete().filtrarPaquetes(filtro,
				categoriaFiltrada, tipoOrdenacion);

		return new DtResultadoBusqueda(filtrarPorActividad, filtrarPaquete);
	}

}
