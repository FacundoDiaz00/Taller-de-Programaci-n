package logica.controladores;

import excepciones.DeparamentoYaRegistradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorActividadTuristica {

    void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException;

    //Operaciones de CdeU: Alta de Actividad Turistica
	public ArrayList<String> obtenerIdProveedores();
	public Set<String> obtenerIdDepartamentos();
	public boolean altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta);
	//fin operaciones AAT

}
