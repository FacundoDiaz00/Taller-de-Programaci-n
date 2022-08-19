package logica.manejadores;

import java.util.Map;

import logica.entidades.Paquete;
/**
 * @author Equipo taller prog 16
 */

public class ManejadorPaquete {
    private ManejadorPaquete instancia;

    private Map<String, Paquete> paquetes;

    public ManejadorPaquete getInstancia(){
        if(instancia == null){
            instancia = new ManejadorPaquete();
        }
        return instancia;
    }

    public Map<String, Paquete> getPaquetes() {
        return paquetes;
    }

    public void addPaquete(Paquete paquete) {
        paquetes.put(paquete.getNombre(), paquete);
    }

    public Paquete getPaquete(String nombre) {
        return paquetes.get(nombre);
    }
    
}
