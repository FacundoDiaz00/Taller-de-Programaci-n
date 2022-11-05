package logica.datatypes;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.utils.UtilsDT;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompra {
	private String fechaCompraStr;
	private int cantTuristas;
	private float costo;
	private String vencimientoStr;
	private DTPaquete paquete;

	public DTCompra() {
		
	}
	
	public DTCompra(LocalDate fechaCompra, int cantTuristas, float costo, LocalDate vencimiento, DTPaquete paquete) {
		this.fechaCompraStr = fechaCompra.format(UtilsDT.formatterLocalDate);
		this.cantTuristas = cantTuristas;
		this.costo = costo;
		this.vencimientoStr = vencimiento.format(UtilsDT.formatterLocalDate);
		this.paquete = paquete;
	}

	public LocalDate getFechaCompra() {
		return LocalDate.parse(fechaCompraStr, UtilsDT.formatterLocalDate);
	}

	public int getCantTuristas() {
		return cantTuristas;
	}

	public float getCosto() {
		return costo;
	}

	public LocalDate getVencimiento() {
		return LocalDate.parse(vencimientoStr, UtilsDT.formatterLocalDate);
	}

	public DTPaquete getPaquete() {
		return paquete;
	}

	public String getFechaCompraStr() {
		return fechaCompraStr;
	}

	public String getVencimientoStr() {
		return vencimientoStr;
	}


}
