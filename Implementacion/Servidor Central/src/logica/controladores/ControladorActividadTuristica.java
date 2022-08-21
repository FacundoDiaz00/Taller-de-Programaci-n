package logica.controladores;

import logica.manejadores.ManejadorDepartamento;
import logica.controladores.IControladorActividadTuristica;
import logica.controladores.ControladorUsuario;
import logica.controladores.Fabrica;
import logica.entidades.ActividadTuristica;
import logica.entidades.Departamento;
import logica.manejadores.ManejadorActividadTuristica;

import java.util.*;
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
}
