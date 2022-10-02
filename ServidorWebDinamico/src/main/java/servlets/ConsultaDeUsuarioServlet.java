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
 * Servlet implementation class ConsultaDeUsuarioServlet
 */
@WebServlet("/ConsultaDeUsuarioServlet")
public class ConsultaDeUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IControladorUsuario cu;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaDeUsuarioServlet() {
        super();
        cu = Fabrica.getInstancia().getIControladorUsuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.getWriter().append("Served at: ").append(req.getContextPath());
		req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_usuario.jsp").forward(req, res);}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
