package no.hvl.dat107;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AvdelingDAO {
    private static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }

    public Avdeling finnAvdelingMedId(int avdelingsid) {
        EntityManager em = emf.createEntityManager();

        Avdeling a = null;

        try {
            a = em.find(Avdeling.class, avdelingsid);
        }   finally {
            em.close();
        }
        return a;
    }

}
