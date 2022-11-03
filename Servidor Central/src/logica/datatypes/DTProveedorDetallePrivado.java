package logica.datatypes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTProveedorDetallePrivado extends DTProveedorDetalle {
	private Map<EstadoActividadTuristica, List<DTActividadTuristica>> actividadesNoConfirmadas;

	public DTProveedorDetallePrivado() {
		super(); 
	}
	
	public DTProveedorDetallePrivado(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
			Imagen img, String desc, String url, List<DTActividadTuristicaDetalle> actividades,
			Map<EstadoActividadTuristica, List<DTActividadTuristica>> actividadesNoConfirmadas, List<String> seguidos, List<String> seguidores) {
		super(nickname, nombre, apellido, correo, fechaNac, img, desc, url, actividades, seguidos, seguidores);
		this.actividadesNoConfirmadas = actividadesNoConfirmadas;
	}

	public Map<EstadoActividadTuristica, List<DTActividadTuristica>> getActividadesNoConfirmadas() {
		return actividadesNoConfirmadas;
	}
}
