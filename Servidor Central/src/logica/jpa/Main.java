package logica.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		ActividadJPA act = new ActividadJPA();
        act.setNombre("aba");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(act);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
	}

}
