package servlets;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.paqueteturisticasservice.CompraYaRegistradaException_Exception;
import publicar.paqueteturisticasservice.DtPaqueteDetalles;
import publicar.paqueteturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.paqueteturisticasservice.PaquetesSinActividadesExcepcion_Exception;
import publicar.paqueteturisticasservice.WebServicePaquetes;
import publicar.paqueteturisticasservice.WebServicePaquetesService;
import publicar.usuarioturisticasservice.DtTurista;
import publicar.usuarioturisticasservice.DtUsuario;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/CompraPaquete")
public class CompraPaqueteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServicePaquetes wbPaquetes;

    public CompraPaqueteServlet() {
        super();
        wbPaquetes = new WebServicePaquetesService().getWebServicePaquetesPort();
    }

    /**
     * 
     * 
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        int cant_turistas = Integer.valueOf(req.getParameter("cant_turistas"));
        String nombre_paquete = (String) req.getParameter("nombre_paquete");
        DtUsuario turi = (DtUsuario) req.getSession().getAttribute("usuarioLogeado");
        String nickTuri = "";
        if (turi != null && turi instanceof DtTurista) {
            nickTuri = turi.getNickname();
        } else {
            resp.sendRedirect("index");
            return;
        }

        try {
            wbPaquetes.comprarPaquete(nickTuri, nombre_paquete,
                    cant_turistas);
            req.setAttribute("exito", "exito");
            DtPaqueteDetalles paquete = wbPaquetes.obtenerDtPaqueteDetalle(nombre_paquete);
            req.setAttribute("paquete", paquete);

            resp.sendRedirect("ConsultaPaquete?id=" + URLEncoder.encode(nombre_paquete, "UTF-8") + "&mostrarMensajeConfirmacionCompra=true");
            return;
        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
            resp.sendRedirect("ConsultaPaquete?id=" + URLEncoder.encode(nombre_paquete, "UTF-8") 
            	+ "&mensajeDeError=" + URLEncoder.encode("Para comprar un paquete es necesario estar loggeado con un Turista", "UTF-8"));           
        } catch (CompraYaRegistradaException_Exception e) {
        	resp.sendRedirect("ConsultaPaquete?id=" + URLEncoder.encode(nombre_paquete, "UTF-8") 
        		+ "&mensajeDeError=" + URLEncoder.encode("El usuario logueado ya ha comprado este mismo paquete.", "UTF-8"));
        } catch (PaquetesSinActividadesExcepcion_Exception e) {
        	resp.sendRedirect("ConsultaPaquete?id=" + URLEncoder.encode(nombre_paquete, "UTF-8") 
        		+ "&mensajeDeError=" + URLEncoder.encode("El paquete no puede ser comprado, ya que no tiene asociada ninguna actividad turística.", "UTF-8"));
        }
    }
}
