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
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

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
        if (turi != null)
            nickTuri = turi.getNickname();

        try {
            Fabrica.getInstancia().getIControladorPaquete().comprarPaquete(nickTuri, nombre_paquete,
                    cant_turistas, null);
        } catch (ObjetoNoExisteEnTurismoUy e) {
            String objetoFaltante = e.getClaseObjetoFaltante();
            // if (objetoFaltante == "Usuario")
            req.setAttribute("motivoDeError", "Para comprar un paquete es necesario estar loggeado con un Turista");
            // else
            // req.setAttribute("motivoDeError", "No existe un " + objetoFaltante + " con
            // ese nombre.");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
        } catch (CompraYaRegistradaException e) {
            req.setAttribute("motivoDeError", "El usuario logueado ya ha comprado este mismo paquete.");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
        } catch (PaquetesSinActividadesExcepcion e) {
            req.setAttribute("motivoDeError",
                    "El paquete no puede ser comprado, ya que no tiene asociada ninguna actividad turística.");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
        }

        resp.sendRedirect("index");
    }
}
