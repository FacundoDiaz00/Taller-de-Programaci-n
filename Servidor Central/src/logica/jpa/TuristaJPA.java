package logica.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity @Table(name = "turistas")
public class TuristaJPA extends UsuarioJPA {
	@Column(name = "nacionalidad", nullable = false)
	private String nacionalidad;

	public TuristaJPA() {
		super();
	}

	public TuristaJPA(String nickname, String email, String nombre, String apellido, LocalDate fechaNac,
			String tipoUsuario, String nacionalidad) {
		super(nickname, email, nombre, apellido, fechaNac, tipoUsuario);
		this.nacionalidad = nacionalidad;
	}
}