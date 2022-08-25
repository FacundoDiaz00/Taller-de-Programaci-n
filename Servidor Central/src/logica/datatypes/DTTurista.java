package logica.datatypes;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class DTTurista extends DTUsuario {
    private String nacionalidad;

    public DTTurista(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
            String nacionalidad) {
        super(nickname, nombre, apellido, correo, fechaNac);
        this.nacionalidad = nacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
}
