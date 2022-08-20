package logica.controladores;

import logica.manejadores.ManejadorDepartamento;
import logica.controladores.IControladorActividadTuristica;
import logica.entidades.Departamento;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class ControladorActividadTuristica implements IControladorActividadTuristica {
	public ControladorActividadTuristica() {
	}
	
	public Collection<String> obtenerIdProveedores() {
		ControladorUsuario CU = ControladorUsuario();
		return CU.obtenerIDProveedores();	
	}
	
	public Collection<String> obtenerIdDepartamentos(){
		ManejadorDepartamento MU = ManejadorDepartamento.getInstancia();
		Map<String, Departamento> departamentos = MU.getDepartamentos();
		Collection<String> res = null;
		
		Iterator<String> it = departamentos.keySet().iterator();
		while(it.hasNext()){
		  res.add(it.next());
		}
		return res;
	}
	
	public boolean altaActividadTurística(String nomProv, String desc, int dur, int costo, String ciudad, LocalDate fecha ) {
		return false;
	}
}
