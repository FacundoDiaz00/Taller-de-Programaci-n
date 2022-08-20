package logica.entidades;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class Inscripcion {

    private LocalDate fechaInscrpicion;
    private int cantidadTuristas;

    private Compra compra;
    private SalidaTuristica salidaTuristica;

    public Inscripcion(LocalDate fechaInscrpicion, int cantidadTuristas, Compra compra,
            SalidaTuristica salidaTuristica) {
        this.fechaInscrpicion = fechaInscrpicion;
        this.cantidadTuristas = cantidadTuristas;
        this.compra = compra;
        this.salidaTuristica = salidaTuristica;
    }

    // Todo falta el calculo de costoInscripcion

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

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public SalidaTuristica getSalidaTuristica() {
        return salidaTuristica;
    }

    public void setSalidaTuristica(SalidaTuristica salidaTuristica) {
        this.salidaTuristica = salidaTuristica;
    }

    public String getNombreSalida() {
        return salidaTuristica.getNombre();
    }
}
