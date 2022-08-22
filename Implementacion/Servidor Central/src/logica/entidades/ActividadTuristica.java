package logica.entidades;

import logica.datatypes.DTActividadTuristica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

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

    private Map<String, Paquete> paquetes;
    private Map<String, SalidaTuristica> salidas;

    public ActividadTuristica(String nombre, String descrpicion, int duracion, float costoPorTurista, String cuidad,
            LocalDate fechaAlta) {
        this.nombre = nombre;
        this.descrpicion = descrpicion;
        this.duracion = duracion;
        this.costoPorTurista = costoPorTurista;
        this.cuidad = cuidad;
        this.fechaAlta = fechaAlta;
        this.paquetes = new HashMap<>();
        this.salidas = new HashMap<>();
    }

    public DTActividadTuristica obtenerDTActividadTuristica(){
        return new DTActividadTuristica(nombre, descrpicion, costoPorTurista, cuidad, duracion, fechaAlta);
    }

    @Override
    public boolean equals(Object obj) {
        return ((ActividadTuristica) obj).nombre.equals(this.nombre);
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

    public Map<String, Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Map<String, Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public Map<String, SalidaTuristica> getSalidas() {
        return salidas;
    }

    public void setSalidas(Map<String, SalidaTuristica> salidas) {
        this.salidas = salidas;
    }

    public List<String> obtenerIdSalidasTuristicas() {
        var listaSalidas = new ArrayList<String>();
        for (var salida : salidas.values()) {
            listaSalidas.add(salida.getNombre());
        }
        return listaSalidas;
    }
}
