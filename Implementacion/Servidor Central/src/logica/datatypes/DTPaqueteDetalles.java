package logica.datatypes;

import java.util.Map;

/**
 * @author Equipo taller prog 16
 */

public class DTPaqueteDetalles extends DTPaquete {

    private Map<String, DTActividadTuristica> actividades;

    public DTPaqueteDetalles(String nombre, String descrpicion, int validez, float descuento, Map<String, DTActividadTuristica> actividades) {
        super(nombre, descrpicion, validez, descuento);
        this.actividades = actividades;
    }

    @Override
    public Map<String, DTActividadTuristica> getActividades() {
        return actividades;
    }

    @Override
    public void setActividades(Map<String, DTActividadTuristica> actividades) {
        this.actividades = actividades;
    }
}
