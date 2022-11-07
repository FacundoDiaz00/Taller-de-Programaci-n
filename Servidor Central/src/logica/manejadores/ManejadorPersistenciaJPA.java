package logica.manejadores;

import java.util.ArrayList;
import java.util.List;

import excepciones.ObjetoNoExisteEnTurismoUy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.datatypes.DTActividadTuristicaDetalle;
import logica.datatypes.DTInscripcion;
import logica.entidades.ActividadTuristica;
import logica.jpa.ActividadJPA;

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
        ActividadJPA actJPA = act.obtenerActividadJPA();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(actJPA);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void persistirModificacionUsuario(String usuario) {
        // TODO actualizar los datos del usuario si ya está en la base
    }

    public List<DTActividadTuristicaDetalle> obtenerActividadesFinalizadasDeProveedor(String nickname) {
        // TODO buscar por nick en la base y devolver todas las actividades asociadas
        return new ArrayList<DTActividadTuristicaDetalle>();
    }

    public List<DTInscripcion> obtenerInscripcionesDeTurista(String nickname) {
        // TODO buscar por nick en la base y devolver todas las inscripciones asociadas
        return new ArrayList<DTInscripcion>();
    }

    public List<String> obtenerIdActividadesFinalizadas() {
        // TODO
        return new ArrayList<String>();
    }
}
