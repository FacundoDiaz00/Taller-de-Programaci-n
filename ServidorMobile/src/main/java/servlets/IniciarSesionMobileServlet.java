package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publicar.usuarioturisticasservice.DtUsuario;
import publicar.usuarioturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.usuarioturisticasservice.ContraseniaInvalidaException_Exception;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/IniciarSesionMobile")
public class IniciarSesionMobileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceUsuarios wbUser;


    public IniciarSesionMobileServlet() {
        super();
        wbUser = new WebServiceUsuariosService().getWebServiceUsuariosPort();
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

            DtUsuario usuario;
            if (tipoID.equals("1")) {
                usuario = wbUser.obtenerDtUsuarioPorEmail(email, password);
            } else {
                usuario = wbUser.obtenerDtUsuarioPorNickname(nickname, password);
            }            
            
            req.setAttribute("usuarioLogeado", usuario);
            HttpSession sesion = req.getSession(true);
            sesion.setAttribute("usuarioLogeado", usuario);
            resp.sendRedirect("index");

        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
            req.setAttribute("motivoDeError", "El usuario es incorrecto");
            req.getRequestDispatcher("/WEB-INF/jsp/iniciar_sesion.jsp").forward(req, resp);
        } catch (ContraseniaInvalidaException_Exception e) {
            req.setAttribute("motivoDeError", "La contrasenia es incorrecta");
            req.getRequestDispatcher("/WEB-INF/jsp/iniciar_sesion.jsp").forward(req, resp);
        }
    }

}