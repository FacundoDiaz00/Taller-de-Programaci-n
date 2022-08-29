package logica.entidades;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Equipo taller prog 16
 */

public class Departamento {

    private String nombre;
    private String descripcion;
    private String url;

    Map<String, ActividadTuristica> actividadTuristicas;

    public Departamento(String nombre, String descripcion, String url) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setUrl(url);
        setActividadTuristicas(new HashMap<>());
    }

    @Override
    public boolean equals(Object obj) {
        return ((Departamento)obj).getNombre().equals(this.getNombre());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, ActividadTuristica> getActividadTuristicas() {
        return actividadTuristicas;
    }

    public void setActividadTuristicas(Map<String, ActividadTuristica> actividadTuristicas) {
        this.actividadTuristicas = actividadTuristicas;
    }

	public void asociarActividadTuristica(ActividadTuristica AT) {
		actividadTuristicas.put(AT.getNombre(), AT);
	}
   
}
