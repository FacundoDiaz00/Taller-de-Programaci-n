package servlets;

import java.awt.Image;
import java.io.File;
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

import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.UsuarioYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorMaestro;
import logica.controladores.IControladorUsuario;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
import utils.Utiles;

/**
 * Servlet implementation class AltaDeUsuario
 */
@WebServlet("/AltaDeUsuario")
@MultipartConfig
public class AltaDeUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorUsuario contUsuario;
	
	private final String tipoUsuarioProveedor = "proveedor";
	private final String tipoUsuarioTurista = "turista";
	

	public AltaDeUsuarioServlet() {
		super();
		this.contUsuario = Fabrica.getInstancia().getIControladorUsuario();
	}

	/**
	 * parametros posibles: - tipo : Tipo de usuario a crear, puede ser "turista" o "proveedor"
	 * 						- body: con la info del usuario a crear. Los atributos son los siguientes: 
	 * 							-
	 * 
	 */
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req = Utiles.insertarLoDeSiempre(req);
		req.getRequestDispatcher("/WEB-INF/jsp/alta_de_usuario.jsp").forward(req, resp);
		
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		Part filePart = req.getPart("img");
		
		
		try {
			
			 
			filePart.getSize();
			String nombreImg = filePart.getSubmittedFileName();
			InputStream imgContent = filePart.getInputStream();
			String tipo = filePart.getContentType();
			
			String futuroNombreDelPath = "../../webapp/img/usuarios/" + nickname;
			
			int read = imgContent.read(); //Leo antisipadamente para saber si esta o no el archivo vacio
			
			boolean hayImagen = read != -1; //Si recivo -1 es que lei todo el archivo, por ende si en este punto no hay nada es que no viajo nada 
			
			Imagen imgDt = null;
			if(hayImagen) {
				imgDt = new Imagen(futuroNombreDelPath);
			}
			
			LocalDate fechaNac = LocalDate.parse(fechaNacStr);
			if (tipoUsuario.equals(tipoUsuarioProveedor)) {
				this.contUsuario.altaProveedor(nickname, nombre, apellido, password, email, fechaNac, imgDt, descripcionGeneral, link);
			} else if (tipoUsuario.equals(tipoUsuarioTurista)) {
				this.contUsuario.altaTurista(nickname, nombre, apellido, password, email, fechaNac, imgDt, nacionalidad);
			} else {
				req.setAttribute("motivoDeError", "No se soporta el alta de este tipo de usuario");
				req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
				return;
			}
			
//			if(hayImagen){
//				File img = new File()
//			}
			
						
			DTUsuario dtUsuario = this.contUsuario.obtenerDTUsuario(nickname);

			System.out.println("Usuario " + dtUsuario.getNickname() + " creado con exito");
	
			
			resp.sendRedirect("IniciarSesion");
			return;
		} catch(UsuarioYaRegistradoException e) {
			System.out.println("El usuario con nickname " + nickname + "y correo " + email + " no se puede crear ya que tiene alguna de sus dos claves repetidas" );
			req.setAttribute("motivoDeError", "Ya existe un usuario con este nickname o con ese correo, cambie alguno de estos y pruebe nuevamente");	
		} catch (ObjetoNoExisteEnTurismoUy e) {
			//Esto caso no puede ocurrir
		}
		
		//En este punto si o si hay error
		
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

				
		req = Utiles.insertarLoDeSiempre(req);

		req.getRequestDispatcher("/WEB-INF/jsp/alta_de_usuario.jsp").forward(req, resp);

	}

}
