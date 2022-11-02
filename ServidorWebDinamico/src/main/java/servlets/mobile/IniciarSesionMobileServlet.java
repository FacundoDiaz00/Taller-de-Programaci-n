package servlets.mobile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excepciones.ContraseniaInvalidaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/IniciarSesionMobile")
public class IniciarSesionMobileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorUsuario contrU;

    public IniciarSesionMobileServlet() {
        super();
        contrU = Fabrica.getInstancia().getIControladorUsuario();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        HttpSession sesion = req.getSession(false);
        Object usr = sesion.getAttribute("usuarioLogeado");
        if (usr != null) {
            req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/mobile/iniciar_sesion.jsp").forward(req, resp);
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }
        String password = (String) req.getParameter("password");
        String email = (String) req.getParameter("email");
        String nickname = (String) req.getParameter("nickname");
        String tipoID = (String) req.getParameter("idForm");

        try {

            DTUsuario usuario;
            if (tipoID.equals("1")) {
                usuario = contrU.obtenerDTUsuarioPorEmail(email, password);
            } else {
                usuario = contrU.obtenerDTUsuarioPorNickname(nickname, password);
            }
            req.setAttribute("usuarioLogeado", usuario);
            HttpSession sesion = req.getSession(true);
            sesion.setAttribute("usuarioLogeado", usuario);
            req.getRequestDispatcher("/WEB-INF/jsp/mobile/bienvenida.jsp").forward(req, resp);

        } catch (ObjetoNoExisteEnTurismoUy e) {
            req.setAttribute("motivoDeError", "El usuario es incorrecto");
            req.getRequestDispatcher("/WEB-INF/jsp/mobile/iniciar_sesion.jsp").forward(req, resp);
        } catch (ContraseniaInvalidaException e) {
            req.setAttribute("motivoDeError", "La contrasenia es incorrecta");
            req.getRequestDispatcher("/WEB-INF/jsp/mobile/iniciar_sesion.jsp").forward(req, resp);
        }
    }

}