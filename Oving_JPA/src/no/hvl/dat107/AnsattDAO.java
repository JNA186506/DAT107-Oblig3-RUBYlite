package no.hvl.dat107;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class AnsattDAO extends Ansatt {
	
	private static EntityManagerFactory emf;
	static {
		
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
		
	}
	
	public Ansatt finnAnsattId(Integer id) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			
			return em.find(Ansatt.class, id);
			
		}
		
		catch(Exception e) {
			
			System.out.println("Søk gav ingen resultater");
			return null;
			
		}
		
	}
	
	public Ansatt finnAnsattBrukernavn(String brukernavn) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			
			String queryString = "SELECT a.aid FROM Ansatt a WHERE UPPER(a.brukernavn) LIKE UPPER(:value)";
			Integer resultat = -1;
			
			try {
				
				resultat = em.createQuery(queryString, Integer.class).setParameter("value", brukernavn).getSingleResult();
				
			}
			
			catch(Exception e) {
				
				System.out.println("Søk gav ingen resultater");
				return null;
				
			}
			
			return em.find(Ansatt.class, resultat);
			
		}
		
		finally {
			
			em.close();
			
		}
		
	}
	
	public static List<Ansatt> ansattListe() {
		EntityManager em = emf.createEntityManager();

		try {
			String jpqlQuery = """ 
					SELECT a FROM Ansatt AS a
					""";
			TypedQuery<Ansatt> query = em.createQuery(jpqlQuery, Ansatt.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}
	
	public void oppdaterAnsatt(int id, String stilling, BigDecimal maanedslonn) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Ansatt a = em.find(Ansatt.class, id);
			a.setMaanedslonn(maanedslonn);
			a.setStilling(stilling);

			em.merge(a);

			tx.commit();
		} finally {
			em.close();
		}

	}
	
	public boolean leggTilAnsatt(int aid, String brukernavn, String fornavn, String etternavn, String ansettelsesdato, String stilling, BigDecimal maanedslonn) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		try {
			tx.begin();
			
			Ansatt NyA = new Ansatt(aid, brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn);
			
			em.persist(NyA);
			
			tx.commit();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			} 
		}finally {
			em.close();
		}
		
		
		return false;
	}

}
