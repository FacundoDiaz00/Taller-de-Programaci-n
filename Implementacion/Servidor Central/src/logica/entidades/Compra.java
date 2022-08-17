package logica.entidades;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class Compra {

    private LocalDate fechaCompra;
    private int cantidadTuristas;
    private LocalDate vencimiento;

    //ToDo Falta el campo calculado costoTotal


    public Compra(LocalDate fechaCompra, int cantidadTuristas, LocalDate vencimiento) {
        this.fechaCompra = fechaCompra;
        this.cantidadTuristas = cantidadTuristas;
        this.vencimiento = vencimiento;
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
}
