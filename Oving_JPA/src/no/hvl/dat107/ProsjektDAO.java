package no.hvl.dat107;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProsjektDAO {
    private static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
    }

    public boolean leggTilProsjekt(String navn) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Prosjekt nyP = new Prosjekt(navn);

            Prosjekt eksisterendeProsjekt = em.find(Prosjekt.class, nyP.getProsjektid());
            if (eksisterendeProsjekt != null) {
                return false;
            }

            em.merge(nyP);
            em.persist(nyP);

            tx.commit();
            return true;
        } finally {
            em.close();
        }
    }

    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            Prosjekt p = em.find(Prosjekt.class, id);


            return null;
        } finally {
            em.close();
        }
    }

}
