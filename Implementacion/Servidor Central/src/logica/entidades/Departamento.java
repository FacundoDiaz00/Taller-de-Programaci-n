package logica.entidades;

/**
 * @author Equipo taller prog 16
 */

public class Departamento {

    private String nombre;
    private String descripcion;
    private String url;

    public Departamento(String nombre, String descripcion, String url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Departamento)obj).nombre.equals(this.nombre);
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
}
