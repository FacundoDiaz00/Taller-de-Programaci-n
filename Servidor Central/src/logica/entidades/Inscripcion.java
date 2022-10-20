package logica.entidades;

import java.time.LocalDate;

import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import logica.datatypes.DTInscripcion;

/**
 * @author Equipo taller prog 16
 */

public class Inscripcion {

    private LocalDate fechaInscrpicion;
    private int cantidadTuristas;

    private Compra compra;
    private SalidaTuristica salidaTuristica;
    private Turista turista;

    public Inscripcion(LocalDate fechaInscrpicion, int cantidadTuristas, SalidaTuristica salidaTuristica,
            Turista tur, Compra compra, String nombreActividad)
            throws FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException {
        // alta salida <= alta inscripcion <= fecha salida, se controlan ambas
        // desigualdades.
        if (salidaTuristica.getFechaAlta().isAfter(fechaInscrpicion)) {
            throw new FechaAltaSalidaTuristicaPosteriorAFechaInscripcion("La fecha de inscripcion: " +
                    fechaInscrpicion.toString() + " es anterior a la fecha de alta de la salida turística: "
                    + salidaTuristica.getFechaAlta() + ". Turista: " + tur.getNickname() + ". Salida: "
                    + salidaTuristica.getNombre());
        }
        if (fechaInscrpicion.isAfter(salidaTuristica.getFechaHoraSalida().toLocalDate())) {
            throw new AltaInscripcionPosteriorAFechaSalidaException(
                    "La fecha de inscripcion debe ser anterior a la fecha de la salida seleccionada.");
        }
        setFechaInscrpicion(fechaInscrpicion);
        setCantidadTuristas(cantidadTuristas);
        setCompra(compra);
        salidaTuristica.agregarInscripcionASalida(this);
        setSalidaTuristica(salidaTuristica);
        setTurista(tur);

        if (compra != null) {
            compra.descontarConsumos(nombreActividad, cantidadTuristas);
            compra.agregarInscripcion(this);
        }

    }

    public boolean estaInscriptoASalida(String nomSalTuri) {
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

    public float getCostoInscripcion() {
        var costo = cantidadTuristas * getSalidaTuristica().getActividad().getCostoPorTurista();

        if (compra != null)
            costo = costo * (1 - compra.getPaquete().getDescuento() / 100);

        return (float) costo;
    }

    public DTInscripcion obtenerDTInscripcion() {
        return new DTInscripcion(fechaInscrpicion, cantidadTuristas, getCostoInscripcion(), getSalidaTuristica().obtenerDTSalidaTuristica(),
                getTurista().getNickname());
    }
}
