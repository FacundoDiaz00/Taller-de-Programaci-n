package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;

/**
 * Servlet implementation class AltaDeUsuario
 */
@WebServlet("/UsuarioYaRegistradoServlet")
@MultipartConfig
public class UsuarioYaRegistradoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceUsuarios wbUsers;

    public UsuarioYaRegistradoServlet() {
        super();
        wbUsers= new WebServiceUsuariosService().getWebServiceUsuariosPort();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String response = "";
        String nickname = req.getParameter("nickname");
        if(nickname != null && nickname.trim().length() > 0){
            if(wbUsers.nicknameDisponibleParaNuevoUsuario(nickname)){
                response = "Nickname disponible\n";
            }else {
                response = "Nickname en uso";
            }
        }else{
            String correo = req.getParameter("correo");
            if(correo != null && correo.trim().length() > 0){
                if(wbUsers.emailDisponibleParaNuevoUsuario(correo)){
                    response = "Correo disponible";
                }else{
                    response = "Correo en uso";
                }
            }
        }
        resp.setContentType("text/plain");
        resp.getWriter().write(response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
