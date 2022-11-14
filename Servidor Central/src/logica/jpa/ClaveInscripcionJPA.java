
package logica.jpa;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ClaveInscripcionJPA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "idSalida", nullable = false)
	private long idSalida;

	@Column(name = "idTurista", nullable = false)
	private long idTurista;

	public long getIdTurista() {
		return idTurista;
	}

	public long getIdSalida() {
		return idSalida;
	}

	public void setIdSalida(long id_salida) {
		this.idSalida = id_salida;
	}

	public void setIdTurista(long id_turista) {
		this.idTurista = id_turista;
	}

	public ClaveInscripcionJPA(long id_sal, long id_tur) {
		this.idSalida = id_sal;
		this.idTurista = id_tur;
	}

	public ClaveInscripcionJPA() {
	}

	@Override
	public boolean equals(Object obje) {
		if (this == obje)
			return true;
		if (obje == null || getClass() != obje.getClass())
			return false;

		ClaveInscripcionJPA that = (ClaveInscripcionJPA) obje;

		if (idSalida != that.idSalida)
			return false;
		return idTurista == that.idTurista;
	}

	@Override
	public int hashCode() {
		int result = (int) (idSalida ^ (idSalida >>> 32));
		result = 31 * result + (int) (idTurista ^ (idTurista >>> 32));
		return result;
	}
}
