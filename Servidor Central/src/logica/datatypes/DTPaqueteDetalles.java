package logica.datatypes;

import java.util.List;
import java.util.Map;

/**
 * @author Equipo taller prog 16
 */

public class DTPaqueteDetalles extends DTPaquete {
    private Map<String, DTActividadTuristica> actividades;
    private List<DTCompra> compras;

    public DTPaqueteDetalles(String nombre, String descrpicion, float descuento, int validez, List<String> categorias, Imagen img, Map<String, DTActividadTuristica> actividades, List<DTCompra> compras) {
        super(nombre, descrpicion, descuento, validez, categorias, img);
        this.actividades = actividades;
        this.compras = compras;
    }

    public Map<String, DTActividadTuristica> getActividades() {
        return actividades;
    }

    public List<DTCompra> getCompras() {
        return compras;
    }
}
