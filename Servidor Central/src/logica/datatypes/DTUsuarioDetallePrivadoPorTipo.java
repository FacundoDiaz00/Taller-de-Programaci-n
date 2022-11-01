package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTUsuarioDetallePrivadoPorTipo {
	private boolean esTurista;
	private DTTuristaDetallePrivado dtTuristaDetalle;
	private DTProveedorDetallePrivado dtProveedorDetalle;
	
	public DTUsuarioDetallePrivadoPorTipo() {
		
	}
	
	public DTUsuarioDetallePrivadoPorTipo(DTUsuario usuario) {
		if (usuario instanceof DTTuristaDetalle) {
			esTurista = true;
			dtTuristaDetalle = (DTTuristaDetallePrivado) usuario;
		} else {
			esTurista = false;
			dtProveedorDetalle = (DTProveedorDetallePrivado) usuario;
		}
	}
	
	public boolean esTurista() {
		return esTurista;
	}

	public DTTuristaDetallePrivado getDtTuristaDetalle() {
		return dtTuristaDetalle;
	}

	public DTProveedorDetallePrivado getDtProveedorDetalle() {
		return dtProveedorDetalle;
	}	
}
