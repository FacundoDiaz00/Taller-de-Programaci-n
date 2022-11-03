package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.utils.UtilsDT;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class DTPaquete {
	private String nombre;
	private String descrpicion;
	private float descuento;
	private int validez;
	private List<String> categorias;
	private Imagen img;

	private String fechaRegistroStr;

	public DTPaquete(){}

	public DTPaquete(String nombre, String descrpicion, float descuento, int validez, List<String> categorias,
			LocalDate fechaAlta, Imagen img) {
		this.nombre = nombre;
		this.descrpicion = descrpicion;
		this.descuento = descuento;
		this.validez = validez;
		this.categorias = categorias;
		this.fechaRegistroStr = fechaAlta.format(UtilsDT.formatterLocalDate);
		this.img = img;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescrpicion() {
		return descrpicion;
	}

	public float getDescuento() {
		return descuento;
	}

	public int getValidez() {
		return validez;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public Imagen getImg() {
		return img;
	}

	public LocalDate getFechaRegistro() {
		return LocalDate.parse(fechaRegistroStr, UtilsDT.formatterLocalDate);
	}

	public String getFechaRegistroStr() {
		return fechaRegistroStr;
	}
}
