package logica.controladores;

import excepciones.PaqueteYaRegistradoException;

/**
 * @author Equipo taller prog 16
 */


public interface IControladorPaquete {
    public void altaTurista(String nombre, String descripcion, int periodovalidez, float descuento) throws PaqueteYaRegistradoException;

}
