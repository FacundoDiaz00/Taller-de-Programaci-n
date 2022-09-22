package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/ConsultaActividadServlet")
public class ConsultaActividadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConsultaActividadServlet() {
		super();
	}

	/**
	 * parametros posibles: - idActividad
	 * 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		var id = req.getParameter("id");

		System.out.println(id);

		req.getRequestDispatcher("/jsps/consulta_actividad_turistica.jsp").forward(req, response);
	}
}
