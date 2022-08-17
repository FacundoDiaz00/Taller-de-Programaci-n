package logica.entidades;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class Inscripcion {

    private LocalDate fechaInscrpicion;
    private int cantidadTuristas;

    public Inscripcion(LocalDate fechaInscrpicion, int cantidadTuristas) {
        this.fechaInscrpicion = fechaInscrpicion;
        this.cantidadTuristas = cantidadTuristas;
    }

    public LocalDate getFechaInscrpicion() {
        return fechaInscrpicion;
    }

    public void setFechaInscrpicion(LocalDate fechaInscrpicion) {
        this.fechaInscrpicion = fechaInscrpicion;
    }

    public int getCantidadTuristas() {
        return cantidadTuristas;
    }

    public void setCantidadTuristas(int cantidadTuristas) {
        this.cantidadTuristas = cantidadTuristas;
    }
}
