package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtUsuarioSeparadoPorTipo {

    private DTProveedor proveedor;
    private DTUsuario usuario;

    private boolean esProveedor;

    public DtUsuarioSeparadoPorTipo() {
    }

    public DtUsuarioSeparadoPorTipo(DTProveedor proveedor) {
        this.proveedor = proveedor;
        this.usuario = null;
        boolean esProveedor = true;
    }

    public DtUsuarioSeparadoPorTipo(DTUsuario usuario) {
        this.usuario = usuario;
        this.proveedor = null;
        boolean esProveedor = false;
    }

    public DTProveedor getProveedor() {
        return proveedor;
    }

    public DTUsuario getUsuario() {
        return usuario;
    }

    public boolean isEsProveedor() {
        return esProveedor;
    }
}
