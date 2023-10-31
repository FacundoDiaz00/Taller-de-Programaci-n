package logica.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

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
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.datatypes.Imagen;
import logica.utils.UtilsDT;

@Entity @Table(name = "salidas")
public class SalidaJPA {
	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id", nullable = false)
	private Long identifLong;

	@Column(name = "nombre", unique = true, nullable = false)
	private String nombre;

	@Column(name = "fechaAlta", nullable = false, columnDefinition = "DATE")
	private LocalDate fechaAlta;

	@Column(name = "fecha_salida", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime fechaSalida;

	@Column(name = "cant_turistas_max", nullable = false)
	private int cantTurMax;

	@Column(name = "lugar", nullable = false)
	private String lugar;

	@ManyToOne @JoinColumn(name = "actividad_jpa_id", nullable = false)
	private ActividadJPA actividadJPA;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "salidaJPA", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Collection<InscripcionJPA> inscripciones = new java.util.ArrayList<>();

	public SalidaJPA() {

	}

	public SalidaJPA(String nombre, LocalDate fechaAlta, LocalDateTime fechaSalida, int cantTurMax, String lugar,
			ActividadJPA actividadJPA, Collection<InscripcionJPA> inscripciones) {
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.fechaSalida = fechaSalida;
		this.cantTurMax = cantTurMax;
		this.lugar = lugar;
		this.actividadJPA = actividadJPA;
		this.inscripciones = inscripciones;
	}

	public Collection<InscripcionJPA> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Collection<InscripcionJPA> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public Long getId() {
		return identifLong;
	}

	public void setId(Long identif) {
		this.identifLong = identif;
	}

	public ActividadJPA getActividadJPA() {
		return actividadJPA;
	}

	public void setActividadJPA(ActividadJPA actividadJPA) {
		this.actividadJPA = actividadJPA;
	}

	public String getNombre() {
		return nombre;
	}

	private Imagen getImagen() {
		String pathImg = UtilsDT.buscarImagen("/salidas/", nombre);
		return pathImg != null ? new Imagen(pathImg) : null;
	}

	public DTSalidaTuristica obtenerDTSalidaTuristica() {
		return new DTSalidaTuristica(nombre, fechaSalida, lugar, fechaAlta, cantTurMax, getImagen(),
				actividadJPA.getNombre());
	}

	public DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle() {
		var insc = new ArrayList<DTInscripcion>();
		for (InscripcionJPA inscr : inscripciones) {
			insc.add(inscr.obtenerDTInscripcion());
		}

		return new DTSalidaTuristicaDetalle(nombre, fechaSalida, lugar, fechaAlta, cantTurMax, getImagen(),
				actividadJPA.getNombre(), insc, actividadJPA.obtenerDTActividadTuristicaDetalle());
	}
}