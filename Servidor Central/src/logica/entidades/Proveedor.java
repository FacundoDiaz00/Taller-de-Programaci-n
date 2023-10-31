package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTProveedorDetalle;
import logica.datatypes.DTProveedorDetallePrivado;
import logica.datatypes.DTUsuario;
import logica.datatypes.EstadoActividadTuristica;
import logica.datatypes.Imagen;
import logica.jpa.ProveedorJPA;
import logica.jpa.UsuarioJPA;
import logica.manejadores.ManejadorPersistenciaJPA;

/**
 * @author Equipo taller prog 16
 */

public class Proveedor extends Usuario {

	private String descrpicionGeneral;
	private String link;

	private Map<String, ActividadTuristica> actividadesTuristicas;

	public Proveedor(String nickname, String nombre, String apellido, String correo, String contra, LocalDate fechaNac,
			Imagen img, String descrpicionGeneral, String link) {
		super(nickname, nombre, apellido, correo, contra, fechaNac, img);
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

		for (ActividadTuristica actividad : actividadesTuristicas.values()) {
			if (actividad.estaAceptada())
				dtActDetalles.add(actividad.obtenerDTActividadTuristicaDetalle());
		}

		return new DTProveedorDetalle(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(),
				getImagen(), getDescrpicionGeneral(), getLink(), dtActDetalles,
				new ArrayList<>(getUsuariosSeguidos().keySet()), new ArrayList<>(getSeguidores().keySet()));
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
	public UsuarioJPA obtenerUsuarioJPA() {
		var usr = ManejadorPersistenciaJPA.getInstancia().encontrarProveedorJPA(getNickname());
		if (usr == null)
			usr = new ProveedorJPA(getNickname(), getCorreo(), getNombre(), getApellido(), getFechaNac(),
					getClass().getSimpleName(), descrpicionGeneral, link);
		ManejadorPersistenciaJPA.getInstancia().agregarProveedorPendientePersistencia(usr);
		return usr;
	}

	@Override
	public DTUsuario obtenerDTUsuarioDetallePrivado() {
		String nickname = this.getNickname();
		String nombre = this.getNombre();
		String apellido = this.getApellido();
		String correo = this.getCorreo();
		LocalDate fechaNac = this.getFechaNac();
		Imagen img = this.getImagen();
		String desc = this.getDescrpicionGeneral();
		String url = this.getLink();

		List<DTActividadTuristicaDetalle> actividades = new ArrayList<>();
		List<DTActividadTuristica> estadoAgregada = new ArrayList<>();
		List<DTActividadTuristica> estadoRechazada = new ArrayList<>();

		for (var act : this.actividadesTuristicas.values()) {
			switch (act.getEstado()) {
			case ACEPTADA:
				actividades.add(act.obtenerDTActividadTuristicaDetalle());
				break;
			case RECHAZADA:
				estadoRechazada.add(act.obtenerDTActividadTuristica());
				break;
			case AGREGADA:
				estadoAgregada.add(act.obtenerDTActividadTuristica());
				break;
			default:
				break;
			}
		}

		List<DTActividadTuristica> estadoFinalizada = ManejadorPersistenciaJPA.getInstancia()
				.obtenerActividadesFinalizadasDeProveedor(nickname);

		Map<EstadoActividadTuristica, List<DTActividadTuristica>> actividadesNoConfirmadas = new HashMap<>();
		actividadesNoConfirmadas.put(EstadoActividadTuristica.RECHAZADA, estadoRechazada);
		actividadesNoConfirmadas.put(EstadoActividadTuristica.AGREGADA, estadoAgregada);
		actividadesNoConfirmadas.put(EstadoActividadTuristica.FINALIZADA, estadoFinalizada);

		return new DTProveedorDetallePrivado(nickname, nombre, apellido, correo, fechaNac, img, desc, url, actividades,
				actividadesNoConfirmadas, new ArrayList<>(getUsuariosSeguidos().keySet()),
				new ArrayList<>(getSeguidores().keySet()));
	}

	public void eliminarActividad(String nomActividad) {
		this.actividadesTuristicas.remove(nomActividad);

	}

}
