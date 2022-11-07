package logica.jpa;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "salidas")
public class SalidaJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "actividad_jpa_id", nullable=false)
    private ActividadJPA actividadJPA;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "salidaJPA", cascade = {CascadeType.PERSIST})
    private Collection<InscripcionJPA> inscripciones = new java.util.ArrayList<>();

    public Collection<InscripcionJPA> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Collection<InscripcionJPA> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ActividadJPA getActividadJPA() {
        return actividadJPA;
    }

    public void setActividadJPA(ActividadJPA actividadJPA) {
        this.actividadJPA = actividadJPA;
    }
}