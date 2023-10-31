package servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import publicar.paqueteturisticasservice.DtPaqueteDetalles.Actividades.Entry;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publicar.paqueteturisticasservice.DtActividadTuristica;
import publicar.paqueteturisticasservice.DtPaqueteDetalles;
import publicar.paqueteturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.paqueteturisticasservice.WebServicePaquetes;
import publicar.paqueteturisticasservice.WebServicePaquetesService;
import publicar.usuarioturisticasservice.DtTurista;
import publicar.usuarioturisticasservice.DtUsuario;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;
import utils.Utile;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/ConsultaPaquete")
public class ConsultaPaqueteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServicePaquetes wbPaquetes;
    private WebServiceUsuarios wbUsuarios;

    public ConsultaPaqueteServlet() {
        super();
        wbPaquetes = new WebServicePaquetesService().getWebServicePaquetesPort();
        wbUsuarios = new WebServiceUsuariosService().getWebServiceUsuariosPort();
    }
        

    /**
     * parametros posibles: - String id -> nombre del paquete consultado
     * 
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        try {
        	
            String identificador = (String) req.getParameter("id");
            System.out.println(identificador);
            identificador  = URLDecoder.decode(identificador , "UTF-8");
            String mostrarMensajeConfirmacionCompra = (String) req.getParameter("mostrarMensajeConfirmacionCompra");
            if(mostrarMensajeConfirmacionCompra != null && !mostrarMensajeConfirmacionCompra.toUpperCase().trim().equals("TRUE")) {
            	mostrarMensajeConfirmacionCompra = null;
            }
            String mensajeError= (String) req.getParameter("mensajeDeError");
            DtPaqueteDetalles paquete = wbPaquetes.obtenerDtPaqueteDetalle(identificador);
            
            HttpSession sesion = req.getSession(false);
            DtUsuario user = (DtUsuario)sesion.getAttribute("usuarioLogeado");
        	if(sesion != null && sesion.getAttribute("usuarioLogeado") != null) {
        		user = (DtUsuario)sesion.getAttribute("usuarioLogeado");
                boolean marcarActividadComoFav = Boolean.valueOf(req.getParameter("marcarComoFav"));
                String nomActividad = (String) req.getParameter("nomAct");
	                if(marcarActividadComoFav) {
	                	wbUsuarios.agregarOEliminarActividadDeFavoritos(user.getNickname(), nomActividad);
	                }
        	}
        	
            Map<String, Boolean> actividadPerteneceAFavoritos = new HashMap<String, Boolean>();
            HashMap<String, DtActividadTuristica> actividades = new HashMap<>();
            
            for (Entry ent: paquete.getActividades().getEntry()) {
            	actividades.put((String)ent.getKey(),ent.getValue());
            }
        	
            if(sesion != null && user != null && user instanceof DtTurista) {
            	for(var actividad: actividades.keySet()) {
            		actividadPerteneceAFavoritos.put(actividad, wbUsuarios.perteneceAFavoritosDeTurista(user.getNickname(), actividad));
            	}
            	
            	req.setAttribute("actividadFavorito", actividadPerteneceAFavoritos);
            }
            req.setAttribute("paquete", paquete);

            req = Utile.insertarLoDeSiempre(req);
            req.setAttribute("exito", mostrarMensajeConfirmacionCompra);
            req.setAttribute("motivoDeError", mensajeError);
            req.setAttribute("mensajeError", mensajeError);
            req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_paquete.jsp").forward(req, resp);
        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
            req.setAttribute("motivoDeError", "No existe un paquete con ese nombre");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
        } catch (publicar.usuarioturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
        	req.setAttribute("motivoDeError", "El usuario logeado no pertenece al sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
		}
    }
}
