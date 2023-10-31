package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DTTuristaDetalle.class)
public class DTTurista extends DTUsuario {
	private String nacionalidad;


	public DTTurista(){}
	public DTTurista(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, Imagen img,
			String nacionalidad) {
		super(nickname, nombre, apellido, correo, fechaNac, img);
		this.nacionalidad = nacionalidad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	@Override
	public boolean equals(Object equalsObject) {
		try {
			var casted = (DTTurista) equalsObject;
			return casted.getNacionalidad().equals(this.nacionalidad) && casted.getNickname().equals(this.getNickname())
					&& casted.getNombre().equals(this.getNombre()) && casted.getApellido().equals(this.getApellido())
					&& casted.getCorreo().equals(this.getCorreo()) && casted.getFechaNac().equals(this.getFechaNac());
		} catch (ClassCastException e2) {
			return false;
		}
	}
}
