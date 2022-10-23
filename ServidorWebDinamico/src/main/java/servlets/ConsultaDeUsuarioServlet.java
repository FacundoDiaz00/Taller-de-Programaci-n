package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import excepciones.ModificacionUsuarioNoPermitida;
import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaDeUsuarioServlet
 */

@WebServlet("/ConsultaDeUsuario")
@MultipartConfig
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        String debelistar = req.getParameter("listar");
        req = Utiles.insertarLoDeSiempre(req);
        HttpSession sesion = req.getSession(false);
        Object usr = sesion.getAttribute("usuarioLogeado");
        if (debelistar != null && debelistar.equals("false")) {
            if (usr != null && ((DTUsuario) usr).getNickname().equals(req.getParameter("id"))) {
                DTUsuario DUser = null;
                try {
                    DUser = contrUsuario.obtenerDTUsuarioDetallePrivado(req.getParameter("id"));
                } catch (ObjetoNoExisteEnTurismoUy e) {
                    req.setAttribute("motivoDeError",
                            "id de usuario invalido. No existe un usuario con ese nickname");
                    req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, res);
                    e.printStackTrace();
                    return;

                }
                req.setAttribute("usuario", DUser);
                req.getRequestDispatcher("/WEB-INF/jsp/perfil_de_usuario.jsp").forward(req, res);

            } else {
                try {
                    DTUsuario DTusr = contrUsuario.obtenerDTUsuarioDetalle(req.getParameter("id"));
                    req.setAttribute("usuario", DTusr);
                    req.getRequestDispatcher("/WEB-INF/jsp/perfil_de_usuario.jsp").forward(req, res);

                } catch (ObjetoNoExisteEnTurismoUy e) {
                    req.setAttribute("motivoDeError",
                            "id de usuario invalido. No existe un usuario con ese nickname");
                    req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, res);
                    e.printStackTrace();
                }
            }
        } else {
            List<DTUsuario> usuarios = contrUsuario.obtenerDTUsuarios();
            req.setAttribute("usuarios", usuarios);
            req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_usuario.jsp").forward(req, res);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        HttpSession sesion = request.getSession(false);
        DTUsuario userLogueado = (DTUsuario) sesion.getAttribute("usuarioLogeado");
        DTUsuario datosNuevos;
        
        String nick = userLogueado.getNickname();
        String modN = request.getParameter("modificar_nombre");
        String modA = request.getParameter("modificar_apellido");
        String modC = request.getParameter("modificar_contrasenia");
        String modFN = request.getParameter("modificar_fechaNac");
        
        String modNac = "";
        String modD = "";
        String modL = "";
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(modFN, formatter);
        
        Part filePart = request.getPart("modificar_img");

        boolean hayImagen = filePart.getSize() > 0;
        String ext = "";
        String futuroNombreDelPath = "";
        Imagen imgDt = null;
        if (hayImagen) {
            ext = Utiles.devolverExtencionDelNombreDeArchivo(filePart.getSubmittedFileName());

            // Esto es la ruta relativa
            futuroNombreDelPath = "/usuarios/" + nick + ext;
            imgDt = new Imagen(futuroNombreDelPath);
        }
        
        if (sesion.getAttribute("usuarioLogueado") instanceof DTTurista){
        	modNac = request.getParameter("modificar_nacionalidad");
        	datosNuevos = (DTUsuario) new DTTurista(nick, modN, modA, modC, fecha, imgDt, modNac);
        }
        else {
        	modD = request.getParameter("modificar_descripcion");
        	modL = request.getParameter("modificar_link");
        	datosNuevos = (DTUsuario) new DTProveedor(nick, modN, modA, modC, fecha, imgDt, modD, modL);
        }
        
        	try {
				contrUsuario.modificarUsuario(datosNuevos);
			} catch (ModificacionUsuarioNoPermitida | ObjetoNoExisteEnTurismoUy e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


        doGet(request, response);
    }

}
