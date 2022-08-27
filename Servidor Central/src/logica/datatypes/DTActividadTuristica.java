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

	private String nombreProveedor;


	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getCostoPorTurista() {
		return costoPorTurista;
	}


	public String getCuidad() {
		return cuidad;
	}


	public int getDuracion() {
		return duracion;
	}


	public LocalDate getFechaAlta() {
		return fechaAlta;
	}


	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public DTActividadTuristica(String nombre, String descripcion, float costoPorTurista, String cuidad, int duracion, LocalDate fechaAlta, String nombreProveedor) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoPorTurista = costoPorTurista;
		this.cuidad = cuidad;
		this.duracion = duracion;
		this.fechaAlta = fechaAlta;
		this.nombreProveedor = nombreProveedor;
	}

	public DTActividadTuristica() {
		super();
	}
}
