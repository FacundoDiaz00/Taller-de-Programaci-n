package logica.datatypes;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.utils.UtilsDT;


@XmlAccessorType(XmlAccessType.FIELD)
public class DTInscripcion {
	private String fechaInscripcionStr;
	private int cantidadTuristas;
	private float costo;
	private DTSalidaTuristica salida;
	private String turista;

	private DTCompra compra;
	
	public DTInscripcion() {}

	public DTInscripcion(LocalDate fechaInscripcion, int cantidadTuristas, float costo, DTSalidaTuristica salida, String turista, DTCompra compra) {
		this.fechaInscripcionStr = fechaInscripcion.format(UtilsDT.formatterLocalDate);
		this.cantidadTuristas = cantidadTuristas;
		this.costo = costo;
		this.salida = salida;
		this.turista = turista;
		this.compra = compra;
	}

	public LocalDate getFechaInscripcion() {
		return LocalDate.parse(this.fechaInscripcionStr, UtilsDT.formatterLocalDate);
	}

	public int getCantidadTuristas() {
		return this.cantidadTuristas;
	}

	public DTSalidaTuristica getSalidaTuristica() {
		return this.salida;
	}

	public String getTurista() {
		return this.turista;
	}

	public float getCosto() {
		return costo;
	}

	public DTCompra getCompra() {
		return compra;
	}

	public String getFechaInscripcionStr() {
		return fechaInscripcionStr;
	}

	public DTSalidaTuristica getSalida() {
		return salida;
	}
}
