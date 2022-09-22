package logica.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.SalidaYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import logica.datatypes.*;
import logica.entidades.*;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorCategoria;
import logica.manejadores.ManejadorDepartamento;
import logica.manejadores.ManejadorSalidaTuristica;
import logica.manejadores.ManejadorUsuario;

/**
 * @author Equipo taller prog 16
 */

public class ControladorActividadTuristica implements IControladorActividadTuristica {

	public void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException {
		ManejadorDepartamento manejadorDepartamento = ManejadorDepartamento.getInstancia();
		if (!manejadorDepartamento.exists(nom)) {
			Departamento dep = new Departamento(nom, descr, URL);
			manejadorDepartamento.addDepartamento(dep);
		} else {
			throw new DeparamentoYaRegistradoException(
					"El departamento con nombre " + nom + " ya existe en el sistema");
		}

	}

	public ControladorActividadTuristica() {
	}

	public List<String> obtenerIdDepartamentos() {
		return new ArrayList<String>(ManejadorDepartamento.getInstancia().obtenerIdDepartamentos());
	}

	public List<String> obtenerIdCategorias() {
		return new ArrayList<String>(ManejadorCategoria.getInstancia().obtenerIdCategorias());
	}

	public void altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad,
			String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta, Imagen img,
			List<String> categorias) throws ActividadTuristicaYaRegistradaException, ObjetoNoExisteEnTurismoUy {

		if (!existeActividadTuristica(nombreActividad)) {
			// Se crea instancia:
			ActividadTuristica actTuristica = new ActividadTuristica(nombreProveedor, departamento, nombreActividad,
					descripcion, duracion, costo, ciudad, fechaAlta, img, categorias);
			ManejadorActividadTuristica MAD = ManejadorActividadTuristica.getInstancia();
			MAD.addActividad(actTuristica);
		} else {
			throw new ActividadTuristicaYaRegistradaException(
					"Ya existe la actividad con el nombre " + nombreActividad);
		}
	}

	public boolean existeActividadTuristica(String nomActividad) {
		ManejadorActividadTuristica MAT = ManejadorActividadTuristica.getInstancia();
		return MAT.exists(nomActividad);
	}

	public List<String> obtenerIdActividadesTuristicas(String departamento) throws ObjetoNoExisteEnTurismoUy {
		List<String> idActividades = new ArrayList<>();
		ManejadorDepartamento mdep = ManejadorDepartamento.getInstancia();
		Departamento dep = mdep.getDepartamento(departamento);
		for (ActividadTuristica act : dep.getActividadTuristicas().values()) {
			idActividades.add(act.getNombre());
		}
		return idActividades;
	}

	public List<String> obtenerIdActividadesTuristicasConfirmadasPorCategoria(String categoria)
			throws ObjetoNoExisteEnTurismoUy {
		return ManejadorCategoria.getInstancia().getCategoria(categoria).obtenerIdActividadesTuristicasConfirmadas();
	}

	public DTActividadTuristicaDetalle obtenerDTActividadTuristicaDetalle(String nombreAct)
			throws ObjetoNoExisteEnTurismoUy {
		ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
		return mat.getActividad(nombreAct).obtenerDTActividadTuristicaDetalle();
	}

	public List<DTActividadTuristica> obtenerDTActividadesTuristicas() {
		var ret = new ArrayList<DTActividadTuristica>();
		for (var activ : ManejadorActividadTuristica.getInstancia().getActividades()) {
			if (activ.estaAceptada())
				ret.add(activ.obtenerDTActividadTuristica());
		}
		return ret;
	}

	@Override
	public List<DTActividadTuristica> obtenerDTActividadesTuristicasConfirmadasPorCategoria(String nomCat) throws ObjetoNoExisteEnTurismoUy {
		Categoria cat = ManejadorCategoria.getInstancia().getCategoria(nomCat);
		List<DTActividadTuristica> dtActis = new ArrayList<>();
		for (ActividadTuristica act : cat.getActividades().values()){
			//if (act.getEstado() == EstadoActividadTuristica.ACEPTADA){ //todo agregar cuando es se deje el CU aceptar/rechasar actividad turistica
				dtActis.add(act.obtenerDTActividadTuristica());
			//}
		}
		return dtActis;
	}

	@Override
	public List<DTActividadTuristica> obtenerDTActividadesTuristicasConfirmadasPorDepartamento(String nomDep) throws ObjetoNoExisteEnTurismoUy{
		Departamento dep = ManejadorDepartamento.getInstancia().getDepartamento(nomDep);
		List<DTActividadTuristica> dtActis = new ArrayList<>();
		for (ActividadTuristica act : dep.getActividadTuristicas().values()){
			//if (act.getEstado() == EstadoActividadTuristica.ACEPTADA){ //todo agregar cuando es se deje el CU aceptar/rechasar actividad turistica
				dtActis.add(act.obtenerDTActividadTuristica());
			//}
		}
		return dtActis;
	}

	@Override
	public List<DTSalidaTuristica> obtenerDTSalidasTuristicas(String nombreActTuri) throws ObjetoNoExisteEnTurismoUy {
		ArrayList<DTSalidaTuristica> dtsSal = new ArrayList<>();

		ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
		ActividadTuristica act = mat.getActividad(nombreActTuri);
		for (SalidaTuristica sal : act.getSalidas().values()) {
			dtsSal.add(sal.obtenerDTSalidaTuristica());
		}
		return dtsSal;
	}

	public List<String> obtenerIdComprasDisponiblesParaInscripcion(String nombreActividad, String nickTurista) {
		// TODO ver DCOM
		return new ArrayList<String>();
	}

	@Override
	public void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris,
			String nombrePaquete) throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException,
			FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException {
		// TODO hacer algo con el nombrePaquete y calcular la fecha de insc

	}

	@Override
	public void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris,
			LocalDate fechaInscripcion) throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException,
			FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException,
			ObjetoNoExisteEnTurismoUy {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstancia();
		Turista turis = (Turista) manejadorUsuario.getUsuarioPorNick(nicknameTuris);
		if (turis.estaInscriptoASalida(nomSalTurim)) {
			throw new InscripcionYaRegistradaException(
					"Ya exite una inscrpcion entre la salida " + nomSalTurim + " y el turista " + nicknameTuris);
		}
		ManejadorSalidaTuristica msal = ManejadorSalidaTuristica.getInstancia();
		SalidaTuristica sal = msal.getSalida(nomSalTurim);
		int cantidadInscrptos = sal.obtenerCantidadInscriptos();
		if (cantidadInscrptos + canTuris > sal.getCantMaxTuristas()) {
			throw new SuperaElMaximoDeTuristasException("La salida " + nomSalTurim
					+ " con las inscripcion ya realizada no tiene la capacidad suficiente para soportar esta inscrpcion");
		}

		turis.altaInscripcionSalidaTuristica(sal, canTuris, fechaInscripcion);

	}

	public void altaSalidaTuristica(String actividad, String nombre, LocalDateTime fechaYHoraSalida,
			LocalDate fechaAlta, String lugar, int cantMaxTur, Imagen img)
			throws SalidaYaRegistradaException, FechaAltaActividadPosteriorAFechaAltaSalidaException,
			FechaAltaSalidaPosteriorAFechaSalidaException, ObjetoNoExisteEnTurismoUy {
		if (fechaAlta == null)
			fechaAlta = LocalDate.now();

		ManejadorSalidaTuristica manejadorSalida = ManejadorSalidaTuristica.getInstancia();
		ManejadorActividadTuristica manejadorActividad = ManejadorActividadTuristica.getInstancia();
		if (manejadorSalida.existeSalidaTuristica(nombre)) {
			throw new SalidaYaRegistradaException("La salida con nombre" + nombre + " ya existe en el sistema.");
		}
		// AltaActividad <= AltaSalida <= Salida, se chequean ambas
		// desigualdades.

		if (manejadorActividad.getActividad(actividad).getFechaAlta().isAfter(fechaAlta)) {
			throw new FechaAltaActividadPosteriorAFechaAltaSalidaException(
					"La fecha de Registro de la salida debe ser posterior a la del alta de la actividad correspondiente.");
		}
		if (fechaAlta.isAfter(ChronoLocalDate.from(fechaYHoraSalida))) {
			throw new FechaAltaSalidaPosteriorAFechaSalidaException(
					"La fecha de la Salida debe ser posterior a la fecha de su registro");
		} else {
			SalidaTuristica salidaTur = new SalidaTuristica(actividad, nombre, cantMaxTur, fechaAlta, fechaYHoraSalida,
					lugar, img);
			manejadorSalida.addSalida(salidaTur);
		}
	}

	public List<String> obtenerIdSalidasTuristicas(String act) throws ObjetoNoExisteEnTurismoUy {
		ManejadorActividadTuristica MAT = ManejadorActividadTuristica.getInstancia();
		ActividadTuristica actTuristica = MAT.getActividad(act);
		Map<String, SalidaTuristica> salidas = actTuristica.getSalidas();
		List<String> res = new ArrayList<>();
		for (String idSalida : salidas.keySet()) {
			res.add(idSalida);
		}
		return res;
	}

	public DTSalidaTuristica obtenerDTSalidaTuristica(String nomSal) throws ObjetoNoExisteEnTurismoUy {
		ManejadorSalidaTuristica MST = ManejadorSalidaTuristica.getInstancia();
		SalidaTuristica sal = MST.getSalida(nomSal);
		return sal.obtenerDTSalidaTuristica();
	}

	public DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle(String nomSal) throws ObjetoNoExisteEnTurismoUy {
		ManejadorSalidaTuristica MST = ManejadorSalidaTuristica.getInstancia();
		SalidaTuristica sal = MST.getSalida(nomSal);
		return sal.obtenerDTSalidaTuristicaDetalle();
	}

	public DTInscripcion obtenerDTInscripcion(String nick, String nomSal) throws ObjetoNoExisteEnTurismoUy {
		ManejadorSalidaTuristica MST = ManejadorSalidaTuristica.getInstancia();
		SalidaTuristica sal = MST.getSalida(nomSal);
		var inscripciones = sal.getInscripciones();
		Iterator<Inscripcion> iteratorInscripciones = inscripciones.iterator();
		Inscripcion insc = null;
		boolean encontrado = false;
		while (!encontrado) {
			insc = iteratorInscripciones.next();
			if (insc.getTurista().getNickname() == nick) {
				encontrado = true;
			}
		}
		return insc.obtenerDTInscripcion();
	}

}
