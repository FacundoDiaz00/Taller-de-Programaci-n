package logica.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ClaveInscripcionJPA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="id_salida", nullable = false)
    private long id_salida;

    @Column(name="id_turista", nullable = false)
    private long id_turista;

    public long getId_turista() {
        return id_turista;
    }

    public long getId_salida() {
        return id_salida;
    }

    public void setId_salida(long id_salida) {
        this.id_salida = id_salida;
    }

    public void setId_turista(long id_turista) {
        this.id_turista = id_turista;
    }

    public ClaveInscripcionJPA(long id_sal, long id_tur) {
    	this.id_salida = id_sal;
    	this.id_turista = id_tur;
    }
    
    public ClaveInscripcionJPA() {
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClaveInscripcionJPA that = (ClaveInscripcionJPA) o;

        if (id_salida != that.id_salida) return false;
        return id_turista == that.id_turista;
    }

    @Override
    public int hashCode() {
        int result = (int) (id_salida ^ (id_salida >>> 32));
        result = 31 * result + (int) (id_turista ^ (id_turista >>> 32));
        return result;
    }
}
