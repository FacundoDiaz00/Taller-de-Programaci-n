package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Equipo taller prog 16
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DTActividadTuristicaDetalle extends DTActividadTuristica {
	private Map<String, DTSalidaTuristica> salidas;
	private Map<String, DTPaquete> paquetes;

	public DTActividadTuristicaDetalle(){}

	public DTActividadTuristicaDetalle(Map<String, DTSalidaTuristica> salidas, Map<String, DTPaquete> paquetes,
			String nombre, String descripcion, float costoPorTurista, String cuidad, int duracion, LocalDate fechaAlta,
			String nickProveedor, String departamento, List<String> categorias, Imagen img, EstadoActividadTuristica estado, int cantFavoritos, String urlVideo) {
		super(nombre, descripcion, costoPorTurista, cuidad, duracion, fechaAlta, nickProveedor, departamento,
				categorias, img, estado, cantFavoritos, urlVideo);
		this.salidas = salidas;
		this.paquetes = paquetes;
	}

	public Map<String, DTSalidaTuristica> getSalidas() {
		return salidas;
	}

	public Map<String, DTPaquete> getPaquetes() {
		return paquetes;
	}

	public DTSalidaTuristica getSalida(String nombre) {
		return salidas.get(nombre);
	}

	public DTPaquete getPaquete(String nombre) {
		return paquetes.get(nombre);
	}
}
