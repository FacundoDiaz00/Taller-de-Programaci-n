package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class DTTurista extends DTUsuario {
    private String nacionalidad;
    private List<String> inscripcionesSalidas;

    public DTTurista(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
            String nacionalidad, List<String> inscripciones_salidas) {
        super(nickname, nombre, apellido, correo, fechaNac);
        this.nacionalidad = nacionalidad;
        this.inscripcionesSalidas = inscripciones_salidas;
    }

    public DTTurista(DTUsuario dtUsuario, String nacionalidad, List<String> inscripciones_salidas) {
        super(dtUsuario.getNickname(), dtUsuario.getNombre(), dtUsuario.getApellido(), dtUsuario.getCorreo(),
                dtUsuario.getFechaNac());
        this.nacionalidad = nacionalidad;
        this.inscripcionesSalidas = inscripciones_salidas;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public List<String> getInscripciones() {
        return inscripcionesSalidas;
    }

}
