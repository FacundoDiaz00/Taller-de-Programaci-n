package logica.datatypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTSalidaTuristicaDetalle extends DTSalidaTuristica {
	private List<DTInscripcion> inscripciones;
	private DTActividadTuristicaDetalle dtActividad;
	
	public DTSalidaTuristicaDetalle() {}

	public DTSalidaTuristicaDetalle(String nombre, LocalDateTime fechaHoraSalida, String lugarSalida,
			LocalDate fechaAlta, int cantMaxTuristas, Imagen img, String actividad, List<DTInscripcion> inscripciones,
			DTActividadTuristicaDetalle dtActividad) {
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
