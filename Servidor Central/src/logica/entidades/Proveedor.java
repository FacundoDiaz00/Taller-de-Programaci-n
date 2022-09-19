package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTProveedorDetalle;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;

/**
 * @author Equipo taller prog 16
 */

public class Proveedor extends Usuario {

	private String descrpicionGeneral;
	private String link;

	private Map<String, ActividadTuristica> actividadesTuristicas;

	public Proveedor(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, Imagen img,
			String descrpicionGeneral, String link) {
		super(nickname, nombre, apellido, correo, fechaNac, img);
		setDescrpicionGeneral(descrpicionGeneral);
		setLink(link);
		setActividadesTuristicas(new HashMap<>());
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
	public DTUsuario obtenerDTUsuario() {
		return new DTProveedor(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), getImagen(),
				getDescrpicionGeneral(), getLink());
	}

	@Override
	public DTUsuario obtenerDTUsuarioDetalle() {
		var dtActDetalles = new ArrayList<DTActividadTuristicaDetalle>();

		for (ActividadTuristica a : actividadesTuristicas.values()) {
			dtActDetalles.add(a.obtenerDTActividadTuristicaDetalle());
		}

		return new DTProveedorDetalle(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(),
				getImagen(), getDescrpicionGeneral(), getLink(), dtActDetalles);
	}

	public void asociarActividadTuristica(ActividadTuristica actividadTuristica) {
		actividadesTuristicas.put(actividadTuristica.getNombre(), actividadTuristica);
	}

	@Override
	public void setearDatos(DTUsuario datosNuevos) {
		super.setearDatos(datosNuevos);
		DTProveedor prov = (DTProveedor) datosNuevos;
		this.setDescrpicionGeneral(prov.getDescrpicionGeneral());
		this.setLink(prov.getLink());
	}

	@Override
	public DTUsuario obtenerDTUsuarioDetallePrivado() {
		// TODO Auto-generated method stub
		return null;
	}

}
