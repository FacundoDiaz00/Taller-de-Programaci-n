package logica.manejadores;

import java.util.*;

import logica.entidades.SalidaTuristica;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorSalidaTuristica {
    private static ManejadorSalidaTuristica instancia;

    private Map<String, SalidaTuristica> salidas;
    private ManejadorSalidaTuristica(){
        salidas = new HashMap<String, SalidaTuristica>();
    }
    public static ManejadorSalidaTuristica getInstancia() {
        if (instancia == null) {
            instancia = new ManejadorSalidaTuristica();
        }
        return instancia;
    }

    public List<SalidaTuristica> getSalidas() {
        return new ArrayList<SalidaTuristica>(salidas.values());
    }

    public Set<String> obtenerIdSalidasTuristicas(){
        return salidas.keySet();
    }

    public void addSalida(SalidaTuristica salida) {
        salidas.put(salida.getNombre(), salida);
    }

    public SalidaTuristica getSalida(String nombre) {
        return salidas.get(nombre);
    }
    
    public Boolean existeSalidaTuristica(String nombre) {
    	return salidas.containsKey(nombre);
    }
}
