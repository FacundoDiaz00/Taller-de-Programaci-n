package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import publicar.actividadesturisticasservice.DtSalidaTuristica;
import publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.usuarioturisticasservice.DtTurista;
import publicar.usuarioturisticasservice.DtUsuario;
import publicar.usuarioturisticasservice.WebServiceUsuarios;
import publicar.usuarioturisticasservice.WebServiceUsuariosService;
import utils.Utiles;

@WebServlet("/ConsultaSalida")
public class ConsultaSalidaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private WebServiceActividades wbActi;
    private WebServiceUsuarios wbUsuarios;

    public ConsultaSalidaServlet() {
        super();
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
        wbUsuarios = new WebServiceUsuariosService().getWebServiceUsuariosPort();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCharacterEncoding() == null) {
            req.setCharacterEncoding("UTF-8");
        }

        String idSalida = (String) req.getParameter("id");

        DtSalidaTuristica infoSalidaTuristica = null;
        try {
        	boolean esFavorito = false;
            HttpSession sesion = req.getSession(false); 
        	DtUsuario user = (DtUsuario)sesion.getAttribute("usuarioLogeado");
            infoSalidaTuristica = wbActi.obtenerDTSalidaTuristicaDetalle(idSalida);
            
        	if(sesion != null && sesion.getAttribute("usuarioLogeado") != null) {
        		user = (DtUsuario)sesion.getAttribute("usuarioLogeado");
                boolean marcarActividadComoFav = Boolean.valueOf(req.getParameter("marcarComoFav"));
                
                if(marcarActividadComoFav) {
                	wbUsuarios.agregarOEliminarActividadDeFavoritos(user.getNickname(), infoSalidaTuristica.getActividad());
                }
                
        	}
        	
            if(user instanceof DtTurista) {
				esFavorito = wbUsuarios.perteneceAFavoritosDeTurista(user.getNickname(), infoSalidaTuristica.getActividad());
            }
            
			req.setAttribute("esFavoritaActividad", esFavorito);
            
        } catch (ObjetoNoExisteEnTurismoUy_Exception e) {
            req.setAttribute("motivoDeError",
                    "Id de salida invalido. No existe una salida turistica con este nombre en el sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
            return;
        } catch (publicar.usuarioturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
        	req.setAttribute("motivoDeError",
                    "El usuario logeado no está registrado en el sistema");
            req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
		}

        req = Utiles.insertarLoDeSiempre(req);

        req.setAttribute("datosSalida", infoSalidaTuristica);
        req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_salida_turistica.jsp").forward(req, resp);

    }

}
