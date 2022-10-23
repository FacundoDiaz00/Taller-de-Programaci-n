package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.CompraYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.PaquetesSinActividadesExcepcion;
import logica.controladores.Fabrica;
import logica.datatypes.DTPaqueteDetalles;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/CompraPaquete")
public class CompraPaqueteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CompraPaqueteServlet() {
        super();
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
        DTUsuario turi = (DTTurista) req.getSession().getAttribute("usuarioLogeado");
        String nickTuri = "";
        if (turi != null && turi instanceof DTTurista) {
            nickTuri = turi.getNickname();
        } else {
            resp.sendRedirect("index");
            return;
        }

        try {
            Fabrica.getInstancia().getIControladorPaquete().comprarPaquete(nickTuri, nombre_paquete,
                    cant_turistas, null);
            req.setAttribute("exito", "exito");
            DTPaqueteDetalles paquete = Fabrica.getInstancia().getIControladorPaquete()
                    .obtenerDTPaqueteDetalle(nombre_paquete);
            req.setAttribute("paquete", paquete);

            resp.sendRedirect("ConsultaPaquete?id=" + nombre_paquete + "&mostrarMensajeConfirmacionCompra=true");
            return;
        } catch (ObjetoNoExisteEnTurismoUy e) {
            String objetoFaltante = e.getClaseObjetoFaltante();
            resp.sendRedirect("ConsultaPaquete?id=" + nombre_paquete + "&mensajeDeError=" + "Para comprar un paquete es necesario estar loggeado con un Turista");           
        } catch (CompraYaRegistradaException e) {
        	resp.sendRedirect("ConsultaPaquete?id=" + nombre_paquete + "&mensajeDeError=" + "El usuario logueado ya ha comprado este mismo paquete.");
        } catch (PaquetesSinActividadesExcepcion e) {
        	resp.sendRedirect("ConsultaPaquete?id=" + nombre_paquete + "&mensajeDeError=" +  "El paquete no puede ser comprado, ya que no tiene asociada ninguna actividad turística.");
        }
    }
}
