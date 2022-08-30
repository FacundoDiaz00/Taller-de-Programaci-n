package logica.datatypes;

import java.time.LocalDate;

/**
 * @author Equipo taller prog 16
 */

public class DTTurista extends DTUsuario {
    private String nacionalidad;

    public DTTurista(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
            String nacionalidad) {
        super(nickname, nombre, apellido, correo, fechaNac);
        this.nacionalidad = nacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }
    
    @Override
    public boolean equals(Object e) {
    	try {
			var casted = (DTTurista) e;
			return casted.getNacionalidad().equals(this.nacionalidad) 
					&& casted.getNickname().equals(this.getNickname())
					&& casted.getNombre().equals(this.getNombre())
					&& casted.getApellido().equals(this.getApellido())
					&& casted.getCorreo().equals(this.getCorreo())
					&& casted.getFechaNac().equals(this.getFechaNac());
		} catch (Exception e2) {
			return false;
		}    	
    }
}
