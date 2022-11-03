package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuarioDetallePorTipo {
	private boolean esTurista;
	private DTTuristaDetalle dtTuristaDetalle;
	private DTProveedorDetalle dtProveedorDetalle;
	
	public DTUsuarioDetallePorTipo() {
		
	}
	
	public DTUsuarioDetallePorTipo(DTUsuario usuario) {
		if (usuario instanceof DTTuristaDetalle) {
			esTurista = true;
			dtTuristaDetalle = (DTTuristaDetalle) usuario;
		} else {
			esTurista = false;
			dtProveedorDetalle = (DTProveedorDetalle) usuario;
		}
	}
	
	public boolean esTurista() {
		return esTurista;
	}

	public DTTuristaDetalle getDtTuristaDetalle() {
		return dtTuristaDetalle;
	}

	public DTProveedorDetalle getDtProveedorDetalle() {
		return dtProveedorDetalle;
	}	
}
