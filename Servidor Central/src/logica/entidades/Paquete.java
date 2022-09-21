package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTCompra;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTPaqueteDetalles;
import logica.datatypes.Imagen;

/**
 * @author Equipo taller prog 16
 */

public class Paquete {

	private String nombre;
	private String descrpicion;
	private int validez;
	private float descuento;
	private LocalDate fechaDeRegistro;
	private Map<String, ActividadTuristica> actividades;
	private Imagen img;
	private List<Compra> compras;
	private List<Categoria> categorias;

	public Paquete(String nombre, String descrpicion, int validez, float descuento, LocalDate fechaR, Imagen img) {
		setNombre(nombre);
		setDescrpicion(descrpicion);
		setValidez(validez);
		setDescuento(descuento);
		setActividades(new HashMap<>());
		setFechaDeRegistro(fechaR);
		setCompras(new ArrayList<Compra>());
		setCategorias(new ArrayList<Categoria>());
		setImagen(img);
	}

	public DTPaqueteDetalles obtenerDTPaqueteDetalle() {
		Map<String, DTActividadTuristica> mapDtAct = new HashMap<>();
		for (ActividadTuristica act : actividades.values()) {
			mapDtAct.put(act.getNombre(), act.obtenerDTActividadTuristica());
		}
		List<String> categorias = new ArrayList<String>();

		for (var cat : this.categorias) {
			categorias.add(cat.getNombre());
		}

		List<DTCompra> compras = new ArrayList<DTCompra>();

		for (var comp : this.compras) {
			compras.add(comp.obtenerDTCompra());
		}

		return new DTPaqueteDetalles(nombre, descrpicion, descuento, validez, categorias, img, mapDtAct, compras);
	}

	public DTPaquete obtenerDTPaquete() {
		List<String> cats = new ArrayList<String>();

		for (var cat : this.categorias) {
			cats.add(cat.getNombre());
		}

		return new DTPaquete(nombre, descrpicion, descuento, validez, cats, img);
	}

	@Override
	public boolean equals(Object obj) {
		return this.getNombre().equals(((Paquete) obj).getNombre());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescrpicion() {
		return descrpicion;
	}

	public void setDescrpicion(String descrpicion) {
		this.descrpicion = descrpicion;
	}

	public int getValidez() {
		return validez;
	}

	public void setValidez(int validez) {
		this.validez = validez;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public LocalDate fechaDeRegistro() {
		return fechaDeRegistro;
	}

	public void setFechaDeRegistro(LocalDate fechaReg) {
		this.fechaDeRegistro = fechaReg;
	}

	public Map<String, ActividadTuristica> getActividades() {
		return actividades;
	}

	public void setActividades(Map<String, ActividadTuristica> actividades) {
		this.actividades = actividades;
	}

	public Set<String> obtenerIdActividadesIncluidas() {
		return actividades.keySet();
	}

	public void agregarActividad(ActividadTuristica actividad) {
		actividades.put(actividad.getNombre(), actividad);
	}

	public void setImagen(Imagen img) {
		this.img = img;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public void setCategorias(List<Categoria> cats) {
		this.categorias = cats;
	}

	public float getCostoPorTurista() {
		float ret = 0.0f;

		for (var act : actividades.values()) {
			ret += act.getCostoPorTurista();
		}

		ret *= 1 - this.descuento / 100.0f;
		return ret;
	}
}
