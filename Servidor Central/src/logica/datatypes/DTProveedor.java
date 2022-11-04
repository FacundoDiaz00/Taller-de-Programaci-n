package logica.datatypes;

import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTProveedor extends DTUsuario {

	private String descrpicionGeneral;

	private String link;

	public DTProveedor(){}

	public DTProveedor(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, Imagen img,
			String descrpicionGeneral, String link) {
		super(nickname, nombre, apellido, correo, fechaNac, img);
		this.descrpicionGeneral = descrpicionGeneral;
		this.link = link;
	}

	public String getDescrpicionGeneral() {
		return descrpicionGeneral;
	}

	public String getLink() {
		return link;
	}

	@Override
	public boolean equals(Object equalsObject) {
		try {
			var casted = (DTProveedor) equalsObject;
			return casted.getNickname().equals(this.getNickname()) && casted.getNombre().equals(this.getNombre())
					&& casted.getApellido().equals(this.getApellido()) && casted.getCorreo().equals(this.getCorreo())
					&& casted.getFechaNac().equals(this.getFechaNac()) && casted.getLink().equals(this.getLink())
					&& casted.getDescrpicionGeneral().equals(this.getDescrpicionGeneral());
		} catch (ClassCastException e2) {
			return false;
		}
	}

}
