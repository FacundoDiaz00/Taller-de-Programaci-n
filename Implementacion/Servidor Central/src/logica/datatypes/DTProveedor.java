package logica.datatypes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Equipo taller prog 16
 */

public class DTProveedor extends DTUsuario {

    private String descrpicionGeneral;
    private String link;

    private Map<String, List<String>> actividadesSalidas;

    public DTProveedor(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
            String descrpicionGeneral, String link, Map<String, List<String>> actividades_y_salidas) {
        super(nickname, nombre, apellido, correo, fechaNac);
        this.descrpicionGeneral = descrpicionGeneral;
        this.link = link;
        this.actividadesSalidas = actividades_y_salidas;
    }

    public DTProveedor(DTUsuario dtUsuario, String descrpicionGeneral, String link,
            Map<String, List<String>> actividades_y_salidas) {
        super(dtUsuario.getNickname(), dtUsuario.getNombre(), dtUsuario.getApellido(), dtUsuario.getCorreo(),
                dtUsuario.getFechaNac());
        this.descrpicionGeneral = descrpicionGeneral;
        this.link = link;
        this.actividadesSalidas = actividades_y_salidas;
    }

    public String getDescrpicionGeneral() {
        return descrpicionGeneral;
    }

    public String getLink() {
        return link;
    }

    public Map<String, List<String>> getActividadesSalidas() {
        return actividadesSalidas;
    }

}
