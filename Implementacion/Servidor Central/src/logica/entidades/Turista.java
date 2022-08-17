package logica.entidades;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class Turista extends Usuario{
    private String nacionalidad;

    public Turista(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, String nacionalidad) {
        super(nickname, nombre, apellido, correo, fechaNac);
        this.nacionalidad = nacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
