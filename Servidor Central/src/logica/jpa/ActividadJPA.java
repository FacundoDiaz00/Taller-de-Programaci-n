
package logica.jpa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.EstadoActividadTuristica;
import logica.datatypes.Imagen;
import logica.utils.UtilsDT;

@Entity @Table(name = "actividades")
public class ActividadJPA {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id", nullable = false)
	private long id;

	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "duracion", nullable = false)
	private int duracion;

	@Column(name = "costo", nullable = false)
	private float costo;

	@Column(name = "ciudad", nullable = false)
	private String ciudad;

	@Column(name = "nombre_departamento", nullable = false)
	private String nombreDepartamento;

	@Column(name = "fechaAlta", nullable = false, columnDefinition = "DATE")
	private LocalDate fechaAlta;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "actividadJPA", cascade = { CascadeType.PERSIST })
	private Collection<SalidaJPA> salidas = new java.util.ArrayList<>();

	@ManyToOne(cascade = { CascadeType.PERSIST }) @JoinColumn(name = "id_proveedor", nullable = false)
	private ProveedorJPA proveedorJPA;

	public ActividadJPA(String nombre, String descripcion, int duracion, float costo, String ciudad,
			String nombre_departamento, LocalDate fechaAlta, Collection<SalidaJPA> salidas, ProveedorJPA proveedorJPA) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.costo = costo;
		this.ciudad = ciudad;
		this.nombreDepartamento = nombre_departamento;
		this.fechaAlta = fechaAlta;
		this.salidas = salidas;
		this.proveedorJPA = proveedorJPA;
	}

	public ActividadJPA() {
	}

	public Collection<SalidaJPA> getSalidas() {
		return salidas;
	}

	public void setSalidas(Collection<SalidaJPA> salidas) {
		this.salidas = salidas;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	private Imagen getImagen() {
		String pathImg = UtilsDT.buscarImagen("/actividades/", nombre);
		return pathImg != null ? new Imagen(pathImg) : null;
	}

	public DTActividadTuristicaDetalle obtenerDTActividadTuristicaDetalle() {
		HashMap<String, DTPaquete> packs = new HashMap<>();
		HashMap<String, DTSalidaTuristica> salid = new HashMap<>();
		for (SalidaJPA sal : salidas) {
			salid.put(sal.getNombre(), sal.obtenerDTSalidaTuristica());
		}
		List<String> listaIdCats = new ArrayList<>();

		return new DTActividadTuristicaDetalle(salid, packs, getNombre(), descripcion, costo, ciudad, duracion,
				fechaAlta, proveedorJPA.getNickname(), nombreDepartamento, listaIdCats, getImagen(),
				EstadoActividadTuristica.FINALIZADA, -1, null);
	}

	public DTActividadTuristica obtenerDTActividadTuristica() {
		List<String> listaIdCats = new ArrayList<>();

		return new DTActividadTuristica(getNombre(), descripcion, costo, ciudad, duracion, fechaAlta,
				proveedorJPA.getNickname(), nombreDepartamento, listaIdCats, getImagen(),
				EstadoActividadTuristica.FINALIZADA, -1, null);
	}

}
