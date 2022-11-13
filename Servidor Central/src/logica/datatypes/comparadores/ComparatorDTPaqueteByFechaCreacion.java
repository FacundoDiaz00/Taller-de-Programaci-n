package logica.datatypes.comparadores;

import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaquete;

import java.util.Comparator;

public class ComparatorDTPaqueteByFechaCreacion implements Comparator<DTPaquete> {
    @Override
    public int compare(DTPaquete o1, DTPaquete o2) {
        return o1.getFechaRegistro().compareTo(o2.getFechaRegistro());
    }

}
