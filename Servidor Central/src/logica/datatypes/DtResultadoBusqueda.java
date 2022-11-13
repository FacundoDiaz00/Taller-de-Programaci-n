package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
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
