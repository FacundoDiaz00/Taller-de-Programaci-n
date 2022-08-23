package logica.controladores;

import excepciones.DeparamentoYaRegistradoException;
import excepciones.InscripcionYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
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
	
	public List<String> obtenerIdProveedores() {
		ControladorUsuario cu = new ControladorUsuario();
		return cu.obtenerIdProveedores();
	}
	
	public List<String> obtenerIdDepartamentos(){
		ManejadorDepartamento MU = ManejadorDepartamento.getInstancia();
		return new ArrayList<String>(MU.obtenerIdDepartamentos());
	}
	
	public boolean altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta ) {
		if(!existeActividadTuristica(nombreActividad)) {
			//Se crea instancia:
			ActividadTuristica AT = new ActividadTuristica(nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);
			
			//Se agrega a la coleccion de actividades:
			ManejadorActividadTuristica MAD = ManejadorActividadTuristica.getInstancia();
			MAD.addActividad(AT);
			
			//Se agrega la relacion con el departamento:
			ManejadorDepartamento MD = ManejadorDepartamento.getInstancia();
			Departamento d = MD.getDepartamento(departamento);
			d.asociarActividadTuristica(AT);
			
			//TODO: Se agrega la relacion con el proveedor:
			return true;
		}
		return false;
	}

	public boolean existeActividadTuristica(String nomActividad) {
		ManejadorActividadTuristica MAT = ManejadorActividadTuristica.getInstancia();
		return MAT.exists(nomActividad);
	}
    
    public ArrayList<String> obtenerIdActividadesTuristicas(String departamento){
    	ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
    	List<ActividadTuristica> actividades = mat.getActividades();
    	System.out.print(actividades.isEmpty());
    	ArrayList<String> idActividades = new ArrayList<String>();
    	for(var actividad: actividades) {
    		idActividades.add(actividad.getNombre());
    	}
    	return idActividades;
    }
    
    public DTActividadTuristicaDetalle obtenerDetallesActividadTuristica(String nombreAct) {
    	ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
    	ActividadTuristica actividad = mat.getActividad(nombreAct);
        Map<String, DTPaquete> paquetes = null;
        Map<String, DTSalidaTuristica> salidas = null;
    	for(var salida: actividad.getSalidas().entrySet()) {
    		DTSalidaTuristica salidaActual = new DTSalidaTuristica(salida.getValue().getNombre(),salida.getValue().getCantMaxTuristas(), salida.getValue().getFechaAlta(), salida.getValue().getFechaHoraSalida(), salida.getValue().getLugarSalida());
    		salidas.put(salida.getKey(), salidaActual);
    	};
    	for(var paquete: actividad.getPaquetes().entrySet()) {
    		DTPaquete paqueteActual = new DTPaquete(paquete.getValue().getNombre(), paquete.getValue().getDescrpicion(), paquete.getValue().getValidez(), paquete.getValue().getDescuento());
    		paquetes.put(paquete.getKey(),paqueteActual);
    	}
    	DTActividadTuristicaDetalle detalle = new DTActividadTuristicaDetalle(salidas, paquetes,actividad.getNombre(), actividad.getDescrpicion(), actividad.getCostoPorTurista(), actividad.getCuidad(), actividad.getDuracion(), actividad.getFechaAlta());
    	return detalle;
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
}
