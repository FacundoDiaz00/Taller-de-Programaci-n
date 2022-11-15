
package logica.jpa;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import logica.datatypes.DTInscripcion;

@Entity @Table(name = "inscripciones")
public class InscripcionJPA {
	@EmbeddedId
	private ClaveInscripcionJPA identifClaveInscripcionJPA;

	@ManyToOne @MapsId("idSalida")
	private SalidaJPA salidaJPA;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }) @MapsId("idTurista")
	private TuristaJPA turistaJPA;

	@Column(name = "fecha_inscripcion", nullable = false, columnDefinition = "DATE")
	private LocalDate fechaInscripcion;

	@Column(name = "cantidad_turistas", nullable = false)
	private int cantidadTuristas;

	@Column(name = "costo", nullable = false)
	private float costo;

	public InscripcionJPA() {
	}

	public InscripcionJPA(SalidaJPA salidaJPA, TuristaJPA turistaJPA, LocalDate fecha_inscripcion,
			int cantidad_turistas, float costo) {
		this.salidaJPA = salidaJPA;
		this.turistaJPA = turistaJPA;
		this.fechaInscripcion = fecha_inscripcion;
		this.cantidadTuristas = cantidad_turistas;
		this.costo = costo;
	}

	public TuristaJPA getTuristaJPA() {
		return turistaJPA;
	}

	public void setTuristaJPA(TuristaJPA turistaJPA) {
		this.turistaJPA = turistaJPA;
	}

	public SalidaJPA getSalidaJPA() {
		return salidaJPA;
	}

	public void setSalidaJPA(SalidaJPA salidaJPA) {
		this.salidaJPA = salidaJPA;
	}

	public DTInscripcion obtenerDTInscripcion() {
		return new DTInscripcion(fechaInscripcion, cantidadTuristas, costo, salidaJPA.obtenerDTSalidaTuristica(),
				turistaJPA.getNickname(), null);
	}
}
