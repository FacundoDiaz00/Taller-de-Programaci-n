package logica.controladores;

import excepciones.DeparamentoYaRegistradoException;

/**
 * @author Equipo taller prog 16
 */

public interface IControladorActividadTuristica {

    void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException;


}
