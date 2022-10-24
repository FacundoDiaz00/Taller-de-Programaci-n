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

        String idActividad = (String) req.getParameter("id");

        DTActividadTuristicaDetalle infoActividadTuristica;
        try {
            infoActividadTuristica = this.contAct.obtenerDTActividadTuristicaDetalle(idActividad);

        } catch (ObjetoNoExisteEnTurismoUy e) {
            req.setAttribute("motivoDeError",
                    "id de actividad invalido. No existe una actividad turistica con este nombre en el sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
        }

        req = Utiles.insertarLoDeSiempre(req);

        req.setAttribute("datosActividad", infoActividadTuristica);
        req.getRequestDispatcher("/WEB-INF/jsp/consulta_actividad_turistica.jsp").forward(req, resp);

    }
}
