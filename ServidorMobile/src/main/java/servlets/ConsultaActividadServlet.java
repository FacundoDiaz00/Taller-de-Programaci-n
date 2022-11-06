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
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/ConsultaActividad")
public class ConsultaActividadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebServiceActividades wbActi;
    private WebServicePaquetes wbPack;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultaActividadServlet() {
        super();
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
        wbPack = new WebServicePaquetesService().getWebServicePaquetesPort();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	if((String)req.getParameter("listar") == "true" && req.getParameter("id") != null)  {
    		
    		
    	}else {
        	if (req.getCharacterEncoding() == null) {
                req.setCharacterEncoding("UTF-8");
            }

            var sessionClosed = req.getParameter("sesionCerrada");
            if (sessionClosed != null && sessionClosed.equals("true")) {
                HttpSession sesion = req.getSession(false);
                sesion.setAttribute("usuarioLogeado", null);
                sesion.invalidate();
            }

            List<String> departamentos = wbActi.obtenerIdDepartamentos().getItem();
            List<String> categorias = wbActi.obtenerIdCategorias().getItem();

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

            List<publicar.actividadesturisticasservice.DtActividadTuristica> actividades;
            List<publicar.paqueteturisticasservice.DtPaquete> paquetes;

            try {
                if (departamentoElegido != null) {
                    paquetes = wbPack.obtenerDtPaquetes().getPaquetes();
                    actividades = wbActi.obtenerDTActividadesTuristicasConfirmadasPorDepartamento(departamentoElegido).getActividadTuristicas();
                } else {
                    paquetes =  wbPack.obtenerDTPaquetesPorCategoria(categoriaElegida).getPaquetes();
                    actividades = wbActi.obtenerDTActividadesTuristicasConfirmadasPorCategoria(categoriaElegida).getActividadTuristicas();
                }
            } catch (publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
                req.setAttribute("motivoDeError", "El nombre de la departamento no existe en el sistema");
                req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
                return;
            } catch (publicar.paqueteturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
                req.setAttribute("motivoDeError", "El nombre de la categoria no existe en el sistema");
                req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("departamentos", departamentos);
            req.setAttribute("categorias", categorias);
            req.setAttribute("actividades", actividades);
            req.setAttribute("paquetes", paquetes);

            req.getRequestDispatcher("/WEB-INF/jsp/listar_actividades.jsp").forward(req, resp);
    	}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
