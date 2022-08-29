package logica.entidades;

import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.manejadores.ManejadorActividadTuristica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Equipo taller prog 16
 */

public class SalidaTuristica {

    private String nombre;
    private int cantMaxTuristas;
    private LocalDate fechaAlta;
    private LocalDateTime fechaHoraSalida;
    private String lugarSalida;

    private ActividadTuristica actividad;
    private Set<Inscripcion> inscripciones;

    public SalidaTuristica(String IDActividad,String nombre, int cantMaxTuristas, LocalDate fechaAlta, LocalDateTime fechaHoraSalida, String lugarSalida) {
        setNombre(nombre);
        setCantMaxTuristas(cantMaxTuristas);
        setFechaAlta(fechaAlta);
        setFechaHoraSalida(fechaHoraSalida);
        setLugarSalida(lugarSalida);
        setActividad(null);
        setInscripciones(new HashSet<>());

        ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica at = mat.obtenerActividadTuristica(IDActividad);
        at.asociarSalidaAActividad(this);
        asociarActividadASalida(at);
    }

    public int obtenerCantidadInscriptos(){
        int count = 0;
        for (Inscripcion insc : inscripciones){
            count += insc.getCantidadTuristas();
        }
        return count;
    }

    public DTSalidaTuristica obtenerDTSalidaTuristica(){
        return new DTSalidaTuristica(getNombre(), getCantMaxTuristas(), getFechaAlta(), getFechaHoraSalida(), getLugarSalida());
    }

    public DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle(){
    	Set<DTInscripcion> res = new HashSet<>();
    	for(Inscripcion insc : inscripciones) {
    		res.add(insc.obtenerDTInscripcion());
    	}
        return new DTSalidaTuristicaDetalle(getNombre(), getCantMaxTuristas(), getFechaAlta(), getFechaHoraSalida(), getLugarSalida(), res);
    }
    
    public void agregarInscripcionASalida(Inscripcion ins){
        inscripciones.add(ins);
    }

    @Override
    public boolean equals(Object obj) {
        return ((SalidaTuristica)obj).getNombre().equals(this.getNombre());
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
}
