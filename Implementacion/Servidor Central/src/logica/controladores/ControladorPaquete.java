package logica.controladores;

import excepciones.PaqueteYaRegistradoException;
import logica.entidades.Paquete;
import logica.manejadores.ManejadorPaquete;

/**
 * @author Equipo taller prog 16
 */

public class ControladorPaquete implements IControladorPaquete{
    public void altaPaquete(String nombre, String descripcion, int periodovalidez, float descuento) throws PaqueteYaRegistradoException {
        
    	ManejadorPaquete mp = ManejadorPaquete.getInstancia();
        
        if(mp.existePaquete(nombre)) {
            throw new PaqueteYaRegistradoException("Ya existe en el sistema un paquete con el nombre: "+nombre);
        }
        Paquete paq = new Paquete(nombre, descripcion, periodovalidez, descuento);
        mp.addPaquete(paq);
    }
}
