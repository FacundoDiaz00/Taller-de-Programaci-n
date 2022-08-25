package logica.datatypes;

import java.time.LocalDate;
import java.util.Map;

import logica.entidades.Paquete;
import logica.entidades.SalidaTuristica;

/**
 * @author Equipo taller prog 16
 */

public class DTActividadTuristicaDetalle extends DTActividadTuristica {
    private Map<String, DTSalidaTuristica> salidas;
    private Map<String, DTPaquete> paquetes;

    public DTActividadTuristicaDetalle(Map<String, DTSalidaTuristica> salidas, Map<String, DTPaquete> paquetes,String nombre, String descripcion, float costoPorTurista, String cuidad, int duracion, LocalDate fechaAlta) {
    	super(nombre, descripcion, costoPorTurista,cuidad,duracion, fechaAlta);
        this.salidas = salidas;
        this.paquetes = paquetes;
    }

    public Map<String, DTSalidaTuristica> getSalidas() {
        return salidas;
    }

    public Map<String, DTPaquete> getPaquetes() {
        return paquetes;
    }

    public DTSalidaTuristica getSalida(String nombre) {
        return salidas.get(nombre);
    }

    public DTPaquete getPaquete(String nombre) {
        return paquetes.get(nombre);
    }
}
