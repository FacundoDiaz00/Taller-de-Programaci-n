package logica.datatypes;

import java.time.LocalDate;

import logica.entidades.Compra;
import logica.entidades.SalidaTuristica;

public class DTInscripcion {
	private LocalDate fechaInscripcion;
    private int cantidadTuristas;
    private String compra;
    private DTSalidaTuristica salidaTuristica;
    private DTTurista turista;
    
    public DTInscripcion(LocalDate fechaInscripcion,int cantidadTuristas,String compra,DTSalidaTuristica salidaTuristica,DTTurista turista) {
    	this.fechaInscripcion = fechaInscripcion;
    	this.cantidadTuristas = cantidadTuristas;
    	this.compra = compra;
    	this.salidaTuristica = salidaTuristica;
    	this.turista = turista;
    }
  
	public LocalDate getFechaInscripcion() {
		return this.fechaInscripcion;
	}
	
	public int getCantidadTuristas() {
		return this.cantidadTuristas;
	}
	
	public String getCompra() {
		return this.compra;
	}
	
	public DTSalidaTuristica getSalidaTuristica() {
		return this.salidaTuristica;
	}
	public DTTurista getTurista() {
		return this.turista;
	}

	public float getCosto() {
		return this.cantidadTuristas * this.getSalidaTuristica().getActividad().getCostoPorTurista();
	}
}
