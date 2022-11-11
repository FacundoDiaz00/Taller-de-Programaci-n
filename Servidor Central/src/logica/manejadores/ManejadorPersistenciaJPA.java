package logica.manejadores;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import configuraciones.Cargador;
import excepciones.ObjetoNoExisteEnTurismoUy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
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
import logica.jpa.InscripcionJPA;
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

    public void persistirModificacionUsuario(UsuarioJPA usuario) {
        // TODO actualizar los datos del usuario si ya está en la base
    }

    public List<DTActividadTuristica> obtenerActividadesFinalizadasDeProveedor(String nickname) {
    	EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

  		Query query = em.createQuery("SELECT a FROM ActividadJPA a WHERE a.proveedorJPA.nickname = ?1");
  		List<ActividadJPA> result;
  		try {
  			result = query.setParameter(1, nickname).getResultList();
  		} catch (NoResultException e) {
  			result = new ArrayList<ActividadJPA>();
  		} 
        em.getTransaction().commit();
        em.close();
        
        var salida = new ArrayList<DTActividadTuristica>();
        
        result.forEach((ActividadJPA act) -> salida.add(act.obtenerDTActividadTuristica()));
        
        return salida;
    }

    public List<DTInscripcion> obtenerInscripcionesDeTurista(String nickname) {
    	EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

  		Query query = em.createQuery("SELECT i FROM InscripcionJPA i WHERE i.turistaJPA.nickname = ?1");
  		List<InscripcionJPA> result;
  		try {
  			result = query.setParameter(1, nickname).getResultList();
  		} catch (NoResultException e) {
  			result = new ArrayList<InscripcionJPA>();
  		} 
        em.getTransaction().commit();
        em.close();
        
        var salida = new ArrayList<DTInscripcion>();
        
        result.forEach((InscripcionJPA insc) -> salida.add(insc.obtenerDTInscripcion()));
        
        return salida;
    }
    
    public List<DTSalidaTuristica> obtenerSalidasDeTurista(String nickname) {
    	EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

  		Query query = em.createQuery("SELECT i.salidaJPA FROM InscripcionJPA i WHERE i.turistaJPA.nickname = ?1");
  		List<SalidaJPA> result;
  		try {
  			result = query.setParameter(1, nickname).getResultList();
  		} catch (NoResultException e) {
  			result = new ArrayList<SalidaJPA>();
  		} 
        em.getTransaction().commit();
        em.close();
        
        var salida = new ArrayList<DTSalidaTuristica>();
        
        result.forEach((SalidaJPA sal) -> salida.add(sal.obtenerDTSalidaTuristica()));
        
        return salida;
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
