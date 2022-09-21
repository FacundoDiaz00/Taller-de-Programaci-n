package logica.manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.entidades.Categoria;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorCategoria {
	private static ManejadorCategoria instancia;

	private Map<String, Categoria> categorias;

	private ManejadorCategoria() {
		categorias = new HashMap<>();
	}

	public static ManejadorCategoria getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorCategoria();
		}
		return instancia;
	}

	public List<Categoria> getCategorias() {
		return new ArrayList<Categoria>(categorias.values());
	}

	public Set<String> obtenerIdCategorias() {
		return categorias.keySet();
	}

	public void addCategoria(Categoria categoria) {
		categorias.put(categoria.getNombre(), categoria);
	}

	public Categoria getCategoria(String nombre) throws ObjetoNoExisteEnTurismoUy {
		if (categorias.containsKey(nombre))
			return categorias.get(nombre);
		else
			throw new ObjetoNoExisteEnTurismoUy(Categoria.class);
	}

	public boolean exists(String nomCat) {
		return categorias.containsKey(nomCat);
	}

}
