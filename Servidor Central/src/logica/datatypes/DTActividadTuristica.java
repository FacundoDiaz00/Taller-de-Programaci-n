package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import logica.utils.UtilsDT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Equipo taller prog 16
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(DTActividadTuristicaDetalle.class)
public class DTActividadTuristica {
	private String nombre;
	private String descripcion;

	private String fechaAltaStr;
	private String cuidad;
	private int duracion;
	private float costoPorTurista;
	private String nicknameProveedor;
	private String departamento;
	private List<String> categorias;
	private Imagen img;
	private EstadoActividadTuristica estado;
	private int cantFavoritos;
	private String urlVideo;

	public DTActividadTuristica() {
	}

	public DTActividadTuristica(String nombre, String descripcion, float costoPorTurista, String cuidad, int duracion,
								LocalDate fechaAlta, String nicknameProveedor, String departamento, List<String> cats, Imagen img, EstadoActividadTuristica estado, int cantFavoritos, String urlVideo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costoPorTurista = costoPorTurista;
		this.cuidad = cuidad;
		this.duracion = duracion;
		this.fechaAltaStr = fechaAlta.format(UtilsDT.formatterLocalDate);
		this.nicknameProveedor = nicknameProveedor;
		this.departamento = departamento;
		this.categorias = cats;
		this.img = img;
		this.estado = estado;
		this.cantFavoritos = cantFavoritos;
		this.urlVideo = urlVideo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getCostoPorTurista() {
		return costoPorTurista;
	}

	public String getCuidad() {
		return cuidad;
	}

	public int getDuracion() {
		return duracion;
	}

	public LocalDate getFechaAlta() {
		return LocalDate.parse(this.fechaAltaStr, UtilsDT.formatterLocalDate);
	}

	public String getNicknameProveedor() {
		return nicknameProveedor;
	}

	public String getDepartamento() {
		return departamento;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public Imagen getImg() {
		return img;
	}
	
	public EstadoActividadTuristica getEstado() {
		return estado;
	}

	public int getCantFavoritos() {
		return cantFavoritos;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public String getFechaAltaStr() {
		return fechaAltaStr;
	}
}

