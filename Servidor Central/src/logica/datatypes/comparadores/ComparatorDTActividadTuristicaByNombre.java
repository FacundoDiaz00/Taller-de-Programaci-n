package logica.datatypes.comparadores;

import java.util.Comparator;

import logica.datatypes.DTActividadTuristica;

public class ComparatorDTActividadTuristicaByNombre implements Comparator<DTActividadTuristica> {
	@Override
	public int compare(DTActividadTuristica actividad1, DTActividadTuristica activ2) {
		return actividad1.getNombre().compareTo(activ2.getNombre());
	}
}
