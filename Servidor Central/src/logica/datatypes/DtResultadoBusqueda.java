package logica.datatypes;

import java.util.List;

public class DtResultadoBusqueda {

    List<DTActividadTuristica> actividades;

    List<DTPaquete> paquetes;

    public DtResultadoBusqueda() {
    }

    public DtResultadoBusqueda(List<DTActividadTuristica> actividades, List<DTPaquete> paquetes) {
        this.actividades = actividades;
        this.paquetes = paquetes;
    }

    public List<DTActividadTuristica> getActividades() {
        return actividades;
    }

    public List<DTPaquete> getPaquetes() {
        return paquetes;
    }
}
