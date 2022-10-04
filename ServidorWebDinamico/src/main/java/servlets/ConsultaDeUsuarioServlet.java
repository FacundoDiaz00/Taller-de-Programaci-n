package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaDeUsuarioServlet
 */

@WebServlet("/ConsultaDeUsuario")
public class ConsultaDeUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorUsuario contrUsuario;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaDeUsuarioServlet() {
		super();
		contrUsuario = Fabrica.getInstancia().getIControladorUsuario();
	}

	/**
	 * parametros posibles: - listar : indica si se listan los usuarios - id :
	 * nombre del usuario cuando se accede a la informacion del perfil
	 * 
	 * 
	 * Observacion: - Si listar = false debera haber una id para dar la info del
	 * usuario.
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String debelistar = (String) req.getParameter("listar");
		System.out.println("debelistar:" + debelistar);
		if (debelistar == "false") {
			System.out.println("dio false");
			req.getRequestDispatcher("/WEB-INF/jsp/perfil_de_usuario_externo.jsp").forward(req, res);
		} else {
			List<DTUsuario> usuarios = contrUsuario.obtenerDTUsuarios();
			req.setAttribute("usuarios", usuarios);
			req = Utiles.insertarLoDeSiempre(req);
			System.out.println("no false");
			req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_usuario.jsp").forward(req, res);
		}

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
