package logica.controladores;

import logica.manejadores.ManejadorDepartamento;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.ControladorUsuario;
import logica.controladores.Fabrica;
import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.time.LocalDate;

import excepciones.DeparamentoYaRegistradoException;
import logica.entidades.Departamento;
import logica.manejadores.ManejadorDepartamento;

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
	public ControladorActividadTuristica() {
	}
	
	public ArrayList<String> obtenerIdProveedores() {
		Fabrica F = Fabrica.getInstancia();
		IControladorUsuario ICU = F.getIControladorUsuario();
		return ICU.obtenerIdProveedores();	
	}
	
	public ArrayList<String> obtenerIdDepartamentos(){
		ManejadorDepartamento MU = ManejadorDepartamento.getInstancia();
		Map<String, Departamento> departamentos = MU.getDepartamentos();
		ArrayList<String> res = new ArrayList<String>();
		
		Iterator<String> it = departamentos.keySet().iterator();
		while(it.hasNext()){
		  res.add(it.next());
		}
		return res;
	}
	
	public boolean altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta ) {
		if(!existeActividadTuristica(nombreActividad)) {
			ActividadTuristica AT = new ActividadTuristica(nombreActividad, descripcion, duracion, costo, ciudad, fechaAlta);
			
			return true;
		}
		return false;
	}

	private boolean existeActividadTuristica(String nomActividad) {
		// TODO Auto-generated method stub
		return false;
	}
}
