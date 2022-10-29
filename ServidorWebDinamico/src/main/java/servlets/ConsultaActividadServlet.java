package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/ConsultaActividad")
public class ConsultaActividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorActividadTuristica contAct;

    public ConsultaActividadServlet() {
        super();
        this.contAct = Fabrica.getInstancia().getIControladorActividadTuristica();
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
        String idActividad = (String) req.getParameter("id");
        boolean sePuedeFinalizar = Boolean.valueOf(req.getParameter("actividadDeProveedor"));  
   
        DTActividadTuristicaDetalle infoActividadTuristica;
        try {
            infoActividadTuristica = this.contAct.obtenerDTActividadTuristicaDetalle(idActividad);
            
            if(finalizar) {
            	
//                req = Utiles.insertarLoDeSiempre(req);
//                req.setAttribute("sePuedeFinalizar", true);
//                req.setAttribute("finalizar", false);
//                req.setAttribute("id", idActividad);
                req.setAttribute("exito", Boolean.TRUE);
                req.getRequestDispatcher("/WEB-INF/jsp/consulta_actividad_turistica.jsp").forward(req, resp);
            }

        } catch (ObjetoNoExisteEnTurismoUy e) {
            req.setAttribute("motivoDeError",
                    "id de actividad invalido. No existe una actividad turistica con este nombre en el sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
        }
        
        req = Utiles.insertarLoDeSiempre(req);

        req.setAttribute("datosActividad", infoActividadTuristica);
        req.setAttribute("sePuedeFinalizar", sePuedeFinalizar);
        req.getRequestDispatcher("/WEB-INF/jsp/consulta_actividad_turistica.jsp").forward(req, resp);

    }
}
