package logica.manejadores;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logica.entidades.SalidaTuristica;

/**
 * @author Equipo taller prog 16
 */

public class ManejadorSalidaTuristica {
    private static ManejadorSalidaTuristica instancia;

    private Map<String, SalidaTuristica> salidas;

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
    	//TODO: Corresponde a ALTA SALIDA TURISTICA
    	return true;
    }
}
