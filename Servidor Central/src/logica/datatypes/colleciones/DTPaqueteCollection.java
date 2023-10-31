package logica.datatypes.colleciones;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.datatypes.DTPaquete;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaqueteCollection {

    private List<DTPaquete> paquetes;

    public DTPaqueteCollection() {}

    public DTPaqueteCollection(List<DTPaquete> paquetes) {
        this.paquetes = paquetes;
    }

    public List<DTPaquete> getPaquetes() {
        return paquetes;
    }
}
