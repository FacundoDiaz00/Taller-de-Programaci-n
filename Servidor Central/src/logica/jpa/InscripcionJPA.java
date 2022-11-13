
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
	private ClaveInscripcionJPA id;

	@ManyToOne @MapsId("id_salida")
	private SalidaJPA salidaJPA;

	@ManyToOne(cascade = { CascadeType.PERSIST }) @MapsId("id_turista")
	private TuristaJPA turistaJPA;

	@Column(name = "fecha_inscripcion", nullable = false, columnDefinition = "DATE")
	private LocalDate fecha_inscripcion;

	@Column(name = "cantidad_turistas", nullable = false)
	private int cantidad_turistas;

	@Column(name = "costo", nullable = false)
	private float costo;

	public InscripcionJPA() {
	}

	public InscripcionJPA(SalidaJPA salidaJPA, TuristaJPA turistaJPA, LocalDate fecha_inscripcion,
			int cantidad_turistas, float costo) {
		this.salidaJPA = salidaJPA;
		this.turistaJPA = turistaJPA;
		this.fecha_inscripcion = fecha_inscripcion;
		this.cantidad_turistas = cantidad_turistas;
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
		return new DTInscripcion(fecha_inscripcion, cantidad_turistas, costo, salidaJPA.obtenerDTSalidaTuristica(),
				turistaJPA.getNickname(), null);
	}
}
