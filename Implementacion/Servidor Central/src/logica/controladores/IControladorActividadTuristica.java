package logica.controladores;

import excepciones.DeparamentoYaRegistradoException;
import logica.datatypes.DTActividadTuristicaDetalle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorActividadTuristica {


    /**
     * Crea un nuevo departamento en base a los parametros pasados
     * @param nom
     * @param descr
     * @param URL
     * @throws DeparamentoYaRegistradoException
     */
    void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException;

    //Operaciones de CdeU: Alta de Actividad Turistica
	List<String> obtenerIdProveedores();
	List<String> obtenerIdDepartamentos();
	boolean altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta);
	DTActividadTuristicaDetalle obtenerDetallesActividadTuristica(String nombreAct);
	ArrayList<String> obtenerIdActividadesTuristicas(String departamento);
	boolean existeActividadTuristica(String nomActividad);

	//fin operaciones AAT

}
