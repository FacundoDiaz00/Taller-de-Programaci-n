package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import publicar.usuarioturisticasservice.DtProveedor;
import publicar.usuarioturisticasservice.DtTurista;
import publicar.usuarioturisticasservice.DtUsuario;
import publicar.usuarioturisticasservice.DtUsuarioDetallePorTipo;
import publicar.usuarioturisticasservice.DtUsuarioDetallePrivadoPorTipo;
import publicar.usuarioturisticasservice.DtUsuarioPorTipo;
import publicar.usuarioturisticasservice.DtUsuarioSeparadosPorTipoCollection;
import publicar.usuarioturisticasservice.Imagen;
import publicar.usuarioturisticasservice.ModificacionUsuarioNoPermitida_Exception;
import publicar.usuarioturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaDeUsuarioServlet
 */

@WebServlet("/ConsultaDeUsuario")
@MultipartConfig
public class ConsultaDeUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    private WebServiceUsuarios wbUser;
    
    

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaDeUsuarioServlet() {
        super();
        wbUser = new WebServiceUsuariosService().getWebServiceUsuariosPort();
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
            if (usr != null && ((DtUsuario) usr).getNickname().equals(req.getParameter("id"))) {
            	DtUsuarioDetallePrivadoPorTipo DUser;
                try {
                	DUser = wbUser.obtenerDTUsuarioDetallePrivado(req.getParameter("id"));
                } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
                    req.setAttribute("motivoDeError",
                            "id de usuario invalido. No existe un usuario con ese nickname");
                    req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, res);
                    e.printStackTrace();
                    return;

                }
                
                req.setAttribute("usuario", DUser.isEsTurista() ? DUser.getDtTuristaDetalle() : DUser.getDtProveedorDetalle());
                req.getRequestDispatcher("/WEB-INF/jsp/perfil_de_usuario.jsp").forward(req, res);

            } else {
                try {
                    DtUsuarioDetallePorTipo DTusr = wbUser.obtenerDTUsuarioDetalle(req.getParameter("id"));
                    req.setAttribute("usuario", DTusr.isEsTurista() ? DTusr.getDtTuristaDetalle() : DTusr.getDtProveedorDetalle());
                    req.getRequestDispatcher("/WEB-INF/jsp/perfil_de_usuario.jsp").forward(req, res);

                } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
                    req.setAttribute("motivoDeError",
                            "id de usuario invalido. No existe un usuario con ese nickname");
                    req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, res);
                    e.printStackTrace();
                }
            }
        } else {
        	List<DtUsuario> usuarios = new ArrayList<>();
        	DtUsuarioSeparadosPorTipoCollection dtUserSepCollection = wbUser.obtenerDTUsuarios();
        	for (DtTurista dtTuri : dtUserSepCollection.getTuristas()) {
        		usuarios.add(dtTuri);
        	}
        	for (DtProveedor dtProvv : dtUserSepCollection.getProveedores()) {
        		usuarios.add(dtProvv);
        	}
        	
        	
             
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
        DtUsuario userLogueado = (DtUsuario) sesion.getAttribute("usuarioLogeado");
        DtUsuarioPorTipo datosNuevos = new DtUsuarioPorTipo();
        
        boolean borrarImagen = "on".equals(request.getParameter("borrar_imagen"));        
        
        String nick = userLogueado.getNickname();
        String correo = userLogueado.getCorreo();
        
        String modN = request.getParameter("modificar_nombre");
        String modA = request.getParameter("modificar_apellido");
        String modC = request.getParameter("input-contrasenia");

        String modFN = request.getParameter("modificar_fechaNac");
        String modNac = null;
        String modD = null;
        String modL = null;
        
        if(modC != null && modC.equals(""))
        	modC = null;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(modFN, formatter);
        
        Part filePart = request.getPart("modificar_img");

        boolean hayImagen = filePart != null && filePart.getSize() > 0 && !borrarImagen;
        String ext = "";
        String futuroNombreDelPath = "";
        Imagen imgDt = null;
        if (hayImagen) {
            ext = Utiles.devolverExtencionDelNombreDeArchivo(filePart.getSubmittedFileName());

            // Esto es la ruta relativa
            futuroNombreDelPath = "/usuarios/" + nick + ext;
            imgDt = new Imagen();
            imgDt.setPath(futuroNombreDelPath);
        }
        
        if (sesion.getAttribute("usuarioLogeado") instanceof DtTurista){
        	modNac = request.getParameter("modificar_nacionalidad");
        	DtTurista nuevoDtTurista = new DtTurista();
        	nuevoDtTurista.setNickname(nick);
        	nuevoDtTurista.setNombre(modN);
        	nuevoDtTurista.setApellido(modA);
        	nuevoDtTurista.setCorreo(correo);
        	nuevoDtTurista.setFechaNacStr(Utiles.localDateToString(fecha));
        	nuevoDtTurista.setImg(imgDt);
        	nuevoDtTurista.setNacionalidad(modNac);

        	datosNuevos.setEsTurista(true);
        	datosNuevos.setDtTurista(nuevoDtTurista);
        	
        }
        else {
        	modD = request.getParameter("modificar_descripcion");
        	modL = request.getParameter("modificar_link");
        	if(modL != null && modL.length() == 0) {
        		modL = null;
        	}
        	DtProveedor nuevoDtProv = new DtProveedor();
        	nuevoDtProv.setNickname(nick);
        	nuevoDtProv.setNombre(modN);
        	nuevoDtProv.setApellido(modA);
        	nuevoDtProv.setCorreo(correo);
        	nuevoDtProv.setFechaNacStr(Utiles.localDateToString(fecha));
        	nuevoDtProv.setImg(imgDt);
        	nuevoDtProv.setDescrpicionGeneral(modD);
        	nuevoDtProv.setLink(modL);

        	datosNuevos.setEsTurista(false);
        	datosNuevos.setDtProveedor(nuevoDtProv);
        }
        
        	try {
        		wbUser.modificarUsuario(datosNuevos, modC, borrarImagen);
				
				 String servidorPath = getServletContext().getRealPath("/");
	            if (hayImagen) {
	                // Utiles.crearDirectorioImagenesSiNoEstaCreado(servidorPath);
	                InputStream imgInputStream = filePart.getInputStream();
	               
	                File imgFile = new File(servidorPath + "/img" + futuroNombreDelPath);
	                imgFile.createNewFile();
	                FileOutputStream imgFileStream = new FileOutputStream(imgFile);

	                byte[] buffer = new byte[8192];

	                int readLength = imgInputStream.read(buffer);
	                while (readLength != -1) {
	                    imgFileStream.write(buffer, 0, readLength);
	                    readLength = imgInputStream.read(buffer);
	                }
	                imgFileStream.close();
	            } else if (borrarImagen && userLogueado.getImg() != null){
	            	File imgDel = new File(servidorPath + "/img" + userLogueado.getImg().getPath());
	            	imgDel.delete();
	            }
	            //Actualizo datos sesion
	            DtUsuarioPorTipo usuario = wbUser.obtenerDTUsuario(nick);
	            sesion.setAttribute("usuarioLogeado", usuario.isEsTurista() ? usuario.getDtTurista() : usuario.getDtProveedor());
	            request.setAttribute("exito", Boolean.TRUE);
	            
	            
			} catch (ObjetoNoExisteEnTurismoUy_Exception | ModificacionUsuarioNoPermitida_Exception e) {
				request.setAttribute("motivoDeError",
	                    "Los datos enviados no son válidos");
				
			} 


        doGet(request, response);
    }

}
