package logica.controladores;

import logica.datatypes.DTPaqueteDetalles;

import java.time.LocalDate;
import java.util.List;

import excepciones.PaqueteYaRegistradoException;

/**
 * @author Equipo taller prog 16
 */


public interface IControladorPaquete {
    void altaPaquete(String nombre, String descripcion, int periodovalidez, float descuento, LocalDate fechaR) throws PaqueteYaRegistradoException;
    List<String> obtenerIdPaquetes();

    List<String> obtenerIdActividadesDeDepartamentoQueNoEstanEnPaquete(String nombreDep, String nombrePaq);
    void agregarActividadAPaquete(String nombreAct, String nombrePaq);


    /**
     * Devuelve todos los DTPaqueteDetalles de todos los paquetes registrados en el sistema.
     * La misma consiste de todos los datos basicos del paquete y de sus actividades asociadas
     * @return
     */
    List<DTPaqueteDetalles> obtenerDetallesPaquetes();

}
