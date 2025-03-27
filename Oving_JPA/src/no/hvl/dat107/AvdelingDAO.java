package no.hvl.dat107;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AvdelingDAO {
    private static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }

    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            Avdeling a = em.find(Avdeling.class, id);
            return a;
        }   finally {
            em.close();
        }
    }

}
