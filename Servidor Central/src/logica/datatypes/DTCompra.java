package logica.datatypes;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class DTCompra {
	private LocalDate fechaCompra;
	private int cantTuristas;
	private float costo;
	private LocalDate vencimiento;
	private String paquete;

	public DTCompra(LocalDate fechaCompra, int cantTuristas, float costo, LocalDate vencimiento, String paquete) {
		this.fechaCompra = fechaCompra;
		this.cantTuristas = cantTuristas;
		this.costo = costo;
		this.vencimiento = vencimiento;
		this.paquete = paquete;
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public float getCosto() {
		return costo;
	}

	public LocalDate getVencimiento() {
		return vencimiento;
	}

	public String getPaquete() {
		return paquete;
	}
}
