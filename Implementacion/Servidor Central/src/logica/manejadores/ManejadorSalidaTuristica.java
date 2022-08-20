package logica.manejadores;

import java.util.Map;

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

    public Map<String, SalidaTuristica> getSalidas() {
        return salidas;
    }

    public void addSalida(SalidaTuristica salida) {
        salidas.put(salida.getNombre(), salida);
    }

    public SalidaTuristica getSalida(String nombre) {
        return salidas.get(nombre);
    }
}
