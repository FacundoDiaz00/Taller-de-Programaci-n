package logica.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

import excepciones.ActividadTuristicaNoAceptada;
import excepciones.ActividadTuristicaYaRegistradaException;
import excepciones.AltaInscripcionPosteriorAFechaSalidaException;
import excepciones.CategoriaYaRegistradaException;
import excepciones.CompraConConsumosInsuficientesExcepcion;
import excepciones.CompraPaqueteVencidoExcepcion;
import excepciones.DeparamentoYaRegistradoException;
import excepciones.FechaAltaActividadPosteriorAFechaAltaSalidaException;
import excepciones.FechaAltaSalidaPosteriorAFechaSalidaException;
import excepciones.FechaAltaSalidaTuristicaPosteriorAFechaInscripcion;
import excepciones.InscripcionYaRegistradaException;
import excepciones.NoExisteConsumoParaLaActividadExcepcion;
import excepciones.ObjetoNoExisteEnTurismoUy;
import excepciones.PaqueteNoCompradoExcepcion;
import excepciones.SalidaYaRegistradaException;
import excepciones.SuperaElMaximoDeTuristasException;
import excepciones.TurismoUyException;
import logica.datatypes.*;
import logica.datatypes.comparadores.ComparatorDTActividadTuristicaByFechaCreacion;
import logica.datatypes.comparadores.ComparatorDTActividadTuristicaByNombre;
import logica.entidades.ActividadTuristica;
import logica.entidades.Categoria;
import logica.entidades.Compra;
import logica.entidades.Departamento;
import logica.entidades.Inscripcion;
import logica.entidades.SalidaTuristica;
import logica.entidades.Turista;
import logica.manejadores.ManejadorActividadTuristica;
import logica.manejadores.ManejadorCategoria;
import logica.manejadores.ManejadorDepartamento;
import logica.manejadores.ManejadorPersistenciaJPA;
import logica.manejadores.ManejadorSalidaTuristica;
import logica.manejadores.ManejadorUsuario;

/**
 * @author Equipo taller prog 16
 */

public class ControladorActividadTuristica implements IControladorActividadTuristica {

    public void altaDepartamento(String nom, String descr, String URL) throws DeparamentoYaRegistradoException {
        ManejadorDepartamento manejadorDepartamento = ManejadorDepartamento.getInstancia();
        if (!manejadorDepartamento.exists(nom)) {
            Departamento dep = new Departamento(nom, descr, URL);
            manejadorDepartamento.addDepartamento(dep);
        } else {
            throw new DeparamentoYaRegistradoException(
                    "El departamento con nombre " + nom + " ya existe en el sistema");
        }

    }

    public ControladorActividadTuristica() {
    }

    public List<String> obtenerIdDepartamentos() {
        return new ArrayList<String>(ManejadorDepartamento.getInstancia().obtenerIdDepartamentos());
    }

    public List<String> obtenerIdCategorias() {
        return new ArrayList<String>(ManejadorCategoria.getInstancia().obtenerIdCategorias());
    }

    public void altaActividadTuristica(String nombreProveedor, String departamento, String nombreActividad,
            String descripcion, int duracion, float costo, String ciudad, LocalDate fechaAlta, Imagen img,
            List<String> categorias, String urlVideo) throws ActividadTuristicaYaRegistradaException, ObjetoNoExisteEnTurismoUy {
        if (fechaAlta == null)
            fechaAlta = LocalDate.now();

        if (existeActividadTuristica(nombreActividad)) {
        	throw new ActividadTuristicaYaRegistradaException(
                    "Ya existe la actividad con el nombre " + nombreActividad);
        } 
        // Se crea instancia:
        ActividadTuristica actTuristica = new ActividadTuristica(nombreProveedor, departamento, nombreActividad,
                descripcion, duracion, costo, ciudad, fechaAlta, img, categorias, urlVideo);
        ManejadorActividadTuristica MAD = ManejadorActividadTuristica.getInstancia();
        MAD.addActividad(actTuristica);
    
    }

    public boolean existeActividadTuristica(String nomActividad) {
        ManejadorActividadTuristica MAT = ManejadorActividadTuristica.getInstancia();
        return MAT.exists(nomActividad) || ManejadorPersistenciaJPA.getInstancia().obtenerIdActividadesFinalizadas().contains(nomActividad);
    }

