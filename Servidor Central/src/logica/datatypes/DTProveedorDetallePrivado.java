package logica.datatypes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTProveedorDetallePrivado extends DTProveedorDetalle {
	private List<DTActividadTuristica> actividadesAgregadas;
	private List<DTActividadTuristica> actividadesFinalizadas;
	private List<DTActividadTuristica> actividadesRechazadas;

	public DTProveedorDetallePrivado() {
		super(); 
	}
	
	public DTProveedorDetallePrivado(String nickname, String nombre, String apellido, String correo, LocalDate fechaNac,
			Imagen img, String desc, String url, List<DTActividadTuristicaDetalle> actividades,
			Map<EstadoActividadTuristica, List<DTActividadTuristica>> actividadesNoConfirmadas, List<String> seguidos, List<String> seguidores) {
		super(nickname, nombre, apellido, correo, fechaNac, img, desc, url, actividades, seguidos, seguidores);
		
		this.actividadesAgregadas = actividadesNoConfirmadas.get(EstadoActividadTuristica.AGREGADA);
		this.actividadesFinalizadas = actividadesNoConfirmadas.get(EstadoActividadTuristica.FINALIZADA);
		this.actividadesRechazadas = actividadesNoConfirmadas.get(EstadoActividadTuristica.RECHAZADA);
	}

	public Map<EstadoActividadTuristica, List<DTActividadTuristica>> getActividadesNoConfirmadas() {
		Map<EstadoActividadTuristica, List<DTActividadTuristica>> map = new HashMap<>();
		
		map.put(EstadoActividadTuristica.AGREGADA, actividadesAgregadas);
		map.put(EstadoActividadTuristica.FINALIZADA, actividadesFinalizadas);
		map.put(EstadoActividadTuristica.RECHAZADA, actividadesRechazadas);
		
		return map;
	}
}
