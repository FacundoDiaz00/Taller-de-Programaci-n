package logica.manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.entidades.ActividadTuristica;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorActividadTuristica {
	private static ManejadorActividadTuristica instancia;

	private Map<String, ActividadTuristica> actividades;

	private ManejadorActividadTuristica() {
		actividades = new HashMap<String, ActividadTuristica>();
	}

	public static ManejadorActividadTuristica getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorActividadTuristica();
		}
		return instancia;
	}

	public List<ActividadTuristica> getActividades() {
		return new ArrayList<ActividadTuristica>(actividades.values());
	}

	public Set<String> obtenerIdActividadesTuristicas() {
		return actividades.keySet();
	}

	public void addActividad(ActividadTuristica actividad) {
		actividades.put(actividad.getNombre(), actividad);
	}

	public ActividadTuristica getActividad(String nombre) throws ObjetoNoExisteEnTurismoUy {
		if (actividades.containsKey(nombre))
			return actividades.get(nombre);
		else {
			throw new ObjetoNoExisteEnTurismoUy(ActividadTuristica.class);
		}
	}

	public boolean exists(String nombreAct) {
		return actividades.containsKey(nombreAct);

	}

	public ActividadTuristica obtenerActividadTuristica(String IDActividad) {
		return actividades.get(IDActividad);
	}
	
	public void removeActividad(String nombre) {
		actividades.remove(nombre);
	}
}
