package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.Imagen;
import utils.Utiles;

/**
 * Servlet implementation class AltaDeUsuario
 */
@WebServlet("/AltaDeActividad") @MultipartConfig
public class AltaActividadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorActividadTuristica contActividad;

	public AltaActividadServlet() {
		super();
		this.contActividad = Fabrica.getInstancia().getIControladorActividadTuristica();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req = Utiles.insertarLoDeSiempre(req);
		req.getRequestDispatcher("/WEB-INF/jsp/alta_de_actividad_turistica.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getCharacterEncoding() == null) {
			req.setCharacterEncoding("UTF-8");
		}

		String nickProveedor = "washington";
		// TODO: generar este dato a partir
		// de la
		// sesión
		String departamento = req.getParameter("departamento");
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		String duracion = req.getParameter("duracion");
		String costo = req.getParameter("costo");
		String ciudad = req.getParameter("ciudad");
		List<String> categorias = Arrays.asList(req.getParameterValues("categorias"));

		Part filePart = req.getPart("img");

		boolean hayImagen = filePart.getSize() > 0;
		String ext = "";
		String futuroNombreDelPath = "";
		Imagen imgDt = null;
		if (hayImagen) {
			ext = Utiles.devolverExtencionDelNombreDeArchivo(filePart.getSubmittedFileName());

			futuroNombreDelPath = "/actividades/" + nombre + ext; // Esto es
																	// la
																	// ruta
																	// relativa
			imgDt = new Imagen(futuroNombreDelPath);
		}

		try {
			contActividad.altaActividadTuristica(nickProveedor, departamento, nombre, descripcion,
					Integer.valueOf(duracion), Float.valueOf(costo), ciudad, null, imgDt, categorias);
			if (hayImagen) {
				// Utiles.crearDirectorioImagenesSiNoEstaCreado(servidorPath);
				InputStream imgInputStream = filePart.getInputStream();
				String servidorPath = getServletContext().getRealPath("/");
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
			}

			System.out.println("Actividad creada con exito");
			req.setAttribute("exito", "exito");

			resp.sendRedirect("AltaDeActividad");
			return;
		} catch (NumberFormatException e) {
			System.out.println("No se ingresaron los números de duracion o costo correctamente");
			req.setAttribute("motivoDeError",
					"No se ingresaron los números de duracion o costo correctamente, cambielos y pruebe nuevamente");
		} catch (ActividadTuristicaYaRegistradaException e) {
			System.out.println("La actividad con nombre '" + nombre
					+ "' no se puede crear ya que ya existe alguna act. con ese nombre.");
			req.setAttribute("motivoDeError", "Ya existe una actividad con ese nombre, cambielo y pruebe nuevamente");
		} catch (ObjetoNoExisteEnTurismoUy e) {
			if (e.getClaseObjetoFaltante().equals("Categoria")) {
				System.out.println("No existe una de las categorias selecionadas");
				req.setAttribute("motivoDeError",
						"No existe una de las categorias selecionadas, cambielo y pruebe nuevamente");
			} else if (e.getClaseObjetoFaltante().equals("Departamento")) {
				System.out.println("No existe el departamento seleccionado");
				req.setAttribute("motivoDeError",
						"No existe el departamento seleccionado, cambielo y pruebe nuevamente");
			}
		}

		// En este punto si o si hay error

		req.setAttribute("departamento", departamento);
		req.setAttribute("nombre", nombre);
		req.setAttribute("descripcion", descripcion);
		req.setAttribute("duracion", duracion);
		req.setAttribute("costo", costo);
		req.setAttribute("ciudad", ciudad);

		req = Utiles.insertarLoDeSiempre(req);

		req.getRequestDispatcher("/WEB-INF/jsp/alta_de_actividad_turistica.jsp").forward(req, resp);

	}

}
