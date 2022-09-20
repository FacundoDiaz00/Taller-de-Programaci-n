package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.controladores.Fabrica;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public IndexServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var departamentos = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdDepartamentos();

		req.setAttribute("departamentos", departamentos);

		req.getRequestDispatcher("/jsps/index.jsp").forward(req, resp);
	}

}
