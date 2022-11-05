package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.actividadesturisticasservice.DtActividadTuristicaDetalle;
import publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/ConsultaActividad")
public class ConsultaActividadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceActividades wbActi;

    public ConsultaActividadServlet() {
        super();
        
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
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

        DtActividadTuristicaDetalle infoActividadTuristica;
        try {
            infoActividadTuristica = wbActi.obtenerDTActividadTuristicaDetalle(idActividad);

        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
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
