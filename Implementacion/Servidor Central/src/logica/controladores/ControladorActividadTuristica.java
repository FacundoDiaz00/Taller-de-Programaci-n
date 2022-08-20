package logica.controladores;

import excepciones.DeparamentoYaRegistradoException;
import logica.entidades.Departamento;
import logica.entidades.Paquete;
import logica.entidades.SalidaTuristica;
import logica.manejadores.ManejadorDepartamento;
import logica.manejadores.ManejadorActividadTuristica;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTSalidaTuristica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import logica.datatypes.DTActividadTuristicaDetalle;

import logica.entidades.ActividadTuristica;
import logica.datatypes.DTSalidaTuristica;

/**
 * @author Equipo taller prog 16
 */

public class ControladorActividadTuristica implements IControladorActividadTuristica {

    @Override
    public void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException {
        ManejadorDepartamento md = ManejadorDepartamento.getInstancia();
        if(!md.exists(nom)){
            Departamento dep = new Departamento(nom, descr, URL);
            md.addDepartamento(dep);
        } else {
            throw new DeparamentoYaRegistradoException("El departamento con nombre " + nom + " ya existe en el sistema");
        }

    }
    
    public ArrayList<String> obtenerIdActividadesTuristicas(String departamento){
    	ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
    	List<ActividadTuristica> actividades = mat.getActividades();
    	ArrayList<String> idActividades = new ArrayList();
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