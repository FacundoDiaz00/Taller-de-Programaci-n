package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.datatypes.DTPaqueteDetalles;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/ConsultaPaquete")
public class ConsultaPaqueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultaPaqueteServlet() {
		super();
	}

	/**
	 * parametros posibles: - String id -> nombre del paquete consultado
	 * 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String identificador = (String) req.getParameter("id");
			DTPaqueteDetalles paquete = Fabrica.getInstancia().getIControladorPaquete().obtenerDTPaqueteDetalle(identificador);
			req.setAttribute("paquete", paquete);

			req = Utiles.insertarLoDeSiempre(req);

			req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_paquete.jsp").forward(req, resp);
		} catch (ObjetoNoExisteEnTurismoUy e) {
			req.setAttribute("motivoDeError", "No existe un paquete con ese nombre");
			req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
		}

	}
}
