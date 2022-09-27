package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.CompraYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/CompraPaquete")
public class CompraPaqueteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompraPaqueteServlet() {
		super();
	}

	/**
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int cant_turistas = Integer.valueOf(req.getParameter("cant_turistas"));
		String nombre_paquete = (String) req.getParameter("nombre_paquete");

		String nickUsuarioLogueado = ""; // TODO: obtener este dato

		try {
			Fabrica.getInstancia().getIControladorPaquete().comprarPaquete(nickUsuarioLogueado, nombre_paquete,
					cant_turistas);
		} catch (ObjetoNoExisteEnTurismoUy e) {
			String objetoFaltante = e.getClaseObjetoFaltante();
			req.setAttribute("motivoDeError", "No existe un " + objetoFaltante + " con ese nombre.");
			req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
		} catch (CompraYaRegistradaException e) {
			req.setAttribute("motivoDeError", "El usuario logueado ya ha comprado este mismo paquete.");
			req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
		}

		resp.sendRedirect("index");
	}
}
