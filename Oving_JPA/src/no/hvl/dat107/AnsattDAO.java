package no.hvl.dat107;

import java.math.BigDecimal;
import java.sql.Date;
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
	
	public List<Ansatt> ansattListe() {
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
			if(!stilling.trim().isEmpty()) {
				
				a.setStilling(stilling);
				System.out.println("Ny stilling er satt");
				
			}
			else System.out.println("Ingen ny stilling");
			if(maanedslonn.compareTo(BigDecimal.ZERO) >= 0) {
				
				a.setMaanedslonn(maanedslonn);
				System.out.println("Ny månedslønn er satt");
				
			}
			else System.out.println("Ingen ny månedslønn");

			em.merge(a);

			tx.commit();
		} finally {
			em.close();
		}

	}
	
	public boolean leggTilAnsatt(String brukernavn, String fornavn, String etternavn, Date ansettelsesdato, String stilling, BigDecimal maanedslonn, Avdeling avdelings) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt nyA = new Ansatt(brukernavn, fornavn, etternavn, ansettelsesdato, stilling, maanedslonn, avdelings);

			em.persist(nyA);

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
