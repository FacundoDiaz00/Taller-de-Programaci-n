package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import logica.datatypes.DTProveedor;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
import utils.Utiles;

/**
 * Servlet implementation class AltaDeUsuario
 */
@WebServlet("/AltaDeSalida")
@MultipartConfig
public class AltaSalidaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorActividadTuristica cat;

    public AltaSalidaServlet() {
        super();
        this.cat = Fabrica.getInstancia().getIControladorActividadTuristica();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Solo muestro el form si es un proveedor:

        DTUsuario usuario = (DTUsuario) req.getSession().getAttribute("usuarioLogeado");
        if (usuario == null || !(usuario instanceof DTProveedor)) {
            req = Utiles.insertarLoDeSiempre(req);
            resp.sendRedirect("/index");
            return;
        }

        req = Utiles.insertarLoDeSiempre(req);
        req.getRequestDispatcher("/WEB-INF/jsp/alta_de_salida_turistica.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        // Solo analizo el request si es un proveedor:
        DTUsuario usuario = (DTUsuario) req.getSession().getAttribute("usuarioLogeado");
        if (usuario == null || !(usuario instanceof DTProveedor)) {
            req = Utiles.insertarLoDeSiempre(req);
            resp.sendRedirect("index");
            return;
        }

        String nickProveedor = ((DTUsuario) req.getSession().getAttribute("usuarioLogeado")).getNickname();
        
        String actividad = req.getParameter("departamento");
        String nombre = req.getParameter("nombre");
        String fechaYHoraSalida = req.getParameter("descripcion");
        String lugar = req.getParameter("duracion");
        String cantMaxTur = req.getParameter("costo");

        Part filePart = req.getPart("img");

        boolean hayImagen = filePart.getSize() > 0;
        String ext = "";
        String futuroNombreDelPath = "";
        Imagen imgDt = null;
        if (hayImagen) {
            ext = Utiles.devolverExtencionDelNombreDeArchivo(filePart.getSubmittedFileName());

            // Esto es la ruta relativa
            futuroNombreDelPath = "/salidas/" + nombre + ext;
            imgDt = new Imagen(futuroNombreDelPath);
        }

        try {
            cat.altaSalidaTuristica(actividad, nombre, LocalDateTime.parse(fechaYHoraSalida), LocalDate.parse(null), lugar,
                    Integer.valueOf(cantMaxTur), imgDt);
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

            System.out.println("Salida creada con exito");
            req.setAttribute("exito", "exito");

            resp.sendRedirect("AltaDeSalida");
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
