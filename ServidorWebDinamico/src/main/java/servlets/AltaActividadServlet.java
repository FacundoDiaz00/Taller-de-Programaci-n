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

import net.java.dev.jaxb.array.StringArray;
import publicar.actividadesturisticasservice.ActividadTuristicaYaRegistradaException_Exception;
import publicar.actividadesturisticasservice.ErrorAlProcesar_Exception;
import publicar.actividadesturisticasservice.Imagen;
import publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.usuarioturisticasservice.DtProveedor;
import publicar.usuarioturisticasservice.DtUsuario;
import utils.Utiles;

/**
 * Servlet implementation class AltaDeUsuario
 */
@WebServlet("/AltaDeActividad")
@MultipartConfig
public class AltaActividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceActividades wbActi;

    public AltaActividadServlet() {
        super();
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
    }
    
    void accionDoGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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
        DtUsuario usuario = (DtUsuario) req.getSession().getAttribute("usuarioLogeado");
        if (usuario == null || !(usuario instanceof DtProveedor)) {
            req = Utiles.insertarLoDeSiempre(req);
            resp.sendRedirect("index");
            return;
        }

        String nickProveedor = ((DtUsuario) req.getSession().getAttribute("usuarioLogeado")).getNickname();
        String departamento = req.getParameter("departamento");
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");
        String duracion = req.getParameter("duracion");
        String costo = req.getParameter("costo");
        String ciudad = req.getParameter("ciudad");
        String url = req.getParameter("url");
        
        if(req.getParameterValues("categorias") == null) {
        	req.setAttribute("motivoDeError", "Se debe seleccionar al menos una categoria");
        } else  {
        	 List<String> categorias = Arrays.asList(req.getParameterValues("categorias"));

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
            	 
            	 StringArray categoriasListOfString = new StringArray();
            	 for (String  cat : categorias) {
            		 categoriasListOfString.getItem().add(cat);
				}

				 wbActi.altaActividadTuristica(nickProveedor, departamento, nombre, descripcion,
							  Integer.valueOf(duracion), Float.valueOf(costo), ciudad, imgContent, ext, categoriasListOfString, url);

                 req.setAttribute("exito", "exito");

                 this.accionDoGet(req, resp);
                 return;
             } catch (NumberFormatException e) {
                 req.setAttribute("motivoDeError",
                         "No se ingresaron los números de duracion o costo correctamente, cambielos y pruebe nuevamente");
             } catch (ActividadTuristicaYaRegistradaException_Exception e) {
                 req.setAttribute("motivoDeError", "Ya existe una actividad con ese nombre, cambielo y pruebe nuevamente");
             } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
                req.setAttribute("motivoDeError",
                          "No existe uno de los objetos enviados");
                
             } catch(ErrorAlProcesar_Exception e) {
            	 req.setAttribute("motivoDeError",
                         "Error general al procesar la solicitud");
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
        req.setAttribute("url", url);
        
        req = Utiles.insertarLoDeSiempre(req);
        req.getRequestDispatcher("/WEB-INF/jsp/alta_de_actividad_turistica.jsp").forward(req, resp);

    }

}
