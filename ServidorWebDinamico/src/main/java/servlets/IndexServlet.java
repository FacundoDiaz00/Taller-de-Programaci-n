package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorActividadTuristica contrAct;

	public IndexServlet() {
		super();
		contrAct = Fabrica.getInstancia().getIControladorActividadTuristica();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var departamentos = contrAct.obtenerIdDepartamentos();
		var actividades = contrAct.obtenerDTActividadesTuristicas();

		req.setAttribute("departamentos", departamentos);
		req.setAttribute("actividades", actividades);

		req.getRequestDispatcher("/jsps/index.jsp").forward(req, resp);
	}

}
