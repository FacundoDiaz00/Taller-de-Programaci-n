package logica.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nickname", unique = true)
    private String nickname;

    // TODO agregar el resto de atributos
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}