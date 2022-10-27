package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class DTProveedorDetalle extends DTProveedor {
	private List<DTActividadTuristicaDetalle> actividades;
	private List<String> seguidos;
	private List<String> seguidores;
	

	public DTProveedorDetalle(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
			Imagen img, String desc, String url, List<DTActividadTuristicaDetalle> actividades, List<String> seguidos, List<String> seguidores) {
		super(nickname, nombre, apellido, correo, fechaNac, img, desc, url);
		this.actividades = actividades;
		this.seguidos = seguidos;
		this.seguidores = seguidores;
	}

	public List<DTActividadTuristicaDetalle> getActividades() {
		return actividades;
	}

	public List<String> getSeguidores() {
		return seguidores;
	}

	public List<String> getSeguidos() {
		return seguidos;
	}
}
