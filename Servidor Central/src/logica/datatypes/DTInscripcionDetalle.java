package logica.datatypes;

import java.time.LocalDate;

import logica.entidades.Compra;
import logica.entidades.SalidaTuristica;

public class DTInscripcionDetalle extends DTInscripcion {
	private DTSalidaTuristica salidaTuristica;
	private DTCompra compra;
	private DTTurista turista;

	public DTInscripcionDetalle(LocalDate fechaInscripcion, int cantidadTuristas, float costo, String salida,
			String turista) {
		super(fechaInscripcion, cantidadTuristas, costo, salida, turista);
		// TODO
	}
}
