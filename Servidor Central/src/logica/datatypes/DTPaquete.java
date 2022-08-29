package logica.datatypes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Equipo taller prog 16
 */

public class DTPaquete {
    private String nombre;
    private String descrpicion;
    private int validez;
    private float descuento;

    public DTPaquete(String nombre, String descrpicion, int validez, float descuento) {
        this.nombre = nombre;
        this.descrpicion = descrpicion;
        this.validez = validez;
        this.descuento = descuento;
    }

    public String getNombre() {
        return nombre;
    }


    public String getDescrpicion() {
        return descrpicion;
    }


    public int getValidez() {
        return validez;
    }

    public float getDescuento() {
        return descuento;
    }


}
