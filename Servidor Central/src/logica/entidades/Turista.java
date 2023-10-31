package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.ControladorActividadTuristica;
import logica.datatypes.DTCompra;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTTurista;
import logica.datatypes.DTTuristaDetalle;
import logica.datatypes.DTTuristaDetallePrivado;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;
import logica.jpa.TuristaJPA;
import logica.jpa.UsuarioJPA;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorPersistenciaJPA;

/**
 * @author Equipo taller prog 16
 */

public class Turista extends Usuario {
	private String nacionalidad;
	private Set<Compra> compras;
	private Set<Inscripcion> inscripciones;
	private Map<String, ActividadTuristica> actividadesFavoritas;

	public Turista(String nickname, String nombre, String apellido, String correo, String contra, LocalDate fechaNac,
			Imagen img, String nacionalidad) {
		super(nickname, nombre, apellido, correo, contra, fechaNac, img);
		setNacionalidad(nacionalidad);
		setCompras(new HashSet<>());
		setInscripciones(new HashSet<>());
		setActividadesFavoritas(new HashMap<>());
	}

	public void altaInscripcionSalidaTuristica(SalidaTuristica sal, int canTuris, LocalDate fechaInscrp, Compra comp,
			String nomActividad)
			throws FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException {
		Inscripcion insc = new Inscripcion(fechaInscrp, canTuris, sal, this, comp, nomActividad);
		inscripciones.add(insc);
	}

	public boolean estaInscriptoASalida(String nomSalTuri) {
		for (Inscripcion insc : inscripciones) {
			if (insc.estaInscriptoASalida(nomSalTuri)) {
				return true;
			}
		}
		return false;
	}

	public Compra obtenerCompraParaNombrePaquete(String nombrePaquete) {
		for (Compra com : this.compras) {
			if (com.correspondeAPaquete(nombrePaquete)) {
				return com;
			}
		}
		return null;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Set<Compra> getCompras() {
		return compras;
	}

	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	public Set<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(Set<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	@Override
	public DTUsuario obtenerDTUsuario() {
		return new DTTurista(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), getImagen(),
				nacionalidad);
	}

	@Override
	public DTUsuario obtenerDTUsuarioDetalle() {
		List<DTSalidaTuristica> salidas = new ArrayList<>();

		for (Inscripcion inscripcion : inscripciones) {
			salidas.add(inscripcion.getSalidaTuristica().obtenerDTSalidaTuristica());
		}
		// Agrego la persistencia
		salidas.addAll(ManejadorPersistenciaJPA.getInstancia().obtenerSalidasDeTurista(getNickname()));

		return new DTTuristaDetalle(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), getImagen(),
				nacionalidad, salidas, new ArrayList<>(getUsuariosSeguidos().keySet()),
				new ArrayList<>(getSeguidores().keySet()));
	}

	@Override
	public void setearDatos(DTUsuario datosNuevos) {
		super.setearDatos(datosNuevos);
		DTTurista tur = (DTTurista) datosNuevos;
		this.setNacionalidad(tur.getNacionalidad());
	}

	@Override
	public UsuarioJPA obtenerUsuarioJPA() {
		var usr = ManejadorPersistenciaJPA.getInstancia().encontrarTuristaJPA(getNickname());
		if (usr == null)
			usr = new TuristaJPA(getNickname(), getCorreo(), getNombre(), getApellido(), getFechaNac(),
					getClass().getSimpleName(), nacionalidad);
		ManejadorPersistenciaJPA.getInstancia().agregarTuristaPendientePersistencia(usr);
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
		String nacionalidad = this.getNacionalidad();

		List<DTSalidaTuristica> inscripciones_salidas = new ArrayList<>();
		this.getInscripciones().forEach(
				(Inscripcion x) -> inscripciones_salidas.add(x.getSalidaTuristica().obtenerDTSalidaTuristica()));
		inscripciones_salidas.addAll(ManejadorPersistenciaJPA.getInstancia().obtenerSalidasDeTurista(getNickname()));

		List<DTCompra> compras = new ArrayList<>();
		this.getCompras().forEach((Compra x) -> compras.add(x.obtenerDTCompra()));

		List<DTInscripcion> inscripciones = new ArrayList<>();
		this.getInscripciones().forEach((Inscripcion x) -> inscripciones.add(x.obtenerDTInscripcion()));
		inscripciones.addAll(ManejadorPersistenciaJPA.getInstancia().obtenerInscripcionesDeTurista(nickname));

		return new DTTuristaDetallePrivado(nickname, nombre, apellido, correo, fechaNac, img, nacionalidad,
				inscripciones_salidas, compras, inscripciones, new ArrayList<>(getUsuariosSeguidos().keySet()),
				new ArrayList<>(getSeguidores().keySet()));
	}

	public boolean existeCompra(String nombrePaquete) {
		for (var compra : compras) {
			if (compra.correspondeAPaquete(nombrePaquete))
				return true;
		}
		return false;
	}

	public List<String> obtenerIdPaquetesConConsumoDisponibleParaActividad(String nombreActividad) {
		List<String> paquetes = new ArrayList<>();
		for (Compra comp : this.compras) {
			if (comp.tieneConsumoDisponibleParaActividad(nombreActividad)
					&& !LocalDate.now().isAfter(comp.getVencimiento())) {
				paquetes.add(comp.getPaquete().getNombre());
			}
		}
		return paquetes;
	}

	public void asociarCompra(Compra compra) {
		compras.add(compra);
	}

	public void agregarOEliminarActividadDeFavoritos(String nombreAct) throws ObjetoNoExisteEnTurismoUy {
		if (actividadesFavoritas.containsKey(nombreAct)) {
			actividadesFavoritas.remove(nombreAct);
			(new ControladorActividadTuristica()).disminuirCantidadDeFavoritos(nombreAct);
		} else {
			var actividad = ManejadorActividadTuristica.getInstancia().getActividad(nombreAct);
			actividadesFavoritas.put(nombreAct, actividad);
			(new ControladorActividadTuristica()).aumentarCantidadDeFavoritos(nombreAct);
		}
	}

	public Map<String, ActividadTuristica> getActividadesFavoritas() {
		return actividadesFavoritas;
	}

	public void setActividadesFavoritas(Map<String, ActividadTuristica> actividadesFavoritas) {
		this.actividadesFavoritas = actividadesFavoritas;
	}

	public boolean estaEnActividadesFavoritas(String nombreAct) {
		return actividadesFavoritas.containsKey(nombreAct);
	}

	public void eliminarInscripcion(String nombreSalida) {
		for (Inscripcion inscripcion : this.inscripciones)
			if (inscripcion.getNombreSalida().equals(nombreSalida)) {
				this.inscripciones.remove(inscripcion);
				return;
			}
	}
}
