package logica.entidades;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import excepciones.NoExisteConsumoParaLaActividadExcepcion;
import logica.datatypes.DTCompra;

/**
 * @author Equipo taller prog 16
 */

public class Compra {

    private LocalDate fechaCompra;
    private int cantidadTuristas;

    private Paquete paquete;
    private Set<Inscripcion> inscripciones;
    private Map<String, Integer> usosRestantesPorActividad;

    public Compra(LocalDate fechaCompra, int cantidadTuristas, Paquete paquete) {
        this.fechaCompra = fechaCompra;
        this.cantidadTuristas = cantidadTuristas;
        this.paquete = paquete;
        this.inscripciones = new HashSet<>();

        calcularMapUsosRestantes();
    }

    public Compra(Paquete paquete, int cantTuristas, LocalDate fechaCompra) {
        this.paquete = paquete;
        this.cantidadTuristas = cantTuristas;
        this.inscripciones = new HashSet<>();
        this.fechaCompra = fechaCompra;
        calcularMapUsosRestantes();
    }

    private void calcularMapUsosRestantes() {
        this.usosRestantesPorActividad = new HashMap<>();
        for (var actividad : paquete.obtenerIdActividadesIncluidas()) {
            usosRestantesPorActividad.put(actividad, this.cantidadTuristas);
        }
    }

    public LocalDate getVencimiento() {
        return this.fechaCompra.plusDays(paquete.getValidez());
    }

    public float getCostoTotal() {
        return paquete.getCostoPorTurista() * (float) cantidadTuristas;
    }

    public DTCompra obtenerDTCompra() {
        return new DTCompra(getFechaCompra(), getCantidadTuristas(), getCostoTotal(), getVencimiento(),
                paquete.getNombre());
    }

    public boolean tieneConsumoDisponibleParaActividad(String nombreActividad) {
        return this.usosRestantesPorActividad.containsKey(nombreActividad)
                && this.usosRestantesPorActividad.get(nombreActividad) > 0;
    }

    public int obtenerConsumosRestantesParaActividad(String nombreActividad)
            throws NoExisteConsumoParaLaActividadExcepcion {
        if (this.usosRestantesPorActividad.containsKey(nombreActividad)) {
            return usosRestantesPorActividad.get(nombreActividad);
        } else {
            throw new NoExisteConsumoParaLaActividadExcepcion("El paquete utilizado no incluye a la actividad");
        }
    }

    public void descontarConsumos(String nomActividad, int cantTuris) {
        this.usosRestantesPorActividad.put(nomActividad, this.usosRestantesPorActividad.get(nomActividad) - cantTuris);
    }

    public void agregarInscripcion(Inscripcion ins) {
        this.inscripciones.add(ins);
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