    public List<String> obtenerIdActividadesTuristicas(String departamento) throws ObjetoNoExisteEnTurismoUy {
        List<String> idActividades = new ArrayList<>();
        ManejadorDepartamento mdep = ManejadorDepartamento.getInstancia();
        Departamento dep = mdep.getDepartamento(departamento);
        for (ActividadTuristica act : dep.getActividadTuristicas().values()) {
            idActividades.add(act.getNombre());
        }
        return idActividades;
    }

    public List<String> obtenerIdActividadesTuristicasConfirmadasPorCategoria(String categoria)
            throws ObjetoNoExisteEnTurismoUy {
        return ManejadorCategoria.getInstancia().getCategoria(categoria).obtenerIdActividadesTuristicasConfirmadas();
    }

    public DTActividadTuristicaDetalle obtenerDTActividadTuristicaDetalle(String nombreAct)
            throws ObjetoNoExisteEnTurismoUy {
    	DTActividadTuristicaDetalle act;
    	if (ManejadorActividadTuristica.getInstancia().exists(nombreAct))
    		act = ManejadorActividadTuristica.getInstancia().getActividad(nombreAct).obtenerDTActividadTuristicaDetalle();
    	else
    		act = ManejadorPersistenciaJPA.getInstancia().obtenerDTActividadTuristicaDetalle(nombreAct);
        
    	if (act == null) 
    		throw new ObjetoNoExisteEnTurismoUy(ActividadTuristica.class);
    	
        return act;
    }

    public List<DTActividadTuristica> obtenerDTActividadesTuristicas() {
        var ret = new ArrayList<DTActividadTuristica>();
        for (var activ : ManejadorActividadTuristica.getInstancia().getActividades()) {
            if (activ.estaAceptada())
                ret.add(activ.obtenerDTActividadTuristica());
        }
        return ret;
    }

    @Override
    public List<DTActividadTuristica> obtenerDTActividadesTuristicasConfirmadasPorCategoria(String nomCat)
            throws ObjetoNoExisteEnTurismoUy {
        Categoria cat = ManejadorCategoria.getInstancia().getCategoria(nomCat);
        List<DTActividadTuristica> dtActis = new ArrayList<>();
        for (ActividadTuristica act : cat.getActividades().values()) {
            if (act.getEstado() == EstadoActividadTuristica.ACEPTADA) {
                dtActis.add(act.obtenerDTActividadTuristica());
            }
        }
        return dtActis;
    }

    @Override
    public List<DTActividadTuristica> obtenerDTActividadesTuristicasConfirmadasPorDepartamento(String nomDep)
            throws ObjetoNoExisteEnTurismoUy {
        Departamento dep = ManejadorDepartamento.getInstancia().getDepartamento(nomDep);
        List<DTActividadTuristica> dtActis = new ArrayList<>();
        for (ActividadTuristica act : dep.getActividadTuristicas().values()) {
            if (act.getEstado() == EstadoActividadTuristica.ACEPTADA)
                dtActis.add(act.obtenerDTActividadTuristica());
        }
        return dtActis;
    }

    @Override
    public List<DTSalidaTuristica> obtenerDTSalidasTuristicas(String nombreActTuri) throws ObjetoNoExisteEnTurismoUy {
        ArrayList<DTSalidaTuristica> dtsSal = new ArrayList<>();

        ManejadorActividadTuristica mat = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica act = mat.getActividad(nombreActTuri);
        for (SalidaTuristica sal : act.getSalidas().values()) {
            dtsSal.add(sal.obtenerDTSalidaTuristica());
        }
        return dtsSal;
    }

    public List<String> obtenerIdComprasDisponiblesParaInscripcion(String nombreActividad, String nickTurista)
            throws ObjetoNoExisteEnTurismoUy {
        ControladorUsuario contUser = new ControladorUsuario();
        Turista turi = contUser.obtenerTurista(nickTurista);
        return turi.obtenerIdPaquetesConConsumoDisponibleParaActividad(nombreActividad);
    }

