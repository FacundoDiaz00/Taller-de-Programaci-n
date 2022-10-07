package logica.entidades;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import logica.datatypes.DTCompra;

/**
 * @author Equipo taller prog 16
 */

public class Compra {

	private LocalDate fechaCompra;
	private int cantidadTuristas;
	private LocalDate vencimiento;

	private Paquete paquete;
	private Set<Inscripcion> inscripciones;
	private Map<String, Integer> usosRestantesPorActividad;

	// TODO Falta el campo calculado costoTotal

	public Compra(LocalDate fechaCompra, int cantidadTuristas, LocalDate vencimiento, Paquete paquete) {
		this.fechaCompra = fechaCompra;
		this.cantidadTuristas = cantidadTuristas;
		this.vencimiento = vencimiento;
		this.paquete = paquete;
		this.inscripciones = new HashSet<>();

		calcularMapUsosRestantes();
	}

	public Compra(Paquete paquete, int cantTuristas) {
		this.paquete = paquete;
		this.cantidadTuristas = cantTuristas;
		this.inscripciones = new HashSet<>();
		this.fechaCompra = LocalDate.now();

		this.vencimiento = LocalDate.now().plusDays(paquete.getValidez());

		calcularMapUsosRestantes();
	}

	private void calcularMapUsosRestantes() {
		this.usosRestantesPorActividad = new HashMap<>();
		for (var actividad : paquete.obtenerIdActividadesIncluidas()) {
			usosRestantesPorActividad.put(actividad, this.cantidadTuristas);
		}
	}

	public DTCompra obtenerDTCompra() {
		var costo = paquete.getCostoPorTurista() * cantidadTuristas;

		return new DTCompra(fechaCompra, cantidadTuristas, (float) costo, vencimiento, paquete.getNombre());
	}

	public LocalDate getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public int getCantidadTuristas() {
		return cantidadTuristas;
	}

	public void setCantidadTuristas(int cantidadTuristas) {
		this.cantidadTuristas = cantidadTuristas;
	}

	public LocalDate getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(LocalDate vencimiento) {
		this.vencimiento = vencimiento;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public Set<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Set<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	public boolean correspondeAPaquete(String nombrePaquete) {
		return paquete.getNombre().equals(nombrePaquete);
	}
}
