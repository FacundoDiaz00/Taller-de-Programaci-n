package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publicar.actividadesturisticasservice.DtActividadTuristicaDetalle;
import publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.usuarioturisticasservice.DtUsuario;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/ConsultaActividad")
public class ConsultaActividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceActividades wbActi;
    private WebServiceUsuarios wbUsuarios;

    public ConsultaActividadServlet() {
        super();
        
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
        wbUsuarios = new WebServiceUsuariosService().getWebServiceUsuariosPort();
    }

    /**
     * parametros posibles: - id = identificador de la actividad a mostrar
     * 
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }
        
        boolean finalizar = Boolean.valueOf(req.getParameter("finalizar"));
        boolean marcarFav = Boolean.valueOf(req.getParameter("marcarComoFav"));
        String idActividad = (String) req.getParameter("id");
        DtActividadTuristicaDetalle infoActividadTuristica;
        boolean esFavorito = false;
        HttpSession sesion = req.getSession(false); 
        if(marcarFav) {

        	if(sesion.getAttribute("usuarioLogeado") != null) {
        		try {
        			
        			DtUsuario user = (DtUsuario)sesion.getAttribute("usuarioLogeado");
        			wbUsuarios.agregarOEliminarActividadDeFavoritos(user.getNickname(), idActividad);
	             
                } catch (publicar.usuarioturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
                    req.setAttribute("motivoDeError", "La actividad que se desea marcar/desmarcar como favorita no existe en el sistema");
                    req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
                } 
        	}
        		
        }
        try {
        	DtUsuario user = (DtUsuario)sesion.getAttribute("usuarioLogeado");
        	
            infoActividadTuristica = wbActi.obtenerDTActividadTuristicaDetalle(idActividad);
			esFavorito = wbUsuarios.perteneceAFavoritosDeTurista(user.getNickname(), idActividad);
			req.setAttribute("esFavoritaActividad", esFavorito);
	        

        } catch (publicar.usuarioturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
            req.setAttribute("motivoDeError",
                    "el usuario logeado es inválido");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
			// TODO Auto-generated catch block
        	req.setAttribute("motivoDeError",
                    "id de actividad invalido. No existe una actividad turistica con este nombre en el sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
		} 
        
        req = Utiles.insertarLoDeSiempre(req);
        
        
       
        

        req.setAttribute("datosActividad", infoActividadTuristica);
//        req.setAttribute("sePuedeFinalizar", sePuedeFinalizar);
        req.getRequestDispatcher("/WEB-INF/jsp/consulta_actividad_turistica.jsp").forward(req, resp);

    }
      
}
