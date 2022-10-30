package logica.datatypes.colleciones;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.datatypes.DTUsuario;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuarioCollection {

    List<DTUsuario> usuarios;

    public DTUsuarioCollection() {
    }

    public DTUsuarioCollection(List<DTUsuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<DTUsuario> getUsuarios() {
        return usuarios;
    }
}
