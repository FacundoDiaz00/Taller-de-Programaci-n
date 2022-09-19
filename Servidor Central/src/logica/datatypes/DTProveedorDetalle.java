package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class DTProveedorDetalle extends DTProveedor {
	private List<DTActividadTuristicaDetalle> actividades;

	public DTProveedorDetalle(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
			Imagen img, String desc, String url, List<DTActividadTuristicaDetalle> actividades) {
		super(nickname, nombre, apellido, correo, fechaNac, img, desc, url);
		this.actividades = actividades;
	}

	public List<DTActividadTuristicaDetalle> getActividades() {
		return actividades;
	}
}