    @Override
    public void altaInscripcionSalidaTuristica(String nomSalTurim, String nicknameTuris, int canTuris,
            LocalDate fechaInscripcion, String nombrePaquete)
            throws InscripcionYaRegistradaException, SuperaElMaximoDeTuristasException,
            FechaAltaSalidaTuristicaPosteriorAFechaInscripcion, AltaInscripcionPosteriorAFechaSalidaException,
            PaqueteNoCompradoExcepcion, CompraPaqueteVencidoExcepcion, CompraConConsumosInsuficientesExcepcion,
            NoExisteConsumoParaLaActividadExcepcion, ObjetoNoExisteEnTurismoUy {

        if (fechaInscripcion == null) {
            fechaInscripcion = LocalDate.now();
        }

        ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstancia();
        Turista turis = (Turista) manejadorUsuario.getUsuarioPorNick(nicknameTuris);
        if (turis.estaInscriptoASalida(nomSalTurim)) {
            throw new InscripcionYaRegistradaException(
                    "Ya exite una inscrpcion para la salida " + nomSalTurim + " del turista " + nicknameTuris);
        }
        ManejadorSalidaTuristica msal = ManejadorSalidaTuristica.getInstancia();
        SalidaTuristica sal = msal.getSalida(nomSalTurim);
        int cantidadInscrptos = sal.obtenerCantidadInscriptos();
        if (cantidadInscrptos + canTuris > sal.getCantMaxTuristas()) {
            throw new SuperaElMaximoDeTuristasException("La salida " + nomSalTurim
                    + " no tiene la capacidad suficiente para soportar esta inscrpcion. El maximo es "
                    + sal.getCantMaxTuristas());
        }

        Compra compraUtilizadaEnInscrpicon = null;
        String nombreActividad = sal.getActividad().getNombre();
        if (nombrePaquete != null) {
            compraUtilizadaEnInscrpicon = turis.obtenerCompraParaNombrePaquete(nombrePaquete);
            if (compraUtilizadaEnInscrpicon == null) {
                throw new PaqueteNoCompradoExcepcion(
                        "El paquete " + nombrePaquete + " no fue comprado por el turista " + nicknameTuris);
            }
            if (fechaInscripcion.isAfter(compraUtilizadaEnInscrpicon.getVencimiento())) {
                throw new CompraPaqueteVencidoExcepcion("La compra de este paquete esta vencida");
            }
            if (canTuris > compraUtilizadaEnInscrpicon.obtenerConsumosRestantesParaActividad(nombreActividad)) {
                throw new CompraConConsumosInsuficientesExcepcion(
                        "La compra no tiene un numero suficiente de consumos disponibles para realizar esta inscripcion");
            }
        }
        turis.altaInscripcionSalidaTuristica(sal, canTuris, fechaInscripcion, compraUtilizadaEnInscrpicon,
                nombreActividad);

    }

    public void altaSalidaTuristica(String actividad, String nombre, LocalDateTime fechaYHoraSalida,
            LocalDate fechaAlta, String lugar, int cantMaxTur, Imagen img)
            throws SalidaYaRegistradaException, FechaAltaActividadPosteriorAFechaAltaSalidaException,
            FechaAltaSalidaPosteriorAFechaSalidaException, ObjetoNoExisteEnTurismoUy, ActividadTuristicaNoAceptada {
        
    	if (fechaAlta == null)
            fechaAlta = LocalDate.now();

        ManejadorSalidaTuristica manejadorSalida = ManejadorSalidaTuristica.getInstancia();
        ManejadorActividadTuristica manejadorActividad = ManejadorActividadTuristica.getInstancia();

        if (manejadorSalida.existeSalidaTuristica(nombre)) 
            throw new SalidaYaRegistradaException("La salida con nombre" + nombre + " ya existe en el sistema.");
        if (manejadorActividad.getActividad(actividad).getFechaAlta().isAfter(fechaAlta))
            throw new FechaAltaActividadPosteriorAFechaAltaSalidaException(
                    "La fecha de Registro de la salida debe ser posterior a la del alta de la actividad correspondiente.");
        if (fechaAlta.isAfter(ChronoLocalDate.from(fechaYHoraSalida))) 
            throw new FechaAltaSalidaPosteriorAFechaSalidaException(
                    "La fecha de la Salida debe ser posterior a la fecha de su registro");
        if (manejadorActividad.getActividad(actividad).getEstado() != EstadoActividadTuristica.ACEPTADA)
            throw new ActividadTuristicaNoAceptada("Se intentó registrar una salida a una actividad no aceptada");
        
        SalidaTuristica salidaTur = new SalidaTuristica(actividad, nombre, cantMaxTur, fechaAlta, fechaYHoraSalida, lugar, img);
        manejadorSalida.addSalida(salidaTur);
    }

