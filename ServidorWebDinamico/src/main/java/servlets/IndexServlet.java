package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excepciones.DeparamentoYaRegistradoException;
import logica.controladores.Fabrica;
import logica.controladores.IControladorActividadTuristica;

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

	public IndexServlet() {
		super();
		contrAct = Fabrica.getInstancia().getIControladorActividadTuristica();
	}

	
	/**
	 * parametros posibles:
	 * 	- idDepartamento : codigo del departamento
	 * 	- idCategoria : codigo de la categoria a buscar
	 * 
	 * 
	 * Observacion: 
	 * 	- En caso de no haber idDepartamento ni idCategoria, se toma como idDepartamento = nombre del primer departamento
	 * 	- No se pueden pasar en simultanio el idDepartamento ni el idCategoria
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		var departamentos = contrAct.obtenerIdDepartamentos();
		var categorias = contrAct.obtenerIdCategorias();
		
		
		String departamentoElegido = (String) req.getParameter("idDepartamento");
		String categoriaElegida = (String) req.getParameter("idCategoria");
		
		
		
		System.out.println();
		System.out.println(req.getParameter("idDepartamento"));
		System.out.println(req.getParameter("idCategoria"));
		
		if (departamentoElegido == null && categoriaElegida == null){
			req.setAttribute("idDepartamento", departamentos.get(0));
		} else if (departamentoElegido != null && categoriaElegida != null){
			req.getRequestDispatcher("/WEB-INF/jsp/errores/500.jsp").forward(req, resp);
		} else {
			req.setAttribute("idDepartamento", req.getParameter("idDepartamento"));
			req.setAttribute("idCategoria", req.getParameter("idCategoria"));
		}
		
		System.out.println(req.getAttribute("idDepartamento"));
		System.out.println(req.getAttribute("idCategoria"));

		
		
		var actividades = contrAct.obtenerDTActividadesTuristicas();


		req.setAttribute("departamentos", departamentos);
		req.setAttribute("actividades", actividades);
		req.setAttribute("categorias", categorias);

		req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
	}

}
