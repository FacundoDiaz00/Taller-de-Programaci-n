package logica.datatypes;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class DTProveedor extends DTUsuario {

	private String descrpicionGeneral;
	private String link;

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
	public boolean equals(Object e) {
		try {
			var casted = (DTProveedor) e;
			return casted.getNickname().equals(this.getNickname()) && casted.getNombre().equals(this.getNombre())
					&& casted.getApellido().equals(this.getApellido()) && casted.getCorreo().equals(this.getCorreo())
					&& casted.getFechaNac().equals(this.getFechaNac()) && casted.getLink().equals(this.getLink())
					&& casted.getDescrpicionGeneral().equals(this.getDescrpicionGeneral());
		} catch (Exception e2) {
			return false;
		}
	}

}
