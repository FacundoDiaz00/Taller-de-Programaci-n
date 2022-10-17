package logica.entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Categoria {
    private String nombre;

    private Map<String, ActividadTuristica> actividades;

    public Categoria(String nombre) {
        this.nombre = nombre;
        setActividades(new HashMap<String, ActividadTuristica>());
    }

    public String getNombre() {
        return nombre;
    }

    public Map<String, ActividadTuristica> getActividades() {
        return actividades;
    }

    public void setActividades(Map<String, ActividadTuristica> actividades) {
        this.actividades = actividades;
    }

    public List<String> obtenerIdActividadesTuristicasConfirmadas() {
        var nombres = new ArrayList<String>();
        for (var act : actividades.values()) {
            if (act.estaAceptada())
                nombres.add(act.getNombre());
        }
        return nombres;
    }

    public void addActividad(ActividadTuristica actividadTuristica) {
        actividades.put(actividadTuristica.getNombre(), actividadTuristica);
    }
}
