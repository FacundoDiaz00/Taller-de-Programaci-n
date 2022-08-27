package logica.entidades;

import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPaqueteDetalles;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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


    public DTPaqueteDetalles obtenerDTPaqueteDetalle(){
        Map<String, DTActividadTuristica> mapDtAct = new HashMap<>();
        for(ActividadTuristica act : actividades.values()){
            mapDtAct.put(act.getNombre(), act.obtenerDTActividadTuristica());
        }
        return new DTPaqueteDetalles(getNombre(), getDescrpicion(), getValidez(), getDescuento(), mapDtAct);
    }

    public DTPaquete obtenerDTPaquete(){
        return new DTPaquete(getNombre(), getDescrpicion(), getValidez(), getDescuento());
    }

    @Override
    public boolean equals(Object obj) {
        return this.getNombre().equals(((Paquete)obj).getNombre());
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
    
    public Set<String> obtenerIdActividadesIncluidas() {
    	return actividades.keySet();
    }

    public void agregarActividad(ActividadTuristica actividad) {
        actividades.put(actividad.getNombre(), actividad);
    }
}
