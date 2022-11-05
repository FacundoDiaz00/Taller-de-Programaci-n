package logica.datatypes.colleciones;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.datatypes.DTProveedor;
import logica.datatypes.DTTurista;
import logica.datatypes.DTUsuario;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuarioSeparadosPorTipoCollection {

    List<DTProveedor> proveedores;

    List<DTTurista> turistas;

    public DTUsuarioSeparadosPorTipoCollection() {
    }

    public DTUsuarioSeparadosPorTipoCollection(List<DTProveedor> proveedores, List<DTTurista> turistas) {
        this.turistas = turistas;
        this.proveedores = proveedores;
    }

    public List<DTTurista> getTuristas() {
        return turistas;
    }

    public List<DTProveedor> getProveedores() {
        return proveedores;
    }
}
