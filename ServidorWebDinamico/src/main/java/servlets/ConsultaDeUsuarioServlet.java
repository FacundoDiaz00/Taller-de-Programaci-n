package servlets;

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

import publicar.actividadesturisticasservice.EstadoActividadTuristica;
import publicar.actividadesturisticasservice.TurismoUyException_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.usuarioturisticasservice.DtProveedor;
import publicar.usuarioturisticasservice.DtTurista;
import publicar.usuarioturisticasservice.DtUsuario;
import publicar.usuarioturisticasservice.DtUsuarioSeparadosPorTipoCollection;
import publicar.usuarioturisticasservice.ErrorAlProcesar_Exception;
import publicar.usuarioturisticasservice.ModificacionUsuarioNoPermitida_Exception;
import publicar.usuarioturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;
import utils.Utile;

/**
 * Servlet implementation class ConsultaDeUsuarioServlet
 */

@WebServlet("/ConsultaDeUsuario") @MultipartConfig
public class ConsultaDeUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebServiceUsuarios wbUser;
	private WebServiceActividades wbActi;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConsultaDeUsuarioServlet() {
		super();
		wbUser = new WebServiceUsuariosService().getWebServiceUsuariosPort();
		wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
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
		boolean seguir = Boolean.valueOf(req.getParameter("seguir"));
		boolean finalizar = Boolean.valueOf(req.getParameter("finalizar"));
		req = Utile.insertarLoDeSiempre(req);
		HttpSession sesion = req.getSession(false);
		boolean seSiguenUsuarios = false;
		Object usr = sesion.getAttribute("usuarioLogeado");

		if (debelistar != null && debelistar.equals("false")) {

			if (finalizar) {
				String idActividad = (String) req.getParameter("idAct");
				try {
					wbActi.cambiarEstadoDeActividadTuristica(idActividad, EstadoActividadTuristica.FINALIZADA);

					req.setAttribute("exito", true);
				} catch (TurismoUyException_Exception e) {
					req.setAttribute("motivoDeError", "La actividad turistica que se desea finalizar no existe");
					req.setAttribute("finalizar", false);
				}
			} else if (seguir) {

				if (usr != null && !((DtUsuario) usr).getNickname().equals(req.getParameter("id"))) {
					try {
						wbUser.seguirODejarDeSeguirUsuario(((DtUsuario) usr).getNickname(), req.getParameter("id"));


						req.setAttribute("exito", Boolean.TRUE);

					} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
						req.setAttribute("motivoDeError",
								"id de usuario invalido. No existe un usuario con ese nickname");
						req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, res);
						e.printStackTrace();
					}

				}
			}
			try {
				if (usr != null && !((DtUsuario) usr).getNickname().equals(req.getParameter("id"))) {
					seSiguenUsuarios = wbUser.usuariosSeSiguen(((DtUsuario) usr).getNickname(), req.getParameter("id"));
					req.setAttribute("seSiguenUsuarios", seSiguenUsuarios);

				}



			} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
				throw new RuntimeException(e);
			}
			try {
				String usrVisitado = (String) req.getParameter("id");

				DtUsuarioSeparadosPorTipoCollection seguidos = wbUser.obtenerSeguidos(usrVisitado);
				DtUsuarioSeparadosPorTipoCollection seguidores = wbUser.obtenerSeguidores(usrVisitado);
				req.setAttribute("seguidos", seguidos);
				req.setAttribute("seguidores", seguidores);

			} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
				req.setAttribute("motivoDeError", "id de usuario invalido. No existe un usuario con ese nickname");
				req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, res);
				e.printStackTrace();
			}

			req.setAttribute("seSiguenUsuarios", seSiguenUsuarios);

			if (usr != null && ((DtUsuario) usr).getNickname().equals(req.getParameter("id"))) {
				DtUsuario usuario;
				try {
					usuario = wbUser.obtenerDTUsuarioDetallePrivado(req.getParameter("id"));
				} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
					req.setAttribute("motivoDeError", "id de usuario invalido. No existe un usuario con ese nickname");
					req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, res);
					e.printStackTrace();
					return;
				}

				req.setAttribute("usuario", usuario);
				req.getRequestDispatcher("/WEB-INF/jsp/perfil_de_usuario.jsp").forward(req, res);

			} else {
				try {
					DtUsuario usuario = wbUser.obtenerDTUsuarioDetalle(req.getParameter("id"));

					req.setAttribute("usuario", usuario);
					req.getRequestDispatcher("/WEB-INF/jsp/perfil_de_usuario.jsp").forward(req, res);

				} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
					req.setAttribute("motivoDeError", "id de usuario invalido. No existe un usuario con ese nickname");
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
	 * response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		HttpSession sesion = request.getSession(false);
		DtUsuario userLogueado = (DtUsuario) sesion.getAttribute("usuarioLogeado");
		DtUsuario datosNuevos;

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

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(modFN, formatter);

		Part filePart = request.getPart("modificar_img");

		boolean hayImagen = filePart != null && filePart.getSize() > 0 && !borrarImagen;
		String ext = borrarImagen ? "BORRAR" : "";
		byte[] imgContent = new byte[0];

		if (hayImagen) {
			InputStream imgInputStream = filePart.getInputStream();
			ext = Utile.devolverExtencionDelNombreDeArchivo(filePart.getSubmittedFileName());
			imgContent = imgInputStream.readAllBytes();
			imgInputStream.close();
		}

		if (sesion.getAttribute("usuarioLogeado") instanceof DtTurista) {
			modNac = request.getParameter("modificar_nacionalidad");
			DtTurista nuevoDtTurista = new DtTurista();
			nuevoDtTurista.setNickname(nick);
			nuevoDtTurista.setNombre(modN);
			nuevoDtTurista.setApellido(modA);
			nuevoDtTurista.setCorreo(correo);
			nuevoDtTurista.setFechaNacStr(Utile.localDateToString(fecha));
			nuevoDtTurista.setImg(null);
			nuevoDtTurista.setNacionalidad(modNac);

			datosNuevos = nuevoDtTurista;
		} else {
			modD = request.getParameter("modificar_descripcion");
			modL = request.getParameter("modificar_link");
			if (modL != null && modL.length() == 0) {
				modL = null;
			}
			DtProveedor nuevoDtProv = new DtProveedor();
			nuevoDtProv.setNickname(nick);
			nuevoDtProv.setNombre(modN);
			nuevoDtProv.setApellido(modA);
			nuevoDtProv.setCorreo(correo);
			nuevoDtProv.setFechaNacStr(Utile.localDateToString(fecha));
			nuevoDtProv.setImg(null);
			nuevoDtProv.setDescrpicionGeneral(modD);
			nuevoDtProv.setLink(modL);

			datosNuevos = nuevoDtProv;
		}

		try {
			wbUser.modificarUsuario(datosNuevos, modC, imgContent, ext);
			// Actualizo datos sesion
			DtUsuario usuario = wbUser.obtenerDTUsuario(nick);

			sesion.setAttribute("usuarioLogeado", usuario);
			request.setAttribute("exito", Boolean.TRUE);

		} catch (ObjetoNoExisteEnTurismoUy_Exception | ModificacionUsuarioNoPermitida_Exception e) {
			request.setAttribute("motivoDeError", "Los datos enviados no son válidos");

		} catch (ErrorAlProcesar_Exception e) {
			request.setAttribute("motivoDeError", "Error general al procesar la solicitud");
		}

		doGet(request, response);
	}

}
