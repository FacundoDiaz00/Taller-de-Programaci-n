package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publicar.actividadesturisticasservice.WebServiceActividades;
import publicar.actividadesturisticasservice.WebServiceActividadesService;
import publicar.maestroservices.DtResultadoBusqueda;
import publicar.maestroservices.TipoOrdenacion;
import publicar.maestroservices.WebServiceMaestro;
import publicar.maestroservices.WebServiceMaestroService;
import utils.Utile;

/**
 * Servlet implementation class busquedaServlet
 */
@WebServlet("/busqueda")
public class BusquedaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebServiceMaestro wbMaestro;
	private WebServiceActividades wbActi;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BusquedaServlet() {
		super();
		wbMaestro = new WebServiceMaestroService().getWebServiceMaestroPort();
		wbActi = new WebServiceActividadesService().getWebServiceActividadesPort();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getCharacterEncoding() == null) {
			req.setCharacterEncoding("UTF-8");
		}

		List<String> departamentos = wbActi.obtenerIdDepartamentos().getItem();
		List<String> categorias = wbActi.obtenerIdCategorias().getItem();
		req.setAttribute("departamentos", departamentos);
		req.setAttribute("categorias", categorias);

		req = Utile.insertarLoDeSiempre(req);
		String filtro = (String) req.getParameter("busqueda");
		String depto = (String) req.getParameter("departamentoFiltro");
		String cat = (String) req.getParameter("categoriaFiltro");
		String ord = (String) req.getParameter("tipoOrdenacion");
		if (ord == null) {
			ord = "1";
		}
		if (filtro == null) {
			filtro = "";
		}
		if (depto == null) {
			depto = "";
		}
		if (cat == null) {
			cat = "";
		}
		TipoOrdenacion Tord;
		if (ord.equals("1")) {
			Tord = TipoOrdenacion.ALFABETICAMENTE;
		} else {
			Tord = TipoOrdenacion.FECHA_PUBLICACION;
		}

		DtResultadoBusqueda result = wbMaestro.buscar(filtro, depto, cat, Tord);
		req.setAttribute("resultadosBusqueda", result);
		req.getRequestDispatcher("/WEB-INF/jsp/busqueda.jsp").forward(req, resp);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
//		req.getRequestDispatcher("/WEB-INF/jsp/busqueda.jsp").forward(req, res);

	}

}
