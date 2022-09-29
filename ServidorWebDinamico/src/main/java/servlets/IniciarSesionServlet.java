package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;


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
		

		req.getRequestDispatcher("/WEB-INF/jsp/iniciar_sesion.jsp").forward(req, resp);
	}

		

}