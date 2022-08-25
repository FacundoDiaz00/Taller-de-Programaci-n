package logica.controladores;

import excepciones.*;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTSalidaTuristica;
import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;
import logica.entidades.SalidaTuristica;
import logica.entidades.Turista;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorDepartamento;
import logica.manejadores.ManejadorSalidaTuristica;
import logica.manejadores.ManejadorUsuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Equipo taller prog 16
 */

public class ControladorActividadTuristica implements IControladorActividadTuristica {

    public void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException {
        ManejadorDepartamento md = ManejadorDepartamento.getInstancia();
        if(!md.exists(nom)){
            Departamento dep = new Departamento(nom, descr, URL);
            md.addDepartamento(dep);
        } else {
            throw new DeparamentoYaRegistradoException("El departamento con nombre " + nom + " ya existe en el sistema");
        }

    }
	public ControladorActividadTuristica() {
	}

	
	public List<String> obtenerIdDepartamentos(){
		ManejadorDepartamento MU = ManejadorDepartamento.getInstancia();
		return new ArrayList<String>(MU.obtenerIdDepartamentos());
	}
	
	public void altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta ) throws ActividadTuristicaYaRegistradaException {
		if(!existeActividadTuristica(nombreActividad)) {
			//Se crea instancia:
			ActividadTuristica AT = new ActividadTuristica(nombreProveedor, departamento, nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);
			ManejadorActividadTuristica MAD = ManejadorActividadTuristica.getInstancia();
			MAD.addActividad(AT);
		} else {
			throw new ActividadTuristicaYaRegistradaException("Ya existe el usuario con el nombre " + nombreActividad);
		}
	}

	public boolean existeActividadTuristica(String nomActividad) {
		ManejadorActividadTuristica MAT = ManejadorActividadTuristica.getInstancia();
		return MAT.exists(nomActividad);
	}
    
    public List<String> obtenerIdActividadesTuristicas(String departamento){
		List<String> idActividades = new ArrayList<>();
    	ManejadorDepartamento mdep = ManejadorDepartamento.getInstancia();
		Departamento dep = mdep.getDepartamento(departamento);
		for(ActividadTuristica act : dep.getActividadTuristicas().values()){
			idActividades.add(act.getNombre());
		}
    	return idActividades;
    }
    
    public DTActividadTuristicaDetalle obtenerDetallesActividadTuristica(String nombreAct) {
    	ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
    	return mat.getActividad(nombreAct).obtenerDTActividadTuristicaDetalle();
    }

	@Override
	public List<DTSalidaTuristica> obtenerDTSalidasTuristicas(String nombreActTuri) {
		ArrayList<DTSalidaTuristica> dtsSal = new ArrayList<>();

		ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
		ActividadTuristica act = mat.getActividad(nombreActTuri);
		for (SalidaTuristica sal : act.getSalidas().values()){
			dtsSal.add(sal.obtenerDTSalidaTuristica());
		}
		return dtsSal;
	}

	@Override
	public void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris, LocalDate fechaInscrp) throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Turista turis = (Turista) mu.getUsuario(nicknameTuris);
		if(turis.estaInscriptoASalida(nomSalTurim)){
			throw new InscripcionYaRegistradaException("Ya exite una inscrpcion entre la salida " + nomSalTurim + " y el turista " + nicknameTuris);
		}
		ManejadorSalidaTuristica msal = ManejadorSalidaTuristica.getInstancia();
		SalidaTuristica sal = msal.getSalida(nomSalTurim);
		int cantidadInscrptos = sal.obtenerCantidadInscriptos();
		if(cantidadInscrptos + canTuris > sal.getCantMaxTuristas()){
			throw new SuperaElMaximoDeTuristasException("La salida " + nomSalTurim +  " con las inscripcion ya realizada no tiene la capacidad suficiente para soportar esta inscrpcion");
		}
		turis.altaInscripcionSalidaTuristica(sal,canTuris,fechaInscrp);
	}
	
	public void altaSalidaTuristica(String departamento, String actividadT, String nombre, LocalDate fecha, int hora, String lugar, int cantMaxTur) {
		ManejadorSalidaTuristica ms = ManejadorSalidaTuristica.getInstancia();
	}
}



