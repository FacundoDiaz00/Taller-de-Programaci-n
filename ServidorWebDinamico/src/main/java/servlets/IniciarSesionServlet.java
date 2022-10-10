package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;

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
		String nickname = (String) req.getParameter("nickname");
		String tipoID = (String) req.getParameter("idForm");

		try {
			System.out.print(email);
			System.out.print(password);
			System.out.print(nickname);
			System.out.print(tipoID);
			DTUsuario usuario;
			if (tipoID.equals("1")) {
				usuario = contrU.obtenerDTUsuarioPorEmail(email, password);
			}else {
				usuario = contrU.obtenerDTUsuarioPorNickname(nickname, password);
			}
			req.setAttribute("usuarioLogeado", usuario);
			req = Utiles.insertarLoDeSiempre(req);
			req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
			return;

		} catch (ObjetoNoExisteEnTurismoUy e) {
			// TODO HACER ALGO
			System.out.print("Usuario no existe");
			req.setAttribute("motivoDeError", "El usuario o la contraseña son incorrectos");
			req.getRequestDispatcher("/WEB-INF/jsp/iniciar_sesion.jsp").forward(req, resp);
		}

		// req = Utiles.insertarLoDeSiempre(req);
		// req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req,
		// resp);
	}

}