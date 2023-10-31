package logica.datatypes.colleciones;

import logica.datatypes.DTSalidaTuristica;

import java.util.List;

public class DtSalidaTuristicaCollection {
    private List<DTSalidaTuristica> salidas;

    public DtSalidaTuristicaCollection() {
    }

    public DtSalidaTuristicaCollection(List<DTSalidaTuristica> salidas) {
        this.salidas = salidas;
    }

    public List<DTSalidaTuristica> getSalidas() {
        return salidas;
    }

    public void setSalidas(List<DTSalidaTuristica> salidas) {
        this.salidas = salidas;
    }
}
