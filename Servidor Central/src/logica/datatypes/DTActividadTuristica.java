package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class DTActividadTuristica {
	private String nombre;
	private String descripcion;
	private LocalDate fechaAlta;
	private String cuidad;
	private int duracion;
	private float costoPorTurista;
	private String nicknameProveedor;
	private String departamento;
	private List<String> categorias;
	private Imagen img;

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

	public String getNicknameProveedor() {
		return nicknameProveedor;
	}

	public DTActividadTuristica(String nombre, String descripcion, float costoPorTurista, String cuidad, int duracion,
			LocalDate fechaAlta, String nicknameProveedor) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoPorTurista = costoPorTurista;
		this.cuidad = cuidad;
		this.duracion = duracion;
		this.fechaAlta = fechaAlta;
		this.nicknameProveedor = nicknameProveedor;
	}

	public String getDepartamento() {
		return departamento;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public Imagen getImg() {
		return img;
	}
}
