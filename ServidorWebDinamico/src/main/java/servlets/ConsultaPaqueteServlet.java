package servlets;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.paqueteturisticasservice.DtPaqueteDetalles;
import publicar.paqueteturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.paqueteturisticasservice.WebServicePaquetes;
import publicar.paqueteturisticasservice.WebServicePaquetesService;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/ConsultaPaquete")
public class ConsultaPaqueteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServicePaquetes wbPaquetes;

    public ConsultaPaqueteServlet() {
        super();
        wbPaquetes = new WebServicePaquetesService().getWebServicePaquetesPort();
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
            req.setAttribute("paquete", paquete);

            req = Utiles.insertarLoDeSiempre(req);
            req.setAttribute("exito", mostrarMensajeConfirmacionCompra);
            req.setAttribute("motivoDeError", mensajeError);
            req.setAttribute("mensajeError", mensajeError);
            req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_paquete.jsp").forward(req, resp);
        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
            req.setAttribute("motivoDeError", "No existe un paquete con ese nombre");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
        }
    }
}
