package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.TurismoUyException;
import logica.controladores.Fabrica;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/home")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Fabrica.getInstancia().getIControladorMaestro().generarDatosDePrueba();
		} catch (TurismoUyException e) {
			e.printStackTrace();
		}
		var departamentos = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdDepartamentos();

		req.setAttribute("departamentos", departamentos);

		req.getRequestDispatcher("/jsps/index.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
