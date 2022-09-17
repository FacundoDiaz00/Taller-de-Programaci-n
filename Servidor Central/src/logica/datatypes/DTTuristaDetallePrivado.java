package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class DTTuristaDetallePrivado extends DTTuristaDetalle {
    private List<DTCompra> compras;
    private List<DTInscripcion> dtInscripciones;

    public DTTuristaDetallePrivado(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, Imagen img, String nacionalidad, List<String> inscripciones_salidas, List<DTCompra> compras, List<DTInscripcion> inscripciones) {
        super(nickname, nombre, apellido, correo, fechaNac, img, nacionalidad, inscripciones_salidas);
        this.compras = compras;
        this.dtInscripciones = inscripciones;
    }

    public List<DTCompra> getCompras() {
        return compras;
    }

    public List<DTInscripcion> getDTInscripciones() {
        return dtInscripciones;
    }
}
