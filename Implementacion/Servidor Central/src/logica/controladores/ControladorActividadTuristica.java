package logica.controladores;

import logica.manejadores.ManejadorDepartamento;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.ControladorUsuario;
import logica.controladores.Fabrica;


import logica.entidades.Departamento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class ControladorActividadTuristica implements IControladorActividadTuristica {
	public ControladorActividadTuristica() {
	}
	
	public ArrayList<String> obtenerIdProveedores() {
		Fabrica F = Fabrica.getInstancia();
		IControladorUsuario ICU = F.getIControladorUsuario();
		return ICU.obtenerIDProveedores();	
	}
	
	public ArrayList<String> obtenerIdDepartamentos(){
		ManejadorDepartamento MU = ManejadorDepartamento.getInstancia();
		Map<String, Departamento> departamentos = MU.getDepartamentos();
		ArrayList<String> res = null;
		
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
