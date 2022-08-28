package logica.datatypes;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * @author Equipo taller prog 16
 */

public class DTSalidaTuristicaDetalle extends DTSalidaTuristica{
	private Set<DTInscripcion> inscriptos;

	public DTSalidaTuristicaDetalle(String nombre, int cantMaxTuristas, LocalDate fechaAlta, LocalDateTime fechaHoraSalida, String lugarSalida, Set<DTInscripcion> inscriptos) {
		super(nombre, cantMaxTuristas, fechaAlta, fechaHoraSalida, lugarSalida);
		this.inscriptos = inscriptos;
	}
	
}
