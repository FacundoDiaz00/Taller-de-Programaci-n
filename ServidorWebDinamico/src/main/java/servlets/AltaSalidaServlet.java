package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.SalidaYaRegistradaException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
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

    String nomActividad;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Solo muestro el form si es un proveedor:

        DTUsuario usuario = (DTUsuario) req.getSession().getAttribute("usuarioLogeado");
        if (usuario == null || !(usuario instanceof DTProveedor)) {
            req = Utiles.insertarLoDeSiempre(req);
            resp.sendRedirect("/index");
            return;
        }
        nomActividad = (String) req.getParameter("id");
        try {
        	DTActividadTuristicaDetalle datosActividad = cat.obtenerDTActividadTuristicaDetalle(nomActividad);
        	req.setAttribute("datosActividad", datosActividad);
        
        } catch (ObjetoNoExisteEnTurismoUy e){
        	req.setAttribute("motivoDeError", "No existe la actividad turistica");
            req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_actividad.jsp").forward(req, resp);
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
        
        String actividad = req.getParameter("id");
        String nombre = req.getParameter("nombre");
        String fechaSalida = req.getParameter("fechaSalida");
        String horaSalida = req.getParameter("horaSalida");
        String lugar = req.getParameter("lugar");
        String cantMaxTur = req.getParameter("cantMaxTur");
        System.out.println(nomActividad);
        System.out.println(nombre);
        System.out.println(fechaSalida);
        System.out.println(horaSalida);
        System.out.println(lugar);
        System.out.println(cantMaxTur);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaYHoraSalida = LocalDateTime.parse(fechaSalida + " " + horaSalida, formatter);
        LocalDate fechaDeAlta = null;
        
        
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
            cat.altaSalidaTuristica(nomActividad, nombre, fechaYHoraSalida , fechaDeAlta, lugar,
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
            req = Utiles.insertarLoDeSiempre(req);
            req.getRequestDispatcher("/WEB-INF/jsp/alta_de_salida_turistica.jsp").forward(req, resp);
            return;
            
        } catch (NumberFormatException e) {
            System.out.println("No se ingresaron los números de duracion o costo correctamente");
            req.setAttribute("motivoDeError",
                    "No se ingresaron los números de duracion o costo correctamente, cambielos y pruebe nuevamente");
        } catch (FechaAltaActividadPosteriorAFechaAltaSalidaException e) {
            System.out.println("excepcion: Fecha de alta actividad es posterior a fecha alta de salida");
            req.setAttribute("motivoDeError",
                    "La fecha de la salida debe ser posterior a la fecha de alta de la actividad");
        } catch (FechaAltaSalidaPosteriorAFechaSalidaException e) {
            System.out.println("excepcion: Fecha del alta salida es posterior a fecha alta de la salida");
            req.setAttribute("motivoDeError",
                    "La fecha de la salida debe ser posterior a la fecha actual");
        } catch (SalidaYaRegistradaException e) {
            System.out.println("La actividad con nombre '" + nombre
                   + "' no se puede crear ya que ya existe alguna act. con ese nombre.");
            req.setAttribute("motivoDeError", "Ya existe una actividad con ese nombre, cambielo y pruebe nuevamente");
        } catch (ObjetoNoExisteEnTurismoUy e) {
            if (e.getClaseObjetoFaltante().equals("Actividad")) {
                System.out.println("No existe la actividad seleccionada");
                req.setAttribute("motivoDeError",
                        "No existe la actividad seleccionada, cambielo y pruebe nuevamente");
            } else if (e.getClaseObjetoFaltante().equals("Departamento")) {
                System.out.println("No existe el departamento seleccionado");
                req.setAttribute("motivoDeError",
                        "No existe el departamento seleccionado, cambielo y pruebe nuevamente");
            }
        }

        // En este punto si o si hay error
        req.setAttribute("actividad", actividad);
        req.setAttribute("nombre", nombre);
        //req.setAttribute("fechaYHoraSalida", fechaYHoraSalida);
        req.setAttribute("lugar", lugar);
        req.setAttribute("cantMaxTur", cantMaxTur);

        req = Utiles.insertarLoDeSiempre(req);

        req.getRequestDispatcher("/WEB-INF/jsp/alta_de_salida.jsp").forward(req, resp);

    }

}
