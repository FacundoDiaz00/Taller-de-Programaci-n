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
public class DTPaqueteDetalles extends DTPaquete {
	private Map<String, DTActividadTuristica> actividades;
	private List<DTCompra> compras;

	public DTPaqueteDetalles() {
		
	}
	
	public DTPaqueteDetalles(String nombre, String descrpicion, float descuento, int validez, List<String> categorias,
			LocalDate fechaAlta, Imagen img, Map<String, DTActividadTuristica> actividades, List<DTCompra> compras) {
		super(nombre, descrpicion, descuento, validez, categorias, fechaAlta, img);
		this.actividades = actividades;
		this.compras = compras;
	}

	public Map<String, DTActividadTuristica> getActividades() {
		return actividades;
	}

	public List<DTCompra> getCompras() {
		return compras;
	}
}
