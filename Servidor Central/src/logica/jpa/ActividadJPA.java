package logica.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ActividadJPA {
    @Id
    @Column(name = "nombre", nullable = false)
    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