    public List<String> obtenerIdSalidasTuristicas(String act) throws ObjetoNoExisteEnTurismoUy {
        ManejadorActividadTuristica manejActTur = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica actTuristica = manejActTur.getActividad(act);
        Map<String, SalidaTuristica> salidas = actTuristica.getSalidas();
        List<String> res = new ArrayList<>();
        for (String idSalida : salidas.keySet()) {
            res.add(idSalida);
        }
        return res;
    }

    public DTSalidaTuristica obtenerDTSalidaTuristica(String nomSal) throws ObjetoNoExisteEnTurismoUy {
        ManejadorSalidaTuristica manejSalTur = ManejadorSalidaTuristica.getInstancia();
        SalidaTuristica sal = manejSalTur.getSalida(nomSal);
        return sal.obtenerDTSalidaTuristica();
    }

    public DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle(String nomSal) throws ObjetoNoExisteEnTurismoUy {
    	DTSalidaTuristicaDetalle sal;
        if (ManejadorSalidaTuristica.getInstancia().existeSalidaTuristica(nomSal))
        	sal = ManejadorSalidaTuristica.getInstancia().getSalida(nomSal).obtenerDTSalidaTuristicaDetalle();
        else 
        	sal = ManejadorPersistenciaJPA.getInstancia().obtenerDTSalidaTuristicaDetalle(nomSal);
        
        if (sal == null)
        	throw new ObjetoNoExisteEnTurismoUy(SalidaTuristica.class); 
        
        
        return sal;
    }

    public DTInscripcion obtenerDTInscripcion(String nick, String nomSal) throws ObjetoNoExisteEnTurismoUy {
        ManejadorSalidaTuristica manejSalTur = ManejadorSalidaTuristica.getInstancia();
        SalidaTuristica sal = manejSalTur.getSalida(nomSal);
        var inscripciones = sal.getInscripciones();
        Iterator<Inscripcion> iteratorInscripciones = inscripciones.iterator();
        Inscripcion insc = null;
        boolean encontrado = false;
        while (!encontrado) {
            insc = iteratorInscripciones.next();
            if (insc.getTurista().getNickname().equals(nick)) {
                encontrado = true;
            }
        }
        return insc.obtenerDTInscripcion();
    }

    public List<String> obtenerIdActividadesTuristicasAgregadas() {
        List<String> idActividades = new ArrayList<>();
        ManejadorActividadTuristica manejActTur = ManejadorActividadTuristica.getInstancia();
        for (ActividadTuristica act : manejActTur.getActividades()) {
            if (act.getEstado() == EstadoActividadTuristica.AGREGADA)
                idActividades.add(act.getNombre());
        }
        return idActividades;
    }

    public void cambiarEstadoDeActividadTuristica(String idActividad, EstadoActividadTuristica nuevoEstado)
            throws TurismoUyException{
        ManejadorActividadTuristica manejActTur = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica act = manejActTur.getActividad(idActividad);
        
        if (nuevoEstado == EstadoActividadTuristica.AGREGADA)
            throw new TurismoUyException("No se puede cambiar el estado de una actividad a AGREGADA");

        if (nuevoEstado != EstadoActividadTuristica.FINALIZADA && act.getEstado() == EstadoActividadTuristica.AGREGADA) {
        	act.setEstado(nuevoEstado);
        } else if (nuevoEstado == EstadoActividadTuristica.FINALIZADA) {
        	
        	ControladorPaquete icp = new ControladorPaquete();
        	if (icp.actividadExisteEnAlgunPaquete(idActividad)) {
        		throw new TurismoUyException("No es posible finalizar una activdad que esté dentro de un paquete");
        	}
        	
			ManejadorPersistenciaJPA.getInstancia().persistirActividad(idActividad);
            act.eliminarLinks();
      
		} else {
            throw new TurismoUyException("No se puede cambiar el estado");
        }
    }

    public void altaCategoria(String nombre) throws CategoriaYaRegistradaException {
        ManejadorCategoria manejadorcAt = ManejadorCategoria.getInstancia();
        if (!manejadorcAt.exists(nombre)) {
            Categoria cat = new Categoria(nombre);
            manejadorcAt.addCategoria(cat);
        } else {
            throw new CategoriaYaRegistradaException("La categoria " + nombre + " ya existe en el sistema.");
        }
    }

	public void aumentarCantidadDeFavoritos(String nombreAct) throws ObjetoNoExisteEnTurismoUy {
		ManejadorActividadTuristica manejActTur = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica act = manejActTur.getActividad(nombreAct);
        
        act.incrementarCantidadDeFavoritos();
	}
	
