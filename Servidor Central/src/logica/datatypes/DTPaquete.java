package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

public class DTPaquete {
	private String nombre;
	private String descrpicion;
	private float descuento;
	private int validez;
	private List<String> categorias;
	private Imagen img;
	private LocalDate fechaRegistro;

	public DTPaquete(String nombre, String descrpicion, float descuento, int validez, List<String> categorias,
			LocalDate fechaAlta, Imagen img) {
		this.nombre = nombre;
		this.descrpicion = descrpicion;
		this.descuento = descuento;
		this.validez = validez;
		this.categorias = categorias;
		this.fechaRegistro = fechaAlta;
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
		return fechaRegistro;
	}
}
