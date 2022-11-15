package logica.datatypes.comparadores;

import java.util.Comparator;

import logica.datatypes.DTActividadTuristica;

public class ComparatorDTActividadTuristicaByFechaCreacion implements Comparator<DTActividadTuristica> {
	@Override
	public int compare(DTActividadTuristica aciv1, DTActividadTuristica act2) {
		return aciv1.getFechaAlta().compareTo(act2.getFechaAlta());
	}
}
