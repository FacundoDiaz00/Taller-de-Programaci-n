package logica.entidades;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class ActividadTuristica {

    private String nombre;
    private String descrpicion;
    private int duracion;
    private float costoPorTurista;
    private String cuidad;
    private LocalDate fechaAlta;

    public ActividadTuristica(String nombre, String descrpicion, int duracion, float costoPorTurista, String cuidad, LocalDate fechaAlta) {
        this.nombre = nombre;
        this.descrpicion = descrpicion;
        this.duracion = duracion;
        this.costoPorTurista = costoPorTurista;
        this.cuidad = cuidad;
        this.fechaAlta = fechaAlta;
    }

    @Override
    public boolean equals(Object obj) {
        return ((ActividadTuristica)obj).nombre.equals(this.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescrpicion() {
        return descrpicion;
    }

    public void setDescrpicion(String descrpicion) {
        this.descrpicion = descrpicion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public float getCostoPorTurista() {
        return costoPorTurista;
    }

    public void setCostoPorTurista(float costoPorTurista) {
        this.costoPorTurista = costoPorTurista;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
