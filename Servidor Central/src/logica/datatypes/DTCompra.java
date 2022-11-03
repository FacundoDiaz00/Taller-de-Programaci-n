package logica.datatypes;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompra {
	private LocalDate fechaCompra;
	private int cantTuristas;
	private float costo;
	private LocalDate vencimiento;
	private DTPaquete paquete;

	public DTCompra() {
		
	}
	
	public DTCompra(LocalDate fechaCompra, int cantTuristas, float costo, LocalDate vencimiento, DTPaquete paquete) {
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

	public DTPaquete getPaquete() {
		return paquete;
	}
}