	public void disminuirCantidadDeFavoritos(String nombreAct) throws ObjetoNoExisteEnTurismoUy {
		ManejadorActividadTuristica manejActTur = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica act = manejActTur.getActividad(nombreAct);
        
        act.decrementarCantidadDeFavoritos();
	}
	
    @Override
    public Map<String, List<DTSalidaTuristica>> obtenerDTSalidasTuristicas() {
        Map<String, List<DTSalidaTuristica>> res = new HashMap<>();
    	ManejadorActividadTuristica mact = ManejadorActividadTuristica.getInstancia();
    	for (ActividadTuristica act : mact.getActividades()) {
            if (act.getEstado() == EstadoActividadTuristica.ACEPTADA){
                List<DTSalidaTuristica> colecionActividades = new ArrayList<>();
                for (SalidaTuristica sal : act.getSalidas().values()){
                    colecionActividades.add(sal.obtenerDTSalidaTuristica());
                }
                res.put(act.getNombre(), colecionActividades);

            }
    	}
        return res;
    }

	@Override
	public void incrementarContadorActividad(String nombreAct) throws ObjetoNoExisteEnTurismoUy {
		ManejadorActividadTuristica manejActTur = ManejadorActividadTuristica.getInstancia();
        ActividadTuristica act = manejActTur.getActividad(nombreAct);
        
        act.incrementarContadorVisitas();
	}

	@Override
	public void incrementarContadorSalida(String nombreSal) throws ObjetoNoExisteEnTurismoUy {
		ManejadorSalidaTuristica manejSalTur = ManejadorSalidaTuristica.getInstancia();
        SalidaTuristica sal = manejSalTur.getSalida(nombreSal);
        
        sal.incrementarContadorVisitas();
	}

	@Override
	public List<String[]> obtenerDatosVisitas() {
		ArrayList<String[]> tuplas = new ArrayList<String[]>();
		
		for (var actividad: ManejadorActividadTuristica.getInstancia().getActividades()) {
			tuplas.add(
				new String[] { actividad.getNombre(), actividad.getProveedor().getNickname(), "Actividad", String.valueOf(actividad.getCantVisitas())}
			);
		}
		
		for (var salida: ManejadorSalidaTuristica.getInstancia().getSalidas()) {
			tuplas.add(
				new String[] { salida.getNombre(), salida.getActividad().getProveedor().getNickname(), "Salida", String.valueOf(salida.getCantVisitas())}
			);
		}
		
		// Ordeno
		tuplas.sort((String[] tupla1, String[] tupla2) -> Integer.valueOf(tupla1[3]).compareTo(Integer.valueOf(tupla2[3])));
		
		// Me quedo con el top 10
		return tuplas.subList(0, Math.min(10, tuplas.size()));
	}


    @Override
    public List<DTActividadTuristica> filtrarActividades(String filtro, String departamento, String categoria, TipoOrdenacion ordenacion) {
        ManejadorActividadTuristica mact = ManejadorActividadTuristica.getInstancia();
        List<DTActividadTuristica> actividades = new ArrayList<>();
        for (ActividadTuristica act : mact.getActividades()){
            /*
                En ninguna parte del caso de uso dice que solo son confirmadas, pero no le encuentro sentido que
                  sea de otro tipo en base a lo que siempre se dijo de donde se muestran los otros tipos de actividades
            */
            if (act.getEstado() == EstadoActividadTuristica.ACEPTADA){
                if (filtro == null || act.getNombre().contains(filtro) || act.getDescrpicion().contains(filtro)){
                    boolean debeIngresar = true;

                    if (categoria != null && !act.getCategorias().containsKey(categoria)) {
                        debeIngresar = false;
                    }
                    if (departamento != null && !act.getDepartamento().getNombre().equals(departamento)) {
                        debeIngresar = false;
                    }
                    if (debeIngresar) {
                        actividades.add(act.obtenerDTActividadTuristica());
                    }
                }
            }
        }

        switch (ordenacion){
            case ALFABETICAMENTE:
                actividades.sort(new ComparatorDTActividadTuristicaByNombre());
                break;

            case FECHA_PUBLICACION:
                actividades.sort(new ComparatorDTActividadTuristicaByFechaCreacion().reversed());
                break;
        }
        return actividades;
    }
}
