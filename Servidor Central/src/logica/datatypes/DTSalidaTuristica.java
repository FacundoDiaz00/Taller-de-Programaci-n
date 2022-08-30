package logica.datatypes;


import java.time.LocalDate;
import java.time.LocalDateTime;

import logica.entidades.ActividadTuristica;

/**
 * @author Equipo taller prog 16
 */

public class  DTSalidaTuristica {
	private String nombre;
	private int cantMaxTuristas;
	private LocalDate fechaAlta;
	private LocalDateTime fechaHoraSalida;
	private String lugarSalida;
    private DTActividadTuristica actividad;

	public DTSalidaTuristica(String nombre, int cantMaxTuristas, LocalDate fechaAlta, LocalDateTime fechaHoraSalida, String lugarSalida,DTActividadTuristica actividad) {
		this.nombre = nombre;
		this.cantMaxTuristas = cantMaxTuristas;
		this.fechaAlta = fechaAlta;
		this.fechaHoraSalida = fechaHoraSalida;
		this.lugarSalida = lugarSalida;
		this.actividad = actividad;
	}

	@Override
	public boolean equals(Object obj) {
		return ((DTSalidaTuristica)obj).nombre.equals(this.nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantMaxTuristas() {
		return cantMaxTuristas;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public LocalDateTime getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public String getLugarSalida() {
		return lugarSalida;
	}

	public DTActividadTuristica getActividad() {
		return actividad;
	}

}
