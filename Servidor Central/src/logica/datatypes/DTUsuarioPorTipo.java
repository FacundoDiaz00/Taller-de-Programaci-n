package logica.datatypes;

import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuarioPorTipo {
	private boolean esTurista;

	private DTTurista dtTurista;

	private DTProveedor dtProveedor;
	
	public DTUsuarioPorTipo() {
		
	}
	
	public DTUsuarioPorTipo(DTUsuario usuario) {
		if (usuario instanceof DTTurista) {
			esTurista = true;
			dtTurista = (DTTurista) usuario;
		} else {
			esTurista = false;
			dtProveedor = (DTProveedor) usuario;
		}
	}
	
	public boolean esTurista() {
		return esTurista;
	}

	public DTTurista getDtTurista() {
		return dtTurista;
	}

	public DTProveedor getDtProveedor() {
		return dtProveedor;
	}	
}
