package logica.datatypes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Equipo taller prog 16
 */

public class DTProveedorDetalle extends DTProveedor {
    private Map<String, List<String>> actividadesSalidas;

    public DTProveedorDetalle(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
            String descrpicionGeneral, String link, Map<String, List<String>> actividades_y_salidas) {
        super(nickname, nombre, apellido, correo, fechaNac, descrpicionGeneral, link);
        this.actividadesSalidas = actividades_y_salidas;
    }


    public Map<String, List<String>> getActividadesSalidas() {
        return actividadesSalidas;
    }

}
