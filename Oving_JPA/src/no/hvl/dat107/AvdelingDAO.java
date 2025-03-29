package no.hvl.dat107;

import jakarta.persistence.*;

import java.util.List;

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

    public boolean leggTilAvdeling(String avdelingsnavn, Ansatt leder) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Avdeling nyA = new Avdeling(avdelingsnavn, leder.getAid());

            Ansatt eksisterendeLeder = em.find(Ansatt.class, leder.getAid());
            if (eksisterendeLeder == null || eksisterendeLeder.getAvdeling() != null) {
                return false;
            }

            leder.setAvdeling(nyA);

            em.persist(nyA);
            em.merge(leder);

            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            em.close();
        }


        return false;
    }

    public boolean oppdaterAnsattAvedeling(String brukernavn, Avdeling avdeling) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        AnsattDAO an = new AnsattDAO();
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
            a.setAvdeling(avdeling);

            em.merge(a);
            tx.commit();
        } finally {
            em.close();
        }

        return false;
    }

}
