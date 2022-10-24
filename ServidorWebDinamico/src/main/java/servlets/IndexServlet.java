package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.IControladorPaquete;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaquete;

/**
 * Servlet implementation class IndexServlet
 * 
 * 
 * 
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorActividadTuristica contrAct;
    private IControladorPaquete contrPack;

    public IndexServlet() {
        super();
        contrAct = Fabrica.getInstancia().getIControladorActividadTuristica();
        contrPack = Fabrica.getInstancia().getIControladorPaquete();
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

        var departamentos = contrAct.obtenerIdDepartamentos();
        var categorias = contrAct.obtenerIdCategorias();

        String departamentoElegido = (String) req.getParameter("idDepartamento");
        String categoriaElegida = (String) req.getParameter("idCategoria");

        if (departamentoElegido == null && categoriaElegida == null) {
            req.setAttribute("idDepartamento", departamentos.get(0));
        } else if (departamentoElegido != null && categoriaElegida != null) {
            req.setAttribute("motivoDeError",
                    "No se puede ingresar el parametro idDepartamento y idCategoria a la vez");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
        } else {
            req.setAttribute("idDepartamento", req.getParameter("idDepartamento"));
            req.setAttribute("idCategoria", req.getParameter("idCategoria"));
        }

        departamentoElegido = (String) req.getAttribute("idDepartamento");
        categoriaElegida = (String) req.getAttribute("idCategoria");

        List<DTActividadTuristica> actividades;
        List<DTPaquete> paquetes;

        try {
            if (departamentoElegido != null) {
                paquetes = contrPack.obtenerDTPaquetes(); // No se filtra
                actividades = contrAct.obtenerDTActividadesTuristicasConfirmadasPorDepartamento(departamentoElegido);
            } else {
                paquetes = contrPack.obtenerDTPaquetesPorCategoria(categoriaElegida);
                actividades = contrAct.obtenerDTActividadesTuristicasConfirmadasPorCategoria(categoriaElegida);
            }
        } catch (ObjetoNoExisteEnTurismoUy e) {
            req.setAttribute("motivoDeError", "El nombre de la categoria/departamento no existe en el sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("departamentos", departamentos);
        req.setAttribute("categorias", categorias);
        req.setAttribute("actividades", actividades);
        req.setAttribute("paquetes", paquetes);

        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }

}
