package servlets;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.actividadesturisticasservice.ErrorAlProcesar_Exception;
import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.maestroservices.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.maestroservices.WebServiceMaestro;
import publicar.maestroservices.WebServiceMaestroService;
import utils.Utile;

/**
 * Servlet implementation class ImageneServlet
 */
@WebServlet("/CertificadoInscripcion")
public class CertificadoInscripcionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private WebServiceActividades wbActi;
    
    public CertificadoInscripcionServlet() {
        super();
        wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
    }

    /**
     * parametros posibles:
     * - href : path relativo de la imagen
     * 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		String nickname = (String) request.getParameter("idTurista");
		String nombreSalida = (String) request.getParameter("idSalida");
	
			byte[] content;
			try {
				content = wbActi.getComprobanteInscripcion(nickname, nombreSalida);
				response.setContentType("application/pdf");
				response.setContentLength((int) content.length);
				OutputStream out = response.getOutputStream();
				out.write(content);
				out.close();	
			} catch (ErrorAlProcesar_Exception e) {
				request.setAttribute("motivoDeError", "No se pudo generar el PDF");
				request.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(request, response);
				e.printStackTrace();
			} catch (publicar.actividadesturisticasservice.ObjetoNoExisteEnTurismoUy_Exception e) {
				request.setAttribute("motivoDeError", "Objetos faltantes");
				request.getRequestDispatcher("/WEB-INF/jsp/errores/400.jsp").forward(request, response);
				e.printStackTrace();
			}
					
	
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
