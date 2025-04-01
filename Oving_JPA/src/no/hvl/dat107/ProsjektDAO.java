package no.hvl.dat107;

import jakarta.persistence.*;

import java.util.List;

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
            return p;
        } catch (Exception e) {
            System.out.println("Søket ga ingen resultat");
            return null;
        } finally {
            em.close();
        }
    }

    public boolean registrerDeltakelse(String brukernavn, Prosjekt prosjekt) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            TypedQuery<Ansatt> query = em.createQuery("SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn",
                    Ansatt.class);
            query.setParameter("brukernavn", brukernavn);

            List<Ansatt> result = query.getResultList();
            if (result.isEmpty()) {
                return false;
            }

            Ansatt a = result.get(0);
            a.setProsjekt(prosjekt);

            em.merge(a);
            tx.commit();
            return true;

        } finally {
            em.close();
        }
    }

    public void /*TODO*/ registrerTimer() {
        /* TODO legg til registrer timer
            Trenger timer funksjonalitet */
    }

}
