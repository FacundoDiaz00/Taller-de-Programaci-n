package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import logica.datatypes.DTProveedorDetalle;
import logica.datatypes.DTUsuario;

/**
 * @author Equipo taller prog 16
 */

public class Proveedor extends Usuario {

    private String descrpicionGeneral;
    private String link;

    private Map<String, ActividadTuristica> actividadesTuristicas;

    public Proveedor(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
            String descrpicionGeneral, String link) {
        super(nickname, nombre, apellido, correo, fechaNac);
        this.descrpicionGeneral = descrpicionGeneral;
        this.link = link;
        this.actividadesTuristicas = new HashMap<>();
    }

    public String getDescrpicionGeneral() {
        return descrpicionGeneral;
    }

    public void setDescrpicionGeneral(String descrpicionGeneral) {
        this.descrpicionGeneral = descrpicionGeneral;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Map<String, ActividadTuristica> getActividadesTuristicas() {
        return actividadesTuristicas;
    }

    public void setActividadesTuristicas(Map<String, ActividadTuristica> actividadesTuristicas) {
        this.actividadesTuristicas = actividadesTuristicas;
    }

    @Override
    public DTUsuario getDTUsuarioDetalle() {
        Map<String, List<String>> salidas = new HashMap<>();

        for (ActividadTuristica a : actividadesTuristicas.values()) {
            salidas.put(a.getNombre(), new ArrayList<String>());
            var salidasList = a.obtenerIdSalidasTuristicas();
            salidas.get(a.getNombre()).addAll(salidasList);
        }

        return new DTProveedorDetalle(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), descrpicionGeneral, link, salidas);
    }

	public void asociarActividadTuristica(ActividadTuristica actividadTuristica) {
		// TODO Auto-generated method stub
		
	}
}
