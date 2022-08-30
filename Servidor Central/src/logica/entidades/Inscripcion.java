package logica.entidades;

import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTTurista;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class Inscripcion {

    private LocalDate fechaInscrpicion;
    private int cantidadTuristas;

    private Compra compra;
    private SalidaTuristica salidaTuristica;
    private Turista turista;

    public Inscripcion(LocalDate fechaInscrpicion, int cantidadTuristas, Compra compra,
            SalidaTuristica salidaTuristica, Turista tur) throws FechaAltaSalidaTuristicaPosteriorAFechaInscripcion  {
        if(salidaTuristica.getFechaAlta().isAfter(fechaInscrpicion)){
            throw new FechaAltaSalidaTuristicaPosteriorAFechaInscripcion("La fecha de inscripción es previa a la fecha de registro de la salida turística elegida. Modifique la fecha e inténtenlo de nuevo.");
        }
        setFechaInscrpicion(fechaInscrpicion);
        setCantidadTuristas(cantidadTuristas);
        setCompra(compra);
        salidaTuristica.agregarInscripcionASalida(this);
        setSalidaTuristica(salidaTuristica);
        setTurista(tur);
    }

    // Todo falta el calculo de costoInscripcion

    public boolean estaInscriptoASalida(String nomSalTuri){
        return salidaTuristica.getNombre().equals(nomSalTuri);
    }


    public LocalDate getFechaInscrpicion() {
        return fechaInscrpicion;
    }

    public void setFechaInscrpicion(LocalDate fechaInscrpicion) {
        this.fechaInscrpicion = fechaInscrpicion;
    }

    public int getCantidadTuristas() {
        return cantidadTuristas;
    }

    public void setCantidadTuristas(int cantidadTuristas) {
        this.cantidadTuristas = cantidadTuristas;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public SalidaTuristica getSalidaTuristica() {
        return salidaTuristica;
    }

    public void setSalidaTuristica(SalidaTuristica salidaTuristica) {
        this.salidaTuristica = salidaTuristica;
    }

    public String getNombreSalida() {
        return salidaTuristica.getNombre();
    }

    public void setTurista(Turista turista) {
        this.turista = turista;
    }

    public Turista getTurista() {
    	return this.turista;
    }
    
    public DTInscripcion obtenerDTInscripcion(){
    	return new DTInscripcion(getFechaInscrpicion(), getCantidadTuristas(), "", getSalidaTuristica().obtenerDTSalidaTuristica(),(DTTurista) getTurista().obtenerDTUsuario());
    }
}
