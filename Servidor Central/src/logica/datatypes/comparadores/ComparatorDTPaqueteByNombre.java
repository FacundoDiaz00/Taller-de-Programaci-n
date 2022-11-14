package logica.datatypes.comparadores;

import java.util.Comparator;

import logica.datatypes.DTPaquete;

public class ComparatorDTPaqueteByNombre implements Comparator<DTPaquete> {
	@Override
	public int compare(DTPaquete paque1, DTPaquete opaque2) {
		return paque1.getNombre().compareTo(opaque2.getNombre());
	}
}
