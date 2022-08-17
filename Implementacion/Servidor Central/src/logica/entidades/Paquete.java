package logica.entidades;

/**
 * @author Equipo taller prog 16
 */

public class Paquete {

    private String nombre;
    private String descrpicion;
    private int validez;
    private float descuento;

    public Paquete(String nombre, String descrpicion, int validez, float descuento) {
        this.nombre = nombre;
        this.descrpicion = descrpicion;
        this.validez = validez;
        this.descuento = descuento;
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
}
