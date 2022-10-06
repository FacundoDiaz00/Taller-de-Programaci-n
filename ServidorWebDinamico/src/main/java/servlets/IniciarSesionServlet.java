package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/IniciarSesion")
public class IniciarSesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorUsuario contrU;

	public IniciarSesionServlet() {
		super();
		contrU = Fabrica.getInstancia().getIControladorUsuario();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.printf("req: ", req.getParameter("email"), "\n resp: ", resp);
		req.getRequestDispatcher("/WEB-INF/jsp/iniciar_sesion.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password = (String) req.getParameter("password");
		String email = (String) req.getParameter("email");

		try {
			DTUsuario usuario = contrU.obtenerDTUsuario(email);
			req.setAttribute("usuarioLogeado", usuario);
			req = Utiles.insertarLoDeSiempre(req);
			resp.sendRedirect("index");
			return;

		} catch (ObjetoNoExisteEnTurismoUy e) {
			// TODO HACER ALGO
		}

		// req = Utiles.insertarLoDeSiempre(req);
		// req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req,
		// resp);
	}

}