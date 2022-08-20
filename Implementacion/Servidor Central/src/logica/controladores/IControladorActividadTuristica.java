package logica.controladores;

import excepciones.DeparamentoYaRegistradoException;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorActividadTuristica {

    void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException;

    //Operaciones de CdeU: Alta de Actividad Turistica
	ArrayList<String> obtenerIdProveedores();
	ArrayList<String> obtenerIdDepartamentos();
	boolean altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta);


}
