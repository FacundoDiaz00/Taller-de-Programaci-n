package logica.entidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.datatypes.Imagen;
import logica.manejadores.ManejadorActividadTuristica;

/**
 * @author Equipo taller prog 16
 */

public class SalidaTuristica {

    private String nombre;
    private int cantMaxTuristas;
    private LocalDate fechaAlta;
    private LocalDateTime fechaHoraSalida;
    private String lugarSalida;
    private Imagen img;
    private ActividadTuristica actividad;
    private Set<Inscripcion> inscripciones;

    public SalidaTuristica(String nombreActividad, String nombre, int cantMaxTuristas, LocalDate fechaAlta,
            LocalDateTime fechaHoraSalida, String lugarSalida, Imagen img) {
        setNombre(nombre);
        setCantMaxTuristas(cantMaxTuristas);
        setFechaAlta(fechaAlta);
        setFechaHoraSalida(fechaHoraSalida);
        setLugarSalida(lugarSalida);
        setActividad(null);
        setInscripciones(new HashSet<>());
        setImagen(img);

        ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica actTuristica = mat.obtenerActividadTuristica(nombreActividad);
        actTuristica.asociarSalidaAActividad(this);
        asociarActividadASalida(actTuristica);
    }

    public int obtenerCantidadInscriptos() {
        int count = 0;
        for (Inscripcion insc : inscripciones) {
            count += insc.getCantidadTuristas();
        }
        return count;
    }

    public DTSalidaTuristica obtenerDTSalidaTuristica() {
        return new DTSalidaTuristica(nombre, fechaHoraSalida, lugarSalida, fechaAlta, cantMaxTuristas, img,
                actividad.getNombre());
    }

    public DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle() {
        var insc = new ArrayList<DTInscripcion>();
        for (Inscripcion inscr : inscripciones) {
            insc.add(inscr.obtenerDTInscripcion());
        }
        return new DTSalidaTuristicaDetalle(nombre, fechaHoraSalida, lugarSalida, fechaAlta, cantMaxTuristas, img,
                getActividad().getNombre(), insc, getActividad().obtenerDTActividadTuristicaDetalle());
    }

    public void agregarInscripcionASalida(Inscripcion ins) {
        inscripciones.add(ins);
    }

    @Override
    public boolean equals(Object obj) {
        return ((SalidaTuristica) obj).getNombre().equals(this.getNombre());
    }

    public Set<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Set<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantMaxTuristas() {
        return cantMaxTuristas;
    }

    public void setCantMaxTuristas(int cantMaxTuristas) {
        this.cantMaxTuristas = cantMaxTuristas;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public String getLugarSalida() {
        return lugarSalida;
    }

    public void setLugarSalida(String lugarSalida) {
        this.lugarSalida = lugarSalida;
    }

    public void asociarActividadASalida(ActividadTuristica act) {
        actividad = act;
    }

    public ActividadTuristica getActividad() {
        return actividad;
    }

    public void setActividad(ActividadTuristica actividad) {
        this.actividad = actividad;
    }

    public void setImagen(Imagen img) {
        this.img = img;
    }
}
