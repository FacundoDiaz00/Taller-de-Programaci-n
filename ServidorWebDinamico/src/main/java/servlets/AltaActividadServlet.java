package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
@WebServlet("/AltaDeActividad")
@MultipartConfig
public class AltaActividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorActividadTuristica contActividad;

    public AltaActividadServlet() {
        super();
        this.contActividad = Fabrica.getInstancia().getIControladorActividadTuristica();
    }
    
    void accionDoGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        // Solo muestro el form si es un proveedor:

        DTUsuario usuario = (DTUsuario) req.getSession().getAttribute("usuarioLogeado");
        if (usuario == null || !(usuario instanceof DTProveedor)) {
            req = Utiles.insertarLoDeSiempre(req);
            resp.sendRedirect("/index");
            return;
        }

        req = Utiles.insertarLoDeSiempre(req);
        req.getRequestDispatcher("/WEB-INF/jsp/alta_de_actividad_turistica.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	this.accionDoGet(req, resp);

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
        String departamento = req.getParameter("departamento");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String duracion = req.getParameter("duracion");
        String costo = req.getParameter("costo");
        String ciudad = req.getParameter("ciudad");
        String urlVideo = req.getParameter("urlVideo");
        
        if(req.getParameterValues("categorias") == null) {
        	req.setAttribute("motivoDeError", "Se debe seleccionar al menos una categoria");
        } else  {
        	 List<String> categorias = Arrays.asList(req.getParameterValues("categorias"));

             Part filePart = req.getPart("img");

             boolean hayImagen = filePart.getSize() > 0;
             String ext = "";
             String futuroNombreDelPath = "";
             Imagen imgDt = null;
             if (hayImagen) {
                 ext = Utiles.devolverExtencionDelNombreDeArchivo(filePart.getSubmittedFileName());

                 // Esto es la ruta relativa
                 futuroNombreDelPath = "/actividades/" + nombre + ext;
                 imgDt = new Imagen(futuroNombreDelPath);
             }

             try {
            	 // TODO aceptar la url del video
                 contActividad.altaActividadTuristica(nickProveedor, departamento, nombre, descripcion,
                         Integer.valueOf(duracion), Float.valueOf(costo), ciudad, null, imgDt, categorias, urlVideo);
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

                 req.setAttribute("exito", "exito");

                 this.accionDoGet(req, resp);
                 return;
             } catch (NumberFormatException e) {
                 req.setAttribute("motivoDeError",
                         "No se ingresaron los números de duracion o costo correctamente, cambielos y pruebe nuevamente");
             } catch (ActividadTuristicaYaRegistradaException e) {
                 req.setAttribute("motivoDeError", "Ya existe una actividad con ese nombre, cambielo y pruebe nuevamente");
             } catch (ObjetoNoExisteEnTurismoUy e) {
                 if (e.getClaseObjetoFaltante().equals("Categoria")) {
                     req.setAttribute("motivoDeError",
                             "No existe una de las categorias selecionadas, cambielo y pruebe nuevamente");
                 } else if (e.getClaseObjetoFaltante().equals("Departamento")) {
                     req.setAttribute("motivoDeError",
                             "No existe el departamento seleccionado, cambielo y pruebe nuevamente");
                 }
             }
             req.setAttribute("categoriasSeleccionadas", categorias);
        }
       
        // En este punto si o si hay error
        req.setAttribute("departamento", departamento);
        req.setAttribute("nombre", nombre);
        req.setAttribute("descripcion", descripcion);
        req.setAttribute("duracion", duracion);
        req.setAttribute("costo", costo);
        req.setAttribute("ciudad", ciudad);
        req.setAttribute("urlVideo", urlVideo);
        
        req = Utiles.insertarLoDeSiempre(req);
        req.getRequestDispatcher("/WEB-INF/jsp/alta_de_actividad_turistica.jsp").forward(req, resp);

    }

}
