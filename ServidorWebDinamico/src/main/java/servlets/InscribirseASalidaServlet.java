package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.actividadesturisticasservice.AltaInscripcionPosteriorAFechaSalidaException_Exception;
import publicar.actividadesturisticasservice.CompraConConsumosInsuficientesExcepcion_Exception;
import publicar.actividadesturisticasservice.CompraPaqueteVencidoExcepcion_Exception;
import publicar.actividadesturisticasservice.DtSalidaTuristica;
import publicar.actividadesturisticasservice.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion_Exception;
import publicar.actividadesturisticasservice.InscripcionYaRegistradaException_Exception;
import publicar.actividadesturisticasservice.NoExisteConsumoParaLaActividadExcepcion_Exception;
import publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.actividadesturisticasservice.PaqueteNoCompradoExcepcion_Exception;
import publicar.actividadesturisticasservice.SuperaElMaximoDeTuristasException_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.usuarioturisticasservice.DtTurista;
import utils.Utile;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/InscribiseASalida")
public class InscribirseASalidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebServiceActividades wbActi;
	private DtSalidaTuristica salida = null;

	public InscribirseASalidaServlet() {
		super();
		wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getCharacterEncoding() == null) {
			req.setCharacterEncoding("UTF-8");
		}

		String nombSalida = (String) req.getParameter("id");

		if (nombSalida == null || nombSalida.trim().length() == 0) {
			req.setAttribute("motivoDeError", "No se ha incluido el id de la salida en los parámetros");
			req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
		}

		try {

			salida = wbActi.obtenerDTSalidaTuristica(nombSalida);
			req.setAttribute("salida", salida);
			req = Utile.insertarLoDeSiempre(req);
			DtTurista turi = (DtTurista) req.getSession().getAttribute("usuarioLogeado");
			String nickTuri = "";
			if (turi != null) {
				nickTuri = turi.getNickname();
			} else {
				req = Utile.insertarLoDeSiempre(req);
				resp.sendRedirect("index");
				return;
			}

			List<String> paquetes = wbActi.obtenerIdComprasDisponiblesParaInscripcion(salida.getActividad(), nickTuri)
					.getItem();
			req.setAttribute("paquetes", paquetes);

		} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
			req.setAttribute("motivoDeError", "No existe la salida turistica");
			req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
			return;
		} catch (ClassCastException e) {
			req.setAttribute("motivoDeError", "El usuario logueado no es proveedor");
			req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
			return;
		}

		req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getCharacterEncoding() == null) {
			req.setCharacterEncoding("UTF-8");
		}

		String formaDePago = (String) req.getParameter("formaPago");
		int cantTuristas = Integer.parseInt(req.getParameter("cantTuristas"));
		DtTurista turi = (DtTurista) req.getSession().getAttribute("usuarioLogeado");
		String nickTuri = "";
		if (turi != null && turi instanceof DtTurista) {
			nickTuri = turi.getNickname();
		} else {
			req = Utile.insertarLoDeSiempre(req);
			resp.sendRedirect("index");
			return;
		}

		String nombrePaquete = "";
		if (formaDePago.equals("1")) {
			nombrePaquete = (String) req.getParameter("paquete");
		}

		try {
			List<String> paquetes = wbActi.obtenerIdComprasDisponiblesParaInscripcion(salida.getActividad(), nickTuri)
					.getItem();
			req.setAttribute("paquetes", paquetes);
		} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
			req.setAttribute("motivoDeError", "El paquete ingresado para la inscripción no existe");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		}

		req = Utile.insertarLoDeSiempre(req);
		req.setAttribute("salida", this.salida);

		try {
			wbActi.altaInscripcionSalidaTuristica(salida.getNombre(), nickTuri, cantTuristas, nombrePaquete);

			req.setAttribute("exito", Boolean.TRUE);
			req = Utile.insertarLoDeSiempre(req);
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (InscripcionYaRegistradaException_Exception e) {
			req.setAttribute("motivoDeError", "Usuario ya inscripto para esta salida turística");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (SuperaElMaximoDeTuristasException_Exception e) {
			req.setAttribute("motivoDeError", "La cantidad de turistas ingresadas supera el máximo");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (FechaAltaSalidaTuristicaPosteriorAFechaInscripcion_Exception e) {
			req.setAttribute("motivoDeError",
					"La fecha de la salida turística no puede ser posterior a la fecha de inscripción");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (AltaInscripcionPosteriorAFechaSalidaException_Exception e) {
			req.setAttribute("motivoDeError",
					"La fecha de la inscripción no puede ser posterior a la fecha de la salida asociada");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (PaqueteNoCompradoExcepcion_Exception e) {
			req.setAttribute("motivoDeError", "Se debe comprar el paquete para poder inscribirse a la salida asociada");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (CompraPaqueteVencidoExcepcion_Exception e) {
			req.setAttribute("motivoDeError", "La compra asociada a la salida turistica se encuentra vencida");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (CompraConConsumosInsuficientesExcepcion_Exception e) {
			req.setAttribute("motivoDeError",
					"La compra asociada a la salida turisitica se encuentra sin consumos disponibles");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (NoExisteConsumoParaLaActividadExcepcion_Exception e) {
			req.setAttribute("motivoDeError",
					"No existe ningún consumo para la actividad asociada a la salida seleccionada");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
			req.setAttribute("motivoDeError", "No existe la salida a la que se desea inscribir");
			req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
		}

	}

}