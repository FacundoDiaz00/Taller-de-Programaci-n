package servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excepciones.InscripcionYaRegistradaException;
import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.CompraConConsumosInsuficientesExcepcion;
import excepciones.CompraPaqueteVencidoExcepcion;
import excepciones.ContraseniaInvalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.NoExisteConsumoParaLaActividadExcepcion;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.PaqueteNoCompradoExcepcion;
import excepciones.SuperaElMaximoDeTuristasException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;
import utils.Utiles;

/**
 * Servlet implementation class ConsultaActividadServlet
 */
@WebServlet("/InscribiseASalida")
public class InscribirseASalidaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IControladorActividadTuristica contrAT;
    private DTSalidaTuristica salida = null;

    public InscribirseASalidaServlet() {
        super();
        contrAT = Fabrica.getInstancia().getIControladorActividadTuristica();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String nombSalida = (String) req.getParameter("id");
    	
    	try {
    		
    		salida = this.contrAT.obtenerDTSalidaTuristica(nombSalida);
    		req.setAttribute("salida", salida);
    		req = Utiles.insertarLoDeSiempre(req);
    		
    	} catch (ObjetoNoExisteEnTurismoUy e) {
            req.setAttribute("motivoDeError", "No existe la salida turistica");
            req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_salida.jsp").forward(req, resp);
        } 
    	
        req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("Entré al post");
        String formaDePago = (String) req.getParameter("formaPago");
        int cantTuristas = Integer.parseInt(req.getParameter("cantTuristas"));
		DTUsuario turi = (DTTurista) req.getSession().getAttribute("usuarioLogeado");
		String nickTuri = "";
		if (turi != null)
			 nickTuri = turi.getNickname();
		LocalDate fechaInscripcion = LocalDate.now();
		String nombrePaquete = null;
		if(formaDePago.equals("1")) {
    		nombrePaquete = (String) req.getParameter("paquete");
    	}

		req = Utiles.insertarLoDeSiempre(req);
		req.setAttribute("salida", this.salida);

	    try {
	    	contrAT.altaInscripcionSalidaTuristica(salida.getNombre(),nickTuri,cantTuristas ,fechaInscripcion,nombrePaquete);

	        req.setAttribute("exito", Boolean.TRUE);
            req = Utiles.insertarLoDeSiempre(req);
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(InscripcionYaRegistradaException e){
	    	req.setAttribute("motivoDeError", "Usuario ya inscripto para esta salida turística");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(SuperaElMaximoDeTuristasException e) {
	    	req.setAttribute("motivoDeError", "La cantidad de turistas ingresadas supera el máximo");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(FechaAltaSalidaTuristicaPosteriorAFechaInscripcion e) {
	    	req.setAttribute("motivoDeError", "La fecha de la salida turística no puede ser posterior a la fecha de inscripción");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(AltaInscripcionPosteriorAFechaSalidaException e){
	    	req.setAttribute("motivoDeError", "La fecha de la inscripción no puede ser posterior a la fecha de la salida asociada");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(PaqueteNoCompradoExcepcion e) {
	    	req.setAttribute("motivoDeError", "Se debe comprar el paquete para poder inscribirse a la salida asociada");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(CompraPaqueteVencidoExcepcion e) {
	    	req.setAttribute("motivoDeError", "La compra asociada a la salida turistica se encuentra vencida");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(CompraConConsumosInsuficientesExcepcion e) {
	    	req.setAttribute("motivoDeError", "La compra asociada a la salida turisitica se encuentra sin consumos disponibles");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(NoExisteConsumoParaLaActividadExcepcion e) {
	    	req.setAttribute("motivoDeError", "No existe ningún consumo para la actividad asociada a la salida seleccionada");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }catch(ObjetoNoExisteEnTurismoUy e){
	    	req.setAttribute("motivoDeError", "No existe la salida a la que se desea inscribir");
            req.getRequestDispatcher("/WEB-INF/jsp/inscribirse_a_salida.jsp").forward(req, resp);
	    }   
	   
       
    }

}