package logica.controladores;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.InscripcionYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTPaqueteDetalles;
import logica.datatypes.DTSalidaTuristica;

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
	List<String> obtenerIdDepartamentos();
	void altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad, String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta) throws ActividadTuristicaYaRegistradaException;
	DTActividadTuristicaDetalle obtenerDetallesActividadTuristica(String nombreAct);
	List<String> obtenerIdActividadesTuristicas(String departamento);
	boolean existeActividadTuristica(String nomActividad);

	//fin operaciones AAT

	/**
	 * Devueve los DtSalidasTuristas de todas las salidas asociadas con la actividad identificada con nombreActTuri
	 * @param nombreActTuri identificador de la actividad turistica
	 * @return
	 */
	List<DTSalidaTuristica> obtenerDTSalidasTuristicas(String nombreActTuri);

	/**
	 *
	 * @param nomSalTurim
	 * @param nicknameTuris
	 * @param canTuris
	 * @param fechaInscrp
	 * @throws InscripcionYaRegistradaException
	 * @throws SuperaElMaximoDeTuristasException
	 */
	void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris, LocalDate fechaInscrp) throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException;


}
