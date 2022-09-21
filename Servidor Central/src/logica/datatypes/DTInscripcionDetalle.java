package logica.datatypes;

import java.time.LocalDate;

public class DTInscripcionDetalle extends DTInscripcion {
	private DTSalidaTuristica salidaTuristica;
	private DTCompra compra;
	private DTTurista turista;

	public DTInscripcionDetalle(LocalDate fechaInscripcion, int cantidadTuristas, float costo, String salida,
			String turista, DTSalidaTuristica salidaTuristica, DTCompra compra, DTTurista dtTurista) {
		super(fechaInscripcion, cantidadTuristas, costo, salida, turista);
		this.salidaTuristica = salidaTuristica;
		this.compra = compra;
		this.turista = dtTurista;
	}

	public DTSalidaTuristica getDTSalidaTuristica() {
		return salidaTuristica;
	}

	public DTCompra getDTCompra() {
		return compra;
	}

	public DTTurista getDTTurista() {
		return turista;
	}

}
