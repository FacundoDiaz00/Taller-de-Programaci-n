package logica.manejadores;

import java.util.ArrayList;
import java.util.List;

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
import logica.entidades.SalidaTuristica;
import logica.jpa.ActividadJPA;
import logica.jpa.SalidaJPA;
import logica.jpa.UsuarioJPA;

public class ManejadorPersistenciaJPA {
    private EntityManagerFactory entityManagerFactory = null;
    private static ManejadorPersistenciaJPA instancia = null;

    private ManejadorPersistenciaJPA() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
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
        entityManager.persist(actJPA);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void persistirModificacionUsuario(String usuario) {
        // TODO actualizar los datos del usuario si ya está en la base
    }

    public List<DTActividadTuristica> obtenerActividadesFinalizadasDeProveedor(String nickname) {
        // TODO buscar por nick en la base y devolver todas las actividades asociadas
        return new ArrayList<DTActividadTuristica>();
    }

    public List<DTInscripcion> obtenerInscripcionesDeTurista(String nickname) {
        // TODO buscar por nick en la base y devolver todas las inscripciones asociadas
        return new ArrayList<DTInscripcion>();
    }
    
    public List<DTSalidaTuristica> obtenerSalidasDeTurista(String nickname) {
        // TODO buscar por nick en la base y devolver todas las salidas asociadas
        return new ArrayList<DTSalidaTuristica>();
    }

    public List<String> obtenerIdActividadesFinalizadas() {
    	EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

  		Query query = em.createQuery("SELECT a.nombre FROM ActividadJPA a");
  		List<String> result = query.getResultList();
        
        em.getTransaction().commit();
        em.close();
        return result;
    }
    
    public ActividadJPA encontrarActividadJPA(String nombre) {
    	EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

  		TypedQuery<ActividadJPA> query = em.createQuery("SELECT a FROM ActividadJPA a WHERE a.nombre = ?1", ActividadJPA.class);
  		ActividadJPA result;
  		try {
  			result = query.setParameter(1, nombre).getSingleResult();
  		} catch (NoResultException e) {
  			result = null;
  		} 
        em.getTransaction().commit();
        em.close();
        return result;
    }
    
    public UsuarioJPA encontrarUsuarioJPA(String nickname) {
    	EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

  		TypedQuery<UsuarioJPA> query = em.createQuery("SELECT a FROM UsuarioJPA a WHERE a.nickname = ?1", UsuarioJPA.class);
  		UsuarioJPA result;
  		try {
  			result = query.setParameter(1, nickname).getSingleResult();
  		} catch (NoResultException e) {
  			result = null;
  		} 
        
        em.getTransaction().commit();
        em.close();
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
		EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

  		TypedQuery<SalidaJPA> query = em.createQuery("SELECT a FROM SalidaJPA a WHERE a.nombre = ?1", SalidaJPA.class);
  		SalidaJPA result;
  		try {
  			result = query.setParameter(1, nomSal).getSingleResult();
  		} catch (NoResultException e) {
  			result = null;
  		} 
        
        em.getTransaction().commit();
        em.close();
        return result;
	}
}
