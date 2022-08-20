package logica.manejadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logica.entidades.ActividadTuristica;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorActividadTuristica {
    private static ManejadorActividadTuristica instancia;

    private Map<String, ActividadTuristica> actividades;

    public static ManejadorActividadTuristica getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorActividadTuristica();
        }
        return instancia;
    }

    public List<ActividadTuristica> getActividades() {
        return new ArrayList<ActividadTuristica>(actividades.values());
    }

    public Set<String> obtenerIdActividadesTuristicas(){
        return actividades.keySet();
    }

    public void addActividad(ActividadTuristica actividad) {
        actividades.put(actividad.getNombre(), actividad);
    }

    public ActividadTuristica getActividad(String nombre) {
        return actividades.get(nombre);
    }
}
