package logica.datatypes.colleciones;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.datatypes.DTActividadTuristica;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtActividadTuristicaCollection {
    private List<DTActividadTuristica> actividadTuristicas;

    public DtActividadTuristicaCollection(){
        this.actividadTuristicas = new ArrayList<>();
    }

    public DtActividadTuristicaCollection(List<DTActividadTuristica> coleccion) {
        this.actividadTuristicas = coleccion;
    }

    public List<DTActividadTuristica> getActividadTuristicas() {
        return actividadTuristicas;
    }
}
