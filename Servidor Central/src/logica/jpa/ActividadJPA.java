package logica.jpa;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "actividades")
public class ActividadJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="nombre", unique=true, nullable = false)
    private String nombre;

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="duracion", nullable = false)
    private int duracion;

    @Column(name="costo", nullable = false)
    private float costo;

    @Column(name="ciudad", nullable = false)
    private String ciudad;

    @Column(name="nombre_departamento", nullable = false)
    private String nombreDepartamento;

    @Column(name="fechaAlta", nullable = false, columnDefinition = "DATE")
    private LocalDate fechaAlta;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "actividadJPA", cascade = {CascadeType.PERSIST})
    private Collection<SalidaJPA> salidas = new java.util.ArrayList<>();

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="id_proveedor", nullable=false)
    private ProveedorJPA proveedorJPA;

    public ActividadJPA (String nombre, String descripcion, int duracion, float costo, String ciudad,
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

    public ActividadJPA() {}

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


}
