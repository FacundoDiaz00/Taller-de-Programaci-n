package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import publicar.actividadesturisticasservice.ActividadTuristicaNoAceptada_Exception;
import publicar.actividadesturisticasservice.DtActividadTuristicaDetalle;
import publicar.actividadesturisticasservice.ErrorAlProcesar_Exception;
import publicar.actividadesturisticasservice.FechaAltaActividadPosteriorAFechaAltaSalidaException_Exception;
import publicar.actividadesturisticasservice.FechaAltaSalidaPosteriorAFechaSalidaException_Exception;
import publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.actividadesturisticasservice.SalidaYaRegistradaException_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.usuarioturisticasservice.DtProveedor;
import publicar.usuarioturisticasservice.DtUsuario;
import utils.Utiles;

/**
 * Servlet implementation class AltaDeUsuario
 */
@WebServlet("/AltaDeSalida")
@MultipartConfig
public class AltaSalidaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceActividades wbActi;

    public AltaSalidaServlet() {
        super();
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
    }
    
    
    private void procesarGet(String nomActividad, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
    	 if (req.getCharacterEncoding() == null) {
             req.setCharacterEncoding("UTF-8");
         }

         // Solo muestro el form si es un proveedor:

         DtUsuario usuario = (DtUsuario) req.getSession().getAttribute("usuarioLogeado");
         if (usuario == null || !(usuario instanceof DtProveedor)) {
             req = Utiles.insertarLoDeSiempre(req);
             resp.sendRedirect("/index");
             return;
         }
         try {
             DtActividadTuristicaDetalle datosActividad = wbActi.obtenerDTActividadTuristicaDetalle(nomActividad);
             req.setAttribute("datosActividad", datosActividad);

         } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
             req.setAttribute("motivoDeError", "No existe la actividad turistica");
             req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
             return;
         }

         req = Utiles.insertarLoDeSiempre(req);
         req.getRequestDispatcher("/WEB-INF/jsp/alta_de_salida_turistica.jsp").forward(req, resp);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.procesarGet((String) req.getParameter("id"), req, resp); 

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        // Solo analizo el request si es un proveedor:
        DtUsuario usuario = (DtUsuario) req.getSession().getAttribute("usuarioLogeado");
        if (usuario == null || !(usuario instanceof DtProveedor)) {
            req = Utiles.insertarLoDeSiempre(req);
            resp.sendRedirect("index");
            return;
        }

        String nickProveedor = ((DtUsuario) req.getSession().getAttribute("usuarioLogeado")).getNickname();

        String actividad = req.getParameter("id");
        String nombre = req.getParameter("nombre");
        String fechaSalida = req.getParameter("fechaSalida");
        String horaSalida = req.getParameter("horaSalida");
        String lugar = req.getParameter("lugar");
        String cantMaxTur = req.getParameter("cantMaxTur");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime fechaYHoraSalida = LocalDateTime.parse(fechaSalida + " " + horaSalida, formatter);

        Part filePart = req.getPart("img");

        boolean hayImagen = filePart.getSize() > 0;
        String ext = "";
        byte[] imgContent = new byte[0]; 
        if (hayImagen) {
         	InputStream imgInputStream = filePart.getInputStream();
            ext = Utiles.devolverExtencionDelNombreDeArchivo(filePart.getSubmittedFileName());
            imgContent = imgInputStream.readAllBytes();
            imgInputStream.close();
        }

        try {
        	        	
        	wbActi.altaSalidaTuristica(actividad, nombre, Utiles.localDateTimeToString(fechaYHoraSalida), lugar,
                    Integer.valueOf(cantMaxTur), imgContent, ext);
           

            req.setAttribute("exito", "exito");

            

            var infoActividadTuristica = wbActi.obtenerDTActividadTuristicaDetalle(actividad);
            
            req.setAttribute("datosActividad", infoActividadTuristica);
            req = Utiles.insertarLoDeSiempre(req);
            
            req.getRequestDispatcher("/WEB-INF/jsp/consulta_actividad_turistica.jsp").forward(req, resp);
            return;

        } catch (NumberFormatException e) {
            req.setAttribute("motivoDeError",
                    "No se ingresaron los números de duracion o costo correctamente, cambielos y pruebe nuevamente");

        } catch (FechaAltaActividadPosteriorAFechaAltaSalidaException_Exception e) {
            req.setAttribute("motivoDeError",
                    "La fecha de la salida debe ser posterior a la fecha de alta de la actividad");

        } catch (FechaAltaSalidaPosteriorAFechaSalidaException_Exception e) {
            req.setAttribute("motivoDeError",
                    "La fecha de la salida debe ser posterior a la fecha actual");

        } catch (SalidaYaRegistradaException_Exception e) {
            req.setAttribute("motivoDeError", "Ya existe una actividad con ese nombre, cambielo y pruebe nuevamente");

        } catch (ActividadTuristicaNoAceptada_Exception e) {
            req.setAttribute("motivoDeError",
                    "La actividad a la que intentó registrar una salida no está aceptada todavía.");

        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
            req.setAttribute("motivoDeError", "No existe entidades seleccionadas. ");        
            return;
        } catch (ErrorAlProcesar_Exception e) {
        	req.setAttribute("motivoDeError", "Error genera al procesar la solicitud.");        
            return;
		}

        // En este punto si o si hay error
        req.setAttribute("actividad", actividad);
        req.setAttribute("nombre", nombre);
        // req.setAttribute("fechaYHoraSalida", fechaYHoraSalida);
        req.setAttribute("lugar", lugar);
        req.setAttribute("fechaSalida", fechaSalida);
        req.setAttribute("horaSalida", horaSalida);
        req.setAttribute("cantMaxTur", cantMaxTur);
        
        this.procesarGet(actividad, req, resp); 

    }

}
