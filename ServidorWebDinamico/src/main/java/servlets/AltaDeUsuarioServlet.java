package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.paqueteturisticasservice.WebServicePaquetes;
import publicar.usuarioturisticasservice.ErrorAlProcesar_Exception;
import publicar.usuarioturisticasservice.Imagen;
import publicar.usuarioturisticasservice.UsuarioYaRegistradoException_Exception;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;
import utils.Utile;

/**
 * Servlet implementation class AltaDeUsuario
 */
@WebServlet("/AltaDeUsuario")
@MultipartConfig
public class AltaDeUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceUsuarios wbUser;

    private final String tipoUsuarioProveedor = "proveedor";
    private final String tipoUsuarioTurista = "turista";

    public AltaDeUsuarioServlet() {
        super();
        wbUser = new WebServiceUsuariosService().getWebServiceUsuariosPort();
    }

    /**
     * parametros posibles: - tipo : Tipo de usuario a crear, puede ser
     * "turista" o "proveedor" - body: con la info del usuario a crear. Los
     * atributos son los siguientes: -
     * 
     */

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        req = Utile.insertarLoDeSiempre(req);
        req.getRequestDispatcher("/WEB-INF/jsp/alta_de_usuario.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }
    	
        String tipoUsuario = (String) req.getParameter("tipoUsuario");

        String nickname = (String) req.getParameter("nickname");
        String nombre = (String) req.getParameter("nombre");
        String apellido = (String) req.getParameter("apellido");
        String password = (String) req.getParameter("password");
        String email = (String) req.getParameter("email");
        String fechaNacStr = (String) req.getParameter("fechaNac");
        String nacionalidad = (String) req.getParameter("nacionalidad");
        String descripcionGeneral = (String) req.getParameter("descripcionGeneral");
        String link = (String) req.getParameter("link");
        if(link == null ) {
        	link = "";
        }
        Part filePart = req.getPart("img");

        try {

            boolean hayImagen = filePart.getSize() > 0;
            String ext = ""; 
            byte[] imgContent = new byte[0]; 
            if (hayImagen) {
            	
            	InputStream imgInputStream = filePart.getInputStream();
                ext = Utile.devolverExtencionDelNombreDeArchivo(filePart.getSubmittedFileName());
                imgContent = imgInputStream.readAllBytes();
                imgInputStream.close();
            }

            LocalDate fechaNac = LocalDate.parse(fechaNacStr);
            if (tipoUsuario.equals(tipoUsuarioProveedor)) {
            	wbUser.altaProveedor(nickname, nombre, apellido, email, password, Utile.localDateToString(fechaNac),imgContent, ext ,descripcionGeneral, link);
            } else if (tipoUsuario.equals(tipoUsuarioTurista)) {
            	wbUser.altaTurista(nickname, nombre, apellido, email, password, Utile.localDateToString(fechaNac), imgContent, ext, nacionalidad);
            } else {
                req.setAttribute("motivoDeError", "No se soporta el alta de este tipo de usuario");
                req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("exito", Boolean.TRUE);
            req = Utile.insertarLoDeSiempre(req);
            req.getRequestDispatcher("/WEB-INF/jsp/alta_de_usuario.jsp").forward(req, resp);
            return;
        } catch (UsuarioYaRegistradoException_Exception e) {
            req.setAttribute("motivoDeError",
                    "Ya existe un usuario con este nickname o con ese correo, cambie alguno de estos y pruebe nuevamente");
        } catch (ErrorAlProcesar_Exception e) {
        	req.setAttribute("motivoDeError",
                    "Error al procesar la solicitud");
        }

        // En este punto si o si hay error

        req.setAttribute("tipoUsuario", tipoUsuario);

        req.setAttribute("nickname", nickname);
        req.setAttribute("nombre", nombre);
        req.setAttribute("apellido", apellido);
        req.setAttribute("password", password);
        req.setAttribute("email", email);
        req.setAttribute("fechaNac", fechaNacStr);
        req.setAttribute("nacionalidad", nacionalidad);
        req.setAttribute("descripcionGeneral", descripcionGeneral);
        req.setAttribute("link", link);

        req = Utile.insertarLoDeSiempre(req);

        req.getRequestDispatcher("/WEB-INF/jsp/alta_de_usuario.jsp").forward(req, resp);

    }

}
