package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */


@XmlAccessorType(XmlAccessType.FIELD)
public class DTTuristaDetalle extends DTTurista {
	private List<DTSalidaTuristica> inscripcionesSalidas;
	private List<String> seguidos;
	private List<String> seguidores;
	
	public DTTuristaDetalle() {
		
	}

	public DTTuristaDetalle(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
			Imagen img, String nacionalidad, List<DTSalidaTuristica> inscripciones_salidas, List<String> seguidos, List<String> seguidores) {
		super(nickname, nombre, apellido, correo, fechaNac, img, nacionalidad);
		this.inscripcionesSalidas = inscripciones_salidas;
		this.seguidos = seguidos;
		this.seguidores = seguidores;
	}

	public List<DTSalidaTuristica> getInscripciones() {
		return inscripcionesSalidas;
	}

	public List<String> getSeguidores() {
		return seguidores;
	}

	public List<String> getSeguidos() {
		return seguidos;
	}

}
