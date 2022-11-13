package logica.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity @Table(name = "usuarios") @Inheritance(strategy = InheritanceType.JOINED)
public class UsuarioJPA {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "nickname", unique = true)
	private String nickname;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	@Column(name = "fecha_nac", nullable = false, columnDefinition = "DATE")
	private LocalDate fechaNac;

	@Column(name = "tipo_usuario")
	private String tipoUsuario;

	public UsuarioJPA() {

	}

	public UsuarioJPA(String nickname, String email, String nombre, String apellido, LocalDate fechaNac,
			String tipoUsuario) {
		this.nickname = nickname;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNac = fechaNac;
		this.tipoUsuario = tipoUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

}