package logica.entidades;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Equipo taller prog 16
 */

public class Turista extends Usuario{
    private String nacionalidad;
    private Set<Compra> compras;
    private Set<Inscripcion> inscripciones;

    public Turista(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String nacionalidad) {
        super(nickname, nombre, apellido, correo, fechaNac);
        this.nacionalidad = nacionalidad;
        this.compras = new HashSet<>();
        this.inscripciones = new HashSet<>();
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Set<Compra> getCompras() {
        return compras;
    }

    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }

    public Set<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Set<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
}
