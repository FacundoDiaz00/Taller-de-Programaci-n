package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import logica.datatypes.DTTurista;
import logica.datatypes.DTTuristaDetalle;
import logica.datatypes.DTUsuario;

/**
 * @author Equipo taller prog 16
 */

public class Turista extends Usuario {
    private String nacionalidad;
    private Set<Compra> compras;
    private Set<Inscripcion> inscripciones;

    public Turista(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
            String nacionalidad) {
        super(nickname, nombre, apellido, correo, fechaNac);
        setNacionalidad(nacionalidad);
        setCompras(new HashSet<>());
        setInscripciones(new HashSet<>());
    }


    public void altaInscripcionSalidaTuristica(SalidaTuristica sal, int canTuris, LocalDate fechaInscrp) throws FechaAltaSalidaTuristicaPosteriorAFechaInscripcion {
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
    	return new DTTurista(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), getNacionalidad());
    }

    @Override
    public DTUsuario obtenerDTUsuarioDetalle() {
        List<String> salidas = new ArrayList<>();

        for (Inscripcion inscripcion : inscripciones) {
            salidas.add(inscripcion.getNombreSalida());
        }

        return new DTTuristaDetalle(getNickname(), getNombre(), getApellido(), getCorreo(), getFechaNac(), getNacionalidad(), salidas);
    }

    @Override
    public void setearDatos(DTUsuario datosNuevos) {
		super.setearDatos(datosNuevos);
		DTTurista tur = (DTTurista) datosNuevos;
		this.setNacionalidad(tur.getNacionalidad());
	}
}
