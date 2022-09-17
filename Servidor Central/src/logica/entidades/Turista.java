package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import logica.datatypes.DTTurista;
import logica.datatypes.DTTuristaDetalle;
import logica.datatypes.DTUsuario;
import logica.datatypes.Imagen;

/**
 * @author Equipo taller prog 16
 */

public class Turista extends Usuario {
    private String nacionalidad;
    private Set<Compra> compras;
    private Set<Inscripcion> inscripciones;

    public Turista(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac, Imagen img, 
            String nacionalidad) {
        super(nickname, nombre, apellido, correo, fechaNac, img);
        setNacionalidad(nacionalidad);
        setCompras(new HashSet<>());
        setInscripciones(new HashSet<>());
    }


    public void altaInscripcionSalidaTuristica(SalidaTuristica sal, int canTuris, LocalDate fechaInscrp) throws FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException {
        Inscripcion insc = new Inscripcion(fechaInscrp, canTuris, null, sal, this);
        inscripciones.add(insc);
    }


    public boolean estaInscriptoASalida(String nomSalTuri){
        for (Inscripcion insc : inscripciones){
            if(insc.estaInscriptoASalida(nomSalTuri)){
                return true;
            }
        }
        return false;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Set<Compra> getCompras() {
        return compras;
    }

    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }

    public Set<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(Set<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    
    @Override
    public DTUsuario obtenerDTUsuario() {
    	return new DTTurista(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), getImagen(), nacionalidad);
    }

    @Override
    public DTUsuario obtenerDTUsuarioDetalle() {
        List<String> salidas = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            salidas.add(inscripcion.getNombreSalida());
        }

        return new DTTuristaDetalle(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), getImagen(), nacionalidad, salidas);
    }

    @Override
    public void setearDatos(DTUsuario datosNuevos) {
		super.setearDatos(datosNuevos);
		DTTurista tur = (DTTurista) datosNuevos;
		this.setNacionalidad(tur.getNacionalidad());
	}
}
