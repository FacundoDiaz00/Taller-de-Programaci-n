package logica.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.ObjetoNoExisteEnTurismoUy;
import logica.controladores.ControladorUsuario;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTPaquete;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.EstadoActividadTuristica;
import logica.datatypes.Imagen;
import logica.jpa.ActividadJPA;
import logica.jpa.ProveedorJPA;
import logica.jpa.SalidaJPA;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorCategoria;
import logica.manejadores.ManejadorDepartamento;

/**
 * @author Equipo taller prog 16
 */

public class ActividadTuristica {

    private String nombre;
    private String descrpicion;
    private int duracion;
    private float costoPorTurista;
    private String cuidad;
    private LocalDate fechaAlta;
    private EstadoActividadTuristica estado;
    private Imagen img;
    private int cantFavoritos;
    private String urlVideo;

    private Map<String, Paquete> paquetes;
    private Map<String, SalidaTuristica> salidas;

    private Proveedor proveedor;
    private Map<String, Categoria> categorias;

    private Departamento departamento;

    public ActividadTuristica(String nombreProveedor, String departamento, String nombre, String descrpicion,
            int duracion, float costoPorTurista, String cuidad, LocalDate fechaAlta, Imagen img,
            List<String> categorias, String urlVideo) throws ObjetoNoExisteEnTurismoUy {
        setNombre(nombre);
        setDescrpicion(descrpicion);
        setDuracion(duracion);
        setCostoPorTurista(costoPorTurista);
        setCuidad(cuidad);
        setFechaAlta(fechaAlta);
        setPaquetes(new HashMap<>());
        setSalidas(new HashMap<>());
        setCategorias(new HashMap<>());
        setImagen(img);
        estado = EstadoActividadTuristica.AGREGADA;
        setUrlVideo(urlVideo);

        // Se agrega a la coleccion de actividades:
        ManejadorActividadTuristica manejadorAct = ManejadorActividadTuristica.getInstancia();
        manejadorAct.addActividad(this);

        // Se agrega la relacion con el departamento:
        ManejadorDepartamento manejadorDep = ManejadorDepartamento.getInstancia();
        Departamento depObject = manejadorDep.getDepartamento(departamento);
        this.departamento = depObject;
        depObject.asociarActividadTuristica(this);

        // Se agrega la relacion con el proveedor:
        ControladorUsuario contUsuario = new ControladorUsuario();
        Proveedor prov = contUsuario.obtenerProveedor(nombreProveedor);
        prov.asociarActividadTuristica(this);
        proveedor = prov;

        // Se agrega la relacion con las categorias:
        ManejadorCategoria manejCat = ManejadorCategoria.getInstancia();

        if (categorias != null)
            for (var categoria : categorias) {
                Categoria catInstancia = manejCat.getCategoria(categoria);
                catInstancia.addActividad(this);
                this.categorias.put(categoria, catInstancia);
            }
    }

    public DTActividadTuristica obtenerDTActividadTuristica() {
        List<String> listaIdCats = new ArrayList<>();
        for (String idCat : categorias.keySet()) {
            listaIdCats.add(idCat);
        }

        return new DTActividadTuristica(getNombre(), getDescrpicion(), getCostoPorTurista(), getCuidad(), getDuracion(),
                getFechaAlta(), getProveedor().getNickname(), this.getDepartamento().getNombre(), listaIdCats,
                getImagen(), estado, cantFavoritos, urlVideo);
    }

    public DTActividadTuristicaDetalle obtenerDTActividadTuristicaDetalle() {
        HashMap<String, DTPaquete> packs = new HashMap<>();
        for (Paquete pack : paquetes.values()) {
            packs.put(pack.getNombre(), pack.obtenerDTPaquete());
        }
        HashMap<String, DTSalidaTuristica> salid = new HashMap<>();
        for (SalidaTuristica sal : salidas.values()) {
            salid.put(sal.getNombre(), sal.obtenerDTSalidaTuristica());
        }
        List<String> listaIdCats = new ArrayList<>();
        for (String idCat : categorias.keySet()) {
            listaIdCats.add(idCat);
        }

        return new DTActividadTuristicaDetalle(salid, packs, getNombre(), getDescrpicion(), getCostoPorTurista(),
                getCuidad(), getDuracion(), getFechaAlta(), getProveedor().getNickname(), getDepartamento().getNombre(),
                listaIdCats, getImagen(), estado, cantFavoritos, urlVideo);
    }

    @Override
    public boolean equals(Object obj) {
        return ((ActividadTuristica) obj).getNombre().equals(this.getNombre());
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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public float getCostoPorTurista() {
        return costoPorTurista;
    }

    public void setCostoPorTurista(float costoPorTurista) {
        this.costoPorTurista = costoPorTurista;
    }

    public String getCuidad() {
        return cuidad;
    }

    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Map<String, Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Map<String, Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public Map<String, SalidaTuristica> getSalidas() {
        return salidas;
    }

    public void setSalidas(Map<String, SalidaTuristica> salidas) {
        this.salidas = salidas;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Map<String, Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Map<String, Categoria> categorias) {
        this.categorias = categorias;
    }

    public EstadoActividadTuristica getEstado() {
        return estado;
    }

    public void setEstado(EstadoActividadTuristica est) {
        estado = est;
    }

    public List<String> obtenerIdSalidasTuristicas() {
        var listaSalidas = new ArrayList<String>();
        for (var salida : salidas.values()) {
            listaSalidas.add(salida.getNombre());
        }
        return listaSalidas;
    }

    public void agregarPaquete(Paquete paquete) {
        this.paquetes.put(paquete.getNombre(), paquete);
    }

    public void asociarSalidaAActividad(SalidaTuristica salidaTur) {
        salidas.put(salidaTur.getNombre(), salidaTur);
    }

    public boolean estaAceptada() {
        return estado == EstadoActividadTuristica.ACEPTADA;
    }

    public Imagen getImagen() {
        return img;
    }

    public void setImagen(Imagen img) {
        this.img = img;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

	public void incrementarCantidadDeFavoritos() {
		cantFavoritos = getCantFavoritos() + 1;
	}

	public void decrementarCantidadDeFavoritos() {
		cantFavoritos = getCantFavoritos() - 1;
	}

	public int getCantFavoritos() {
		return cantFavoritos;
	}

	public String getUrlVideo() {
		return urlVideo;
	}

	public void setUrlVideo(String urlVideo) {
		this.urlVideo = urlVideo;
	}

    public ActividadJPA obtenerActividadJPA() {
        var salidasJPA = new ArrayList<SalidaJPA>();
        salidas.values().forEach((SalidaTuristica s) -> salidasJPA.add(s.obtenerSalidaJPA()));
        return new ActividadJPA(nombre, descrpicion, duracion, costoPorTurista, cuidad,
                departamento.getNombre(), fechaAlta, salidasJPA, (ProveedorJPA) proveedor.obtenerUsuarioJPA());
    }
}
