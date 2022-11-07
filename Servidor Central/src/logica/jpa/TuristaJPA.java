package logica.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "turistas")
public class TuristaJPA extends UsuarioJPA {
    @Column(name="nacionalidad", nullable = false)
    private String nacionalidad;
}