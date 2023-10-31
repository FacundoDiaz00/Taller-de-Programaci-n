package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.actividadesturisticasservice.DtMapActividadSalidaTuristicaCollection;
import publicar.actividadesturisticasservice.DtSalidaTuristica;
import publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import utils.Utile;

@WebServlet("/ConsultaSalida")
public class ConsultaSalidaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceActividades wbActi;

    public ConsultaSalidaServlet() {
        super();
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }
        
        String debelistar = req.getParameter("listar");
    	if(debelistar == null || !debelistar.equals("false"))  {
    		
    		try {
				DtMapActividadSalidaTuristicaCollection salidas = wbActi.obtenerDTSalidasTuristicas();				
				req.setAttribute("datosSalida", salidas);
				req.getRequestDispatcher("/WEB-INF/jsp/listar_salidas.jsp").forward(req, resp);				
			} catch (ObjetoNoExisteEnTurismoUy_Exception e) {
                req.setAttribute("motivoDeError",
                        "No exite alguna entidad");
                req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
                e.printStackTrace();               				
			}
    		
    		
    		
    		
    	} else {
    		String idSalida = (String) req.getParameter("id");

    	    DtSalidaTuristica infoSalidaTuristica;
    	    try {
    	       infoSalidaTuristica = wbActi.obtenerDTSalidaTuristicaDetalle(idSalida);
    	    } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
    	        req.setAttribute("motivoDeError",
    	                "Id de salida invalido. No existe una salida turistica con este nombre en el sistema");
    	        req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
    	        return;
    	    }

    	    req.setAttribute("datosSalida", infoSalidaTuristica);
    	    req.getRequestDispatcher("/WEB-INF/jsp/consulta_salida_turistica.jsp").forward(req, resp);

    	}
        
        

       
    }

}
