package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
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
					SELECT a FROM Ansatt as a
					""";
			
			TypedQuery<Ansatt> query = em.createQuery(jpqlQuery, Ansatt.class);
			
			return query.getResultList();
			
		} finally {
			em.close();
		}
		
	}
	
	public void oppdaterLonn() {
		
	}
	
	public boolean leggTilAnsatt() {
		
		return false;
	}

}
