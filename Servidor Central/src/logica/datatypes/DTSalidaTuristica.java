package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import logica.utils.UtilsDT;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Equipo taller prog 16
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DTSalidaTuristicaDetalle.class)
public class DTSalidaTuristica {
	private String nombre;
	private String fechaHoraSalidaStr;
	private String lugarSalida;
	private String fechaAltaStr;
	private int cantMaxTuristas;
	private Imagen img;
	private String actividad;

	public DTSalidaTuristica(){}

	public DTSalidaTuristica(String nombre, LocalDateTime fechaHoraSalida, String lugarSalida, LocalDate fechaAlta,
			int cantMaxTuristas, Imagen img, String actividad) {
		this.nombre = nombre;
		this.fechaHoraSalidaStr = fechaHoraSalida.format(UtilsDT.formatterLocalDateTime);
		this.lugarSalida = lugarSalida;
		this.fechaAltaStr = fechaAlta.format(UtilsDT.formatterLocalDate);
		this.cantMaxTuristas = cantMaxTuristas;
		this.img = img;
		this.actividad = actividad;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDateTime getFechaHoraSalida() {
		return LocalDateTime.parse(fechaHoraSalidaStr, UtilsDT.formatterLocalDateTime);
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public LocalDate getFechaAlta() {
		return LocalDate.parse(fechaAltaStr, UtilsDT.formatterLocalDate);
	}

	public int getCantMaxTuristas() {
		return cantMaxTuristas;
	}

	public Imagen getImg() {
		return img;
	}

	public String getActividad() {
		return actividad;
	}

	public String getFechaHoraSalidaStr() {
		return fechaHoraSalidaStr;
	}

	public String getFechaAltaStr() {
		return fechaAltaStr;
	}
}
