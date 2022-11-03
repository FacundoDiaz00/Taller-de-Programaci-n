package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTTuristaDetallePrivado extends DTTuristaDetalle {
	private List<DTCompra> compras;
	private List<DTInscripcion> dtInscripciones;
	
	public DTTuristaDetallePrivado() {
		
	}

	public DTTuristaDetallePrivado(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
			Imagen img, String nacionalidad, List<DTSalidaTuristica> inscripciones_salidas, List<DTCompra> compras,
			List<DTInscripcion> inscripciones, List<String> seguidos, List<String> seguidores) {
		super(nickname, nombre, apellido, correo, fechaNac, img, nacionalidad, inscripciones_salidas, seguidos, seguidores);
		this.compras = compras;
		this.dtInscripciones = inscripciones;
	}

	public List<DTCompra> getCompras() {
		return compras;
	}

	public List<DTInscripcion> getDTInscripciones() {
		return dtInscripciones;
	}
}
