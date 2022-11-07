package logica.jpa;

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
}