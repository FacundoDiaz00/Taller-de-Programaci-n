package logica.controladores;

import logica.datatypes.DTPaqueteDetalles;

import java.time.LocalDate;
import java.util.List;

import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.PaqueteYaRegistradoException;

/**
 * @author Equipo taller prog 16
 */


public interface IControladorPaquete {

    /**
     * Crea un paquete con los datos enviados por parámetro
     * @param nombre
     * @param descripcion
     * @param periodovalidez
     * @param descuento
     * @param fechaR
     * @throws PaqueteYaRegistradoException
     */
    void altaPaquete(String nombre, String descripcion, int periodovalidez, float descuento, LocalDate fechaR) throws PaqueteYaRegistradoException;

    /**
     * Debuelve de los nombres de todos los paquetes registrados en el sistema
     * @return
     */
    List<String> obtenerNombrePaquetes();

    /**
     *
     * @param nombreDep
     * @param nombrePaq
     * @return
     */
    List<String> obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String nombreDep, String nombrePaq);

    /**
     * Agrega una actividad turística identificada por 'nombreAct' al paquete 'nombrePaq'
     * @param nombreAct
     * @param nombrePaq
     * @throws ActividadTuristicaYaRegistradaException
     */
    void agregarActividadAPaquete(String nombreAct, String nombrePaq) throws ActividadTuristicaYaRegistradaException;


    /**
     * Devuelve todos los DTPaqueteDetalles de todos los paquetes registrados en el sistema.
     * La misma consiste de todos los datos basicos del paquete y de sus actividades asociadas
     * @return
     */
    List<DTPaqueteDetalles> obtenerDetallesPaquetes();

}
