package logica.jpa;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "inscripciones")
public class InscripcionJPA {
    @EmbeddedId
    private ClaveInscripcionJPA id;

    @ManyToOne
    @MapsId("id_salida")
    private SalidaJPA salidaJPA;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @MapsId("id_turista")
    private TuristaJPA turistaJPA;

    @Column(name="fecha_inscripcion", nullable = false, columnDefinition = "DATE")
    private LocalDate fecha_inscripcion;

    @Column(name="cantidad_turistas", nullable = false)
    private int cantidad_turistas;

    @Column(name="costo", nullable = false)
    private float costo;

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
}

