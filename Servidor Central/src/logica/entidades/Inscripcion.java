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
        this.fechaInscrpicion = fechaInscrpicion;
        this.cantidadTuristas = cantidadTuristas;
        this.compra = compra;
        salidaTuristica.agregarInscripcionASalida(this);
        this.salidaTuristica = salidaTuristica;
        this.turista = tur;
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
    
    public DTInscripcion obtenerDTInscripcion(){
    	return new DTInscripcion(this.fechaInscrpicion, this.cantidadTuristas, "", this.salidaTuristica.obtenerDTSalidaTuristica(),(DTTurista) this.turista.obtenerDTUsuario());
    }
}
