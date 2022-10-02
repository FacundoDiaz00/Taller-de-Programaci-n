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
    private IControladorUsuario cu;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaDeUsuarioServlet() {
        super();
        cu = Fabrica.getInstancia().getIControladorUsuario();
    }


    /**
	 * parametros posibles:
	 * 	- listar : se listan los usuarios/
	 * 	- id : nombre del usuario cuando se accede a la informacion del perfil
	 * 
	 * 
	 * Observacion: 
	 * 	- Si listar = false debera haber una id para dar la info del usuario.
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		List<DTUsuario> usuarios =  cu.obtenerDTUsuarios();
		req.setAttribute("usuarios", usuarios);
		req = Utiles.insertarLoDeSiempre(req);
		req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_usuario.jsp").forward(req, res);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
