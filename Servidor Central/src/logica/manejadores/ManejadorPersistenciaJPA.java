package logica.manejadores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.ObjetoNoExisteEnTurismoUy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import logica.datatypes.DTActividadTuristica;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTInscripcion;
import logica.datatypes.DTSalidaTuristica;
import logica.datatypes.DTSalidaTuristicaDetalle;
import logica.entidades.ActividadTuristica;
import logica.jpa.ActividadJPA;
import logica.jpa.InscripcionJPA;
import logica.jpa.ProveedorJPA;
import logica.jpa.SalidaJPA;
import logica.jpa.TuristaJPA;
import logica.jpa.UsuarioJPA;

public class ManejadorPersistenciaJPA {
	private static ManejadorPersistenciaJPA instancia = null;

	private EntityManagerFactory entityManagerFactory = null;

	private Map<String, TuristaJPA> turistasPendientesDePersistir;
	private Map<String, ProveedorJPA> proveedoresPendientesDePersistir;

	private ManejadorPersistenciaJPA() {
		entityManagerFactory = Persistence.createEntityManagerFactory("default");
		turistasPendientesDePersistir = new HashMap<String, TuristaJPA>();
		proveedoresPendientesDePersistir = new HashMap<String, ProveedorJPA>();
	}

	public static ManejadorPersistenciaJPA getInstancia() {
		if (instancia == null) {
			instancia = new ManejadorPersistenciaJPA();
		}
		return instancia;
	}

	public void persistirActividad(String actividad) throws ObjetoNoExisteEnTurismoUy {
		ActividadTuristica act = ManejadorActividadTuristica.getInstancia().getActividad(actividad);

		if (encontrarActividadJPA(actividad) != null)
			return;

		ActividadJPA actJPA = act.obtenerActividadJPA();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.merge(actJPA);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		turistasPendientesDePersistir = new HashMap<String, TuristaJPA>();
		proveedoresPendientesDePersistir = new HashMap<String, ProveedorJPA>();
	}

	public void persistirModificacionUsuario(UsuarioJPA usuario) {
		// TODO actualizar los datos del usuario si ya está en la base
	}

	public List<DTActividadTuristica> obtenerActividadesFinalizadasDeProveedor(String nickname) {
		EntityManager entitiMan = entityManagerFactory.createEntityManager();
		entitiMan.getTransaction().begin();

		Query query = entitiMan.createQuery("SELECT a FROM ActividadJPA a WHERE a.proveedorJPA.nickname = ?1");
		List<ActividadJPA> result;
		try {
			result = query.setParameter(1, nickname).getResultList();
		} catch (NoResultException e) {
			result = new ArrayList<ActividadJPA>();
		}
		entitiMan.getTransaction().commit();
		entitiMan.close();

		var salida = new ArrayList<DTActividadTuristica>();

		result.forEach((ActividadJPA act) -> salida.add(act.obtenerDTActividadTuristica()));

		return salida;
	}

	public List<DTInscripcion> obtenerInscripcionesDeTurista(String nickname) {
		EntityManager ntityMan = entityManagerFactory.createEntityManager();
		ntityMan.getTransaction().begin();

		Query query = ntityMan.createQuery("SELECT i FROM InscripcionJPA i WHERE i.turistaJPA.nickname = ?1");
		List<InscripcionJPA> result;
		try {
			result = query.setParameter(1, nickname).getResultList();
		} catch (NoResultException e) {
			result = new ArrayList<InscripcionJPA>();
		}
		ntityMan.getTransaction().commit();
		ntityMan.close();

		var salida = new ArrayList<DTInscripcion>();

		result.forEach((InscripcionJPA insc) -> salida.add(insc.obtenerDTInscripcion()));

		return salida;
	}

