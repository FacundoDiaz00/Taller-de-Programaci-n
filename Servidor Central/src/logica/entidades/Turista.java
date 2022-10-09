package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import logica.datatypes.DTCompra;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTTurista;
import logica.datatypes.DTTuristaDetalle;
import logica.datatypes.DTTuristaDetallePrivado;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;

/**
 * @author Equipo taller prog 16
 */

public class Turista extends Usuario {
	private String nacionalidad;
	private Set<Compra> compras;
	private Set<Inscripcion> inscripciones;

	public Turista(String nickname, String nombre, String apellido, String correo, String contra, LocalDate fechaNac, Imagen img,
			String nacionalidad) {
		super(nickname, nombre, apellido, correo, contra, fechaNac, img);
		setNacionalidad(nacionalidad);
		setCompras(new HashSet<>());
		setInscripciones(new HashSet<>());
	}

	public void altaInscripcionSalidaTuristica(SalidaTuristica sal, int canTuris, LocalDate fechaInscrp, Compra comp, String nomActividad)
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

	public Compra obtenerCompraParaNombrePaquete(String nombrePaquete){
		for (Compra com : this.compras){
			if(com.correspondeAPaquete(nombrePaquete)){
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
		List<String> salidas = new ArrayList<>();

		for (Inscripcion inscripcion : inscripciones) {
			salidas.add(inscripcion.getNombreSalida());
		}

		return new DTTuristaDetalle(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), getImagen(),
				nacionalidad, salidas);
	}

	@Override
	public void setearDatos(DTUsuario datosNuevos) {
		super.setearDatos(datosNuevos);
		DTTurista tur = (DTTurista) datosNuevos;
		this.setNacionalidad(tur.getNacionalidad());
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

		List<String> inscripciones_salidas = new ArrayList<>();
		this.getInscripciones().forEach((Inscripcion x) -> inscripciones_salidas.add(x.getNombreSalida()));

		List<DTCompra> compras = new ArrayList<>();
		this.getCompras().forEach((Compra x) -> compras.add(x.obtenerDTCompra()));

		List<DTInscripcion> inscripciones = new ArrayList<>();
		this.getInscripciones().forEach((Inscripcion x) -> inscripciones.add(x.obtenerDTInscripcion()));

		return new DTTuristaDetallePrivado(nickname, nombre, apellido, correo, fechaNac, img, nacionalidad,
				inscripciones_salidas, compras, inscripciones);
	}

	public boolean existeCompra(String nombrePaquete) {
		for (var compra : compras) {
			if (compra.correspondeAPaquete(nombrePaquete))
				return true;
		}
		return false;
	}

	public List<String> obtenerIdPaquetesConConsumoDisponibleParaActividad(String nombreActividad){
		List<String> paquetes = new ArrayList<>();
		for(Compra comp : this.compras){
			if(comp.tieneConsumoDisponibleParaActividad(nombreActividad) && !LocalDate.now().isAfter(comp.getVencimiento())){
				paquetes.add(comp.getPaquete().getNombre());
			}
		}
		return paquetes;
	}

	public void asociarCompra(Compra compra) {
		compras.add(compra);
	}
}
