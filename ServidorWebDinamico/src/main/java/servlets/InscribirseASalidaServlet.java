package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excepciones.ContraseniaInvalidaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTSalidaTuristica;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/InscribiseASalida")
public class InscribirseASalidaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorActividadTuristica contrAT;

    public InscribirseASalidaServlet() {
        super();
        contrAT = Fabrica.getInstancia().getIControladorActividadTuristica();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String nombSalida = (String) req.getParameter("id");
    	
    	try {
    		DTSalidaTuristica salidaTuristica = this.contrAT.obtenerDTSalidaTuristica(nombSalida);
    		req.setAttribute("salida", salidaTuristica);
    	} catch (ObjetoNoExisteEnTurismoUy e) {
            req.setAttribute("motivoDeError", "No existe la salida turistica");
            req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_salida.jsp").forward(req, resp);
        } 
    	
    	
    	System.out.println("Nombre salida:" + nombSalida);
    	req = Utiles.insertarLoDeSiempre(req);
        req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("Entré al post");
        String formaDePago = (String) req.getParameter("formaPago");
        
	    //try {
	    	if(formaDePago.equals("0")) {
	    		//this.contrAT.altaInscripcionSalidaTuristica(formaDePago, formaDePago, 0, null, formaDePago);
	    	}else {
	    		
	    	}
//	    }catch(){
//	    	
//	    }
	    //resp.sendRedirect("index");
	    //req.setAttribute("motivoDeError", "La contrasenia es incorrecta");
	    //req.getRequestDispatcher("/WEB-INF/jsp/iniciar_sesion.jsp").forward(req, resp);
       
    }

}