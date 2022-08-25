package logica.datatypes;


import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class DTActividadTuristica{
	private String nombre;
	private String descripcion;
	private float costoPorTurista;
	private String cuidad;
	private int duracion;
	private LocalDate fechaAlta;


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getCostoPorTurista() {
		return costoPorTurista;
	}

	public void setCostoPorTurista(float costoPorTurista) {
		this.costoPorTurista = costoPorTurista;
	}

	public String getCuidad() {
		return cuidad;
	}

	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public DTActividadTuristica(String nombre, String descripcion, float costoPorTurista, String cuidad, int duracion, LocalDate fechaAlta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoPorTurista = costoPorTurista;
		this.cuidad = cuidad;
		this.duracion = duracion;
		this.fechaAlta = fechaAlta;
	}

	public DTActividadTuristica() {
		super();
	}
}