	public List<DTSalidaTuristica> obtenerSalidasDeTurista(String nickname) {
		EntityManager entityMan = entityManagerFactory.createEntityManager();
		entityMan.getTransaction().begin();

		Query query = entityMan.createQuery("SELECT i.salidaJPA FROM InscripcionJPA i WHERE i.turistaJPA.nickname = ?1");
		List<SalidaJPA> result;
		try {
			result = query.setParameter(1, nickname).getResultList();
		} catch (NoResultException e) {
			result = new ArrayList<SalidaJPA>();
		}
		entityMan.getTransaction().commit();
		entityMan.close();

		var salida = new ArrayList<DTSalidaTuristica>();

		result.forEach((SalidaJPA sal) -> salida.add(sal.obtenerDTSalidaTuristica()));

		return salida;
	}

	public List<String> obtenerIdActividadesFinalizadas() {
		EntityManager eneityMan = entityManagerFactory.createEntityManager();
		eneityMan.getTransaction().begin();

		Query query = eneityMan.createQuery("SELECT a.nombre FROM ActividadJPA a");
		List<String> result = query.getResultList();

		eneityMan.getTransaction().commit();
		eneityMan.close();
		return result;
	}

	public ActividadJPA encontrarActividadJPA(String nombre) {
		EntityManager entityMan = entityManagerFactory.createEntityManager();
		entityMan.getTransaction().begin();

		TypedQuery<ActividadJPA> query = entityMan.createQuery("SELECT a FROM ActividadJPA a WHERE a.nombre = ?1",
				ActividadJPA.class);
		ActividadJPA result;
		try {
			result = query.setParameter(1, nombre).getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}
		entityMan.getTransaction().commit();
		entityMan.close();
		return result;
	}

	public ProveedorJPA encontrarProveedorJPA(String nickname) {
		EntityManager entityMan = entityManagerFactory.createEntityManager();
		entityMan.getTransaction().begin();

		TypedQuery<ProveedorJPA> query = entityMan.createQuery("SELECT a FROM ProveedorJPA a WHERE a.nickname = ?1",
				ProveedorJPA.class);
		ProveedorJPA result;
		try {
			result = query.setParameter(1, nickname).getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}
		if (result == null) {
			result = proveedoresPendientesDePersistir.get(nickname);
		}

		entityMan.getTransaction().commit();
		entityMan.close();
		return result;
	}
	
	public TuristaJPA encontrarTuristaJPA(String nickname) {
		EntityManager entityMan = entityManagerFactory.createEntityManager();
		entityMan.getTransaction().begin();

		TypedQuery<TuristaJPA> query = entityMan.createQuery("SELECT a FROM TuristaJPA a WHERE a.nickname = ?1",
				TuristaJPA.class);
		TuristaJPA result;
		try {
			result = query.setParameter(1, nickname).getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}
		if (result == null) {
			result = turistasPendientesDePersistir.get(nickname);
		}

		entityMan.getTransaction().commit();
		entityMan.close();
		return result;
	}

	public DTActividadTuristicaDetalle obtenerDTActividadTuristicaDetalle(String nombreAct) {
		var actJPA = encontrarActividadJPA(nombreAct);
		if (actJPA == null)
			return null;

		return actJPA.obtenerDTActividadTuristicaDetalle();
	}

	public DTSalidaTuristicaDetalle obtenerDTSalidaTuristicaDetalle(String nomSal) {
		var salJPA = encontrarSalidaJPA(nomSal);
		if (salJPA == null)
			return null;

		return salJPA.obtenerDTSalidaTuristicaDetalle();
	}

	private SalidaJPA encontrarSalidaJPA(String nomSal) {
		EntityManager entityMan = entityManagerFactory.createEntityManager();
		entityMan.getTransaction().begin();

		TypedQuery<SalidaJPA> query = entityMan.createQuery("SELECT a FROM SalidaJPA a WHERE a.nombre = ?1", SalidaJPA.class);
		SalidaJPA result;
		try {
			result = query.setParameter(1, nomSal).getSingleResult();
		} catch (NoResultException e) {
			result = null;
		}

		entityMan.getTransaction().commit();
		entityMan.close();
		return result;
	}

	public void agregarTuristaPendientePersistencia(TuristaJPA usr) {
		turistasPendientesDePersistir.put(usr.getNickname(), usr);
	}
	
	public void agregarProveedorPendientePersistencia(ProveedorJPA usr) {
		proveedoresPendientesDePersistir.put(usr.getNickname(), usr);
	}
}
