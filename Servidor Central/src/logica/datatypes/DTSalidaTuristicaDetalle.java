package logica.datatypes;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Equipo taller prog 16
 */

public class DTSalidaTuristicaDetalle extends DTSalidaTuristica{
	private List<DTInscripcion> inscripciones;
	private DTActividadTuristicaDetalle dtActividad;

	public DTSalidaTuristicaDetalle(String nombre, LocalDateTime fechaHoraSalida, String lugarSalida, LocalDate fechaAlta, int cantMaxTuristas, Imagen img, String actividad, List<DTInscripcion> inscripciones, DTActividadTuristicaDetalle dtActividad) {
		super(nombre, fechaHoraSalida, lugarSalida, fechaAlta, cantMaxTuristas, img, actividad);
		this.inscripciones = inscripciones;
		this.dtActividad = dtActividad;
	}

	public List<DTInscripcion> getInscripciones() {
		return inscripciones;
	}

	public DTActividadTuristicaDetalle getDtActividad() {
		return dtActividad;
	}
	
}
