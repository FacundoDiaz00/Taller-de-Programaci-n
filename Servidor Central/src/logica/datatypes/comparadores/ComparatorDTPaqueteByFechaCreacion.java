package logica.datatypes.comparadores;

import java.util.Comparator;

import logica.datatypes.DTPaquete;

public class ComparatorDTPaqueteByFechaCreacion implements Comparator<DTPaquete> {
	@Override
	public int compare(DTPaquete paque1, DTPaquete paque2) {
		return paque1.getFechaRegistro().compareTo(paque2.getFechaRegistro());
	}

}
