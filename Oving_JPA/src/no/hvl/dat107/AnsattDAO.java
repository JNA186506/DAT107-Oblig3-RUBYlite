package no.hvl.dat107;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class AnsattDAO extends Ansatt {
	
	private static EntityManagerFactory emf;
	
	public Ansatt sokAnsattId(int id) {
		
		return null;
		
	}
	
	public Ansatt sokAnsattBrukernavn(String init) {
		
		return null;
	}
	
	public static List<Ansatt> ansattListe() {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			String jpqlQuery = """ 
					SELECT a FROM Ansatt as a
					""";
			
			TypedQuery<Ansatt> query = em.createNamedQuery(jpqlQuery, Ansatt.class);
			
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
