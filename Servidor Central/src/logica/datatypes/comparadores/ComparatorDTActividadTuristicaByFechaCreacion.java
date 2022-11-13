package logica.datatypes.comparadores;

import logica.datatypes.DTActividadTuristica;

import java.util.Comparator;

public class ComparatorDTActividadTuristicaByFechaCreacion implements Comparator<DTActividadTuristica> {
    @Override
    public int compare(DTActividadTuristica o1, DTActividadTuristica o2) {
        return o1.getFechaAlta().compareTo(o2.getFechaAlta());
    }
}
