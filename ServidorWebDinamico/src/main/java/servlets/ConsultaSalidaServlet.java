package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import utils.Utiles;


@WebServlet("/ConsultaSalida")
public class ConsultaSalidaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IControladorActividadTuristica contAct;
       

    public ConsultaSalidaServlet() {
        super();
        this.contAct = Fabrica.getInstancia().getIControladorActividadTuristica();
    }

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idSalida = (String) req.getParameter("id");
		
		DTSalidaTuristicaDetalle infoSalidaTuristica;
		try {
			infoSalidaTuristica = this.contAct.obtenerDTSalidaTuristicaDetalle(idSalida);
		} catch(ObjetoNoExisteEnTurismoUy e) {
			req.setAttribute("motivoDeError",
					"Id de salida invalido. No existe una salida turistica con este nombre en el sistema");
			req.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(req, resp);
			return;
		}
		
		req = Utiles.insertarLoDeSiempre(req);

		req.setAttribute("datosSalida", infoSalidaTuristica);
		req.getRequestDispatcher("/WEB-INF/jsp/consulta_de_salida_turistica.jsp").forward(req, resp);
		
	}



}
