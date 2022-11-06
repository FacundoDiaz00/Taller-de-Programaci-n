package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publicar.actividadesturisticasservice.DtActividadTuristica;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.paqueteturisticasservice.WebServicePaquetes;
import publicar.paqueteturisticasservice.WebServicePaquetesService;
import publicar.usuarioturisticasservice.DtTurista;
import publicar.usuarioturisticasservice.DtUsuario;
import publicar.usuarioturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;



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
    private WebServiceUsuarios wbUsuarios;

    public IndexServlet() {
        super();
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
        wbPack = new WebServicePaquetesService().getWebServicePaquetesPort();
        wbUsuarios = new WebServiceUsuariosService().getWebServiceUsuariosPort();
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
        DtUsuario user = null;

        var sessionClosed = req.getParameter("sesionCerrada");
        if (sessionClosed != null && sessionClosed.equals("true")) {
            HttpSession sesion = req.getSession(false);
            sesion.setAttribute("usuarioLogeado", null);
            sesion.invalidate();
        }else {
        	HttpSession sesion = req.getSession(false);
        	if(sesion != null && sesion.getAttribute("usuarioLogeado") != null) {
        		user = (DtUsuario)sesion.getAttribute("usuarioLogeado");
                boolean marcarActividadComoFav = Boolean.valueOf(req.getParameter("marcarComoFav"));
                String nomActividad = (String) req.getParameter("nomAct");
                try {
	                if(marcarActividadComoFav) {
	                	wbUsuarios.agregarOEliminarActividadDeFavoritos(user.getNickname(), nomActividad);
	                }
                } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
                    req.setAttribute("motivoDeError", "La actividad que se desea marcar/desmarcar como favorita no existe en el sistema");
                    req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
                }
        	}
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

        List<publicar.actividadesturisticasservice.DtActividadTuristica> actividades = null;
        List<publicar.paqueteturisticasservice.DtPaquete> paquetes = null;

        try {
            if (departamentoElegido != null) {
                paquetes = wbPack.obtenerDtPaquetes().getPaquetes();
                actividades = wbActi.obtenerDTActividadesTuristicasConfirmadasPorDepartamento(departamentoElegido).getActividadTuristicas();
            } else {
                paquetes =  wbPack.obtenerDTPaquetesPorCategoria(categoriaElegida).getPaquetes();
                actividades = wbActi.obtenerDTActividadesTuristicasConfirmadasPorCategoria(categoriaElegida).getActividadTuristicas();
            }
            
            HttpSession sesion = req.getSession(false);
            Map<DtActividadTuristica, Boolean> actividadPerteneceAFavoritos = new HashMap<DtActividadTuristica, Boolean>();
            if(sesion != null && user != null && user instanceof DtTurista) {
            	for(var actividad: actividades) {
            		actividadPerteneceAFavoritos.put(actividad, wbUsuarios.perteneceAFavoritosDeTurista(user.getNickname(), actividad.getNombre()));
            	}
            	
            	req.setAttribute("actividadFavorito", actividadPerteneceAFavoritos);
            }
        } catch (publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
            req.setAttribute("motivoDeError", "El nombre de la departamento no existe en el sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
        } catch (publicar.paqueteturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
            req.setAttribute("motivoDeError", "El nombre de la categoria no existe en el sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        req.setAttribute("departamentos", departamentos);
        req.setAttribute("categorias", categorias);
        req.setAttribute("actividades", actividades);
        req.setAttribute("paquetes", paquetes);

        req.getRequestDispatcher("").forward(req, resp);
    }

}
