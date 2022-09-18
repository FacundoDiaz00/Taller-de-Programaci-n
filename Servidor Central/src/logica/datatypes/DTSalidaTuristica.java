package logica.datatypes;


import java.time.LocalDate;
import java.time.LocalDateTime;

import logica.entidades.ActividadTuristica;

/**
 * @author Equipo taller prog 16
 */

public class  DTSalidaTuristica {
	private String nombre;
	private LocalDateTime fechaHoraSalida;
	private String lugarSalida;
	private LocalDate fechaAlta;
	private int cantMaxTuristas;
	private Imagen img;
    private String actividad;

	public DTSalidaTuristica(String nombre, LocalDateTime fechaHoraSalida, String lugarSalida, LocalDate fechaAlta, int cantMaxTuristas, Imagen img, String actividad) {
		this.nombre = nombre;
		this.fechaHoraSalida = fechaHoraSalida;
		this.lugarSalida = lugarSalida;
		this.fechaAlta = fechaAlta;
		this.cantMaxTuristas = cantMaxTuristas;
		this.img = img;
		this.actividad = actividad;
	}

	public String getNombre() {
		return nombre;
	}

	public LocalDateTime getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
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
}
