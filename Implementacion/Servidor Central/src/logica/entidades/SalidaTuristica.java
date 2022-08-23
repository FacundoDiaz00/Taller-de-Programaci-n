package logica.entidades;

import logica.datatypes.DTSalidaTuristica;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private Set<Inscripcion> inscripciones;

    public SalidaTuristica(String nombre, int cantMaxTuristas, LocalDate fechaAlta, LocalDateTime fechaHoraSalida, String lugarSalida) {
        this.nombre = nombre;
        this.cantMaxTuristas = cantMaxTuristas;
        this.fechaAlta = fechaAlta;
        this.fechaHoraSalida = fechaHoraSalida;
        this.lugarSalida = lugarSalida;
    }

    public int obtenerCantidadInscriptos(){
        int count = 0;
        for (Inscripcion insc : inscripciones){
            count += insc.getCantidadTuristas();
        }
        return count;
    }

    public DTSalidaTuristica obtenerDTSalidaTuristica(){
        return new DTSalidaTuristica(nombre, cantMaxTuristas, fechaAlta, fechaHoraSalida, lugarSalida);
    }

    public void agregarInscripcionASalida(Inscripcion ins){
        inscripciones.add(ins);
    }

    @Override
    public boolean equals(Object obj) {
        return ((SalidaTuristica)obj).nombre.equals(this.nombre);
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
}
