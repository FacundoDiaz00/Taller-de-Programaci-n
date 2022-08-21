package logica.entidades;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Equipo taller prog 16
 */

public class Paquete {

    private String nombre;
    private String descrpicion;
    private int validez;
    private float descuento;

    private Map<String, ActividadTuristica> actividades;

    public Paquete(String nombre, String descrpicion, int validez, float descuento) {
        this.nombre = nombre;
        this.descrpicion = descrpicion;
        this.validez = validez;
        this.descuento = descuento;
        this.actividades = new HashMap<>();
        //TODO: fecha de alta.
    }

    @Override
    public boolean equals(Object obj) {
        return this.nombre.equals(((Paquete)obj).nombre);
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

    public int getValidez() {
        return validez;
    }

    public void setValidez(int validez) {
        this.validez = validez;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Map<String, ActividadTuristica> getActividades() {
        return actividades;
    }

    public void setActividades(Map<String, ActividadTuristica> actividades) {
        this.actividades = actividades;
    }
}
