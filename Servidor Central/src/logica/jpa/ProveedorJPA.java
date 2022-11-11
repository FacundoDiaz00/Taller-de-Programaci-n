package logica.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedores")
public class ProveedorJPA extends UsuarioJPA {

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="url", nullable = true)
    private String url;

    public ProveedorJPA() {
        super();
    }

    public ProveedorJPA(String nickname, String email, String nombre, String apellido, LocalDate fechaNac, String tipoUsuario, String descripcion, String url) {
        super(nickname, email, nombre, apellido, fechaNac, tipoUsuario);
        this.descripcion = descripcion;
        this.url = url;
    }
}