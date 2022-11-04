package servlets;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.maestroservices.ObjetoNoExisteEnTurismoUy_Exception;
import publicar.maestroservices.WebServiceMaestro;
import publicar.maestroservices.WebServiceMaestroService;
import utils.Utiles;

/**
 * Servlet implementation class ImageneServlet
 */
@WebServlet("/Imagen")
public class ImageneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private WebServiceMaestro wbMaster;
    
    public ImageneServlet() {
        super();
        wbMaster = new WebServiceMaestroService().getWebServiceMaestroPort();
    }

    /**
     * parametros posibles:
     * - href : path relativo de la imagen
     * 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String href = (String) request.getParameter("href");
		try {
			String ext = Utiles.devolverExtencionDelNombreDeArchivo(href);
			byte[] content = wbMaster.getImg(href);
			response.setContentType("image/"+ext);
			response.setContentLength((int) content.length);
			OutputStream out = response.getOutputStream();
			out.write(content);
			out.close();			
		} catch (ObjetoNoExisteEnTurismoUy_Exception e) {

		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
