package logica.controladores;

import logica.datatypes.DTPaqueteDetalles;
import logica.entidades.Paquete;
import logica.manejadores.ManejadorPaquete;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class ControladorPaquete implements IControladorPaquete{
    public boolean altaTurista(String nombre, String descripcion, int periodovalidez, float descuento) {
        
    	ManejadorPaquete mp = ManejadorPaquete.getInstancia();
        
        if(mp.existePaquete(nombre)) {
            //TODO: throw exeption.
        	return false;
        }
        Paquete paq = new Paquete(nombre, descripcion, periodovalidez, descuento);
        mp.addPaquete(paq);
        return true; 
    }

    @Override
    public List<DTPaqueteDetalles> obtenerDetallesPaquetes() {
        ArrayList<DTPaqueteDetalles> dtsPacks = new ArrayList<>();
        ManejadorPaquete mp = ManejadorPaquete.getInstancia();
        for (Paquete pack : mp.getPaquetes()){
            dtsPacks.add(pack.obtenerDTPaqueteDetalle());
        }
        return dtsPacks;
    }
}
