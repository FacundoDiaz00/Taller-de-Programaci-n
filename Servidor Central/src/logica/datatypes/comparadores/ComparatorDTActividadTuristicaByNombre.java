package logica.datatypes.comparadores;

import logica.datatypes.DTActividadTuristica;

import java.util.Comparator;

public class ComparatorDTActividadTuristicaByNombre implements Comparator<DTActividadTuristica> {
    @Override
    public int compare(DTActividadTuristica o1, DTActividadTuristica o2) {
        return o1.getNombre().compareTo(o2.getNombre());
    }
}
