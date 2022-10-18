package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class DTTuristaDetalle extends DTTurista {
	private List<DTSalidaTuristica> inscripcionesSalidas;

	public DTTuristaDetalle(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
			Imagen img, String nacionalidad, List<DTSalidaTuristica> inscripciones_salidas) {
		super(nickname, nombre, apellido, correo, fechaNac, img, nacionalidad);
		this.inscripcionesSalidas = inscripciones_salidas;
	}

	public List<DTSalidaTuristica> getInscripciones() {
		return inscripcionesSalidas;
	}

}
