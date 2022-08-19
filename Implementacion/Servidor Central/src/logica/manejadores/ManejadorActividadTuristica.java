package logica.manejadores;

import java.util.Map;

import logica.entidades.ActividadTuristica;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorActividadTuristica {
    private ManejadorActividadTuristica instancia;

    private Map<String, ActividadTuristica> actividades;

    public ManejadorActividadTuristica getInstancia(){
        if(instancia == null){
            instancia = new ManejadorActividadTuristica();
        }
        return instancia;
    }

    public Map<String, ActividadTuristica> getActividades() {
        return actividades;
    }

    public void addActividad(ActividadTuristica actividad) {
        actividades.put(actividad.getNombre(), actividad);
    }

    public ActividadTuristica getActividad(String nombre) {
        return actividades.get(nombre);
    }
}
