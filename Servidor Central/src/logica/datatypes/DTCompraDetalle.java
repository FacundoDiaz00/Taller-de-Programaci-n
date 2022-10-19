package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class DTCompraDetalle extends DTCompra {
	private DTPaquete dtPaquete;
	private List<DTInscripcion> inscripciones;

	public DTCompraDetalle(LocalDate fechaCompra, int cantTuristas, float costo, LocalDate vencimiento, DTPaquete paquete,
			DTPaquete dtP, List<DTInscripcion> insc) {
		super(fechaCompra, cantTuristas, costo, vencimiento, paquete);
		this.dtPaquete = dtP;
		this.inscripciones = insc;
	}

	public DTPaquete getDtPaquete() {
		return dtPaquete;
	}

	public List<DTInscripcion> getInscripciones() {
		return inscripciones;
	}
}