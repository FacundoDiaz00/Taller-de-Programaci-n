package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import logica.utils.UtilsDT;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({DTTurista.class, DTProveedor.class})
public class DTUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private String fechaNacStr;
	private Imagen img;

	public DTUsuario(){}
	
	public DTUsuario(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, Imagen img) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNacStr = fechaNac.format(UtilsDT.formatterLocalDate);
		this.img = img;
	}

	public String getNickname() {
		return nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public LocalDate getFechaNac() {
		return LocalDate.parse(fechaNacStr, UtilsDT.formatterLocalDate);
	}

	public Imagen getImg() {
		return img;
	}

	public String getFechaNacStr() {
		return fechaNacStr;
	}
}
