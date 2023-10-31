package logica.datatypes.colleciones;



import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class DtMapActividadSalidaTuristicaCollection {

    private Map<String, DtSalidaTuristicaCollection> mapSalidas;

    public DtMapActividadSalidaTuristicaCollection() {
    }

    public DtMapActividadSalidaTuristicaCollection(Map<String, DtSalidaTuristicaCollection> mapSalidas) {
        this.mapSalidas = mapSalidas;
    }

    public Map<String, DtSalidaTuristicaCollection> getMapSalidas() {
        return mapSalidas;
    }

    public void setMapSalidas(Map<String, DtSalidaTuristicaCollection> mapSalidas) {
        this.mapSalidas = mapSalidas;
    }
}
