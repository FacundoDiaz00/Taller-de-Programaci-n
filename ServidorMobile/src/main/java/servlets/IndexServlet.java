package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.paqueteturisticasservice.WebServicePaquetes;
import publicar.paqueteturisticasservice.WebServicePaquetesService;



/**
 * Servlet implementation class IndexServlet
 * 
 * 
 * 
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceActividades wbActi;
    private WebServicePaquetes wbPack;

    public IndexServlet() {
        super();
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
        wbPack = new WebServicePaquetesService().getWebServicePaquetesPort();
    }

    /**
     * parametros posibles:
     * - idDepartamento : codigo del departamento
     * - idCategoria : codigo de la categoria a buscar
     * 
     * 
     * Observacion:
     * - En caso de no haber idDepartamento ni idCategoria, se toma como
     * idDepartamento = nombre del primer departamento
     * - No se pueden pasar en simultanio el idDepartamento ni el idCategoria
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        var sessionClosed = req.getParameter("sesionCerrada");
        if (sessionClosed != null && sessionClosed.equals("true")) {
            HttpSession sesion = req.getSession(false);
            sesion.setAttribute("usuarioLogeado", null);
            sesion.invalidate();
        }

        req.getRequestDispatcher("/WEB-INF/jsp/iniciar_sesion.jsp").forward(req, resp);
    }

}
