package logica.controladores;

import excepciones.DeparamentoYaRegistradoException;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTSalidaTuristica;
import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorDepartamento;

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
			//Se crea instancia
			ActividadTuristica AT = new ActividadTuristica(nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);
		
			//Se agrega a la coleccion
			ManejadorActividadTuristica MAD = ManejadorActividadTuristica.getInstancia();
			MAD.addActividad(AT);
			
			ManejadorDepartamento MD = ManejadorDepartamento.getInstancia();
			Departamento d = MD.getDepartamento(departamento);
		
			d.asociarActividadTuristica(AT);
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
}
