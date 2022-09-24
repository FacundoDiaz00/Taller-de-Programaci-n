package utils;

import javax.servlet.http.HttpServletRequest;

import logica.controladores.Fabrica;

public class Utiles {

	public Utiles() {
		// TODO Auto-generated constructor stub
	}

	public static HttpServletRequest insertarLoDeSiempre(HttpServletRequest req) {
		var departamentos = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdDepartamentos();
		var categorias = Fabrica.getInstancia().getIControladorActividadTuristica().obtenerIdCategorias();

		req.setAttribute("departamentos", departamentos);
		req.setAttribute("categorias", categorias);

		// TODO: agregar los datos del usuario logueado

		return req;
	}

}
