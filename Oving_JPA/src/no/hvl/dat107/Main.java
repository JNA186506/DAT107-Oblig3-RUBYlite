package no.hvl.dat107;

import java.util.List;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
	
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	
	private static EntityManagerFactory emf;
	static {
		
		emf = Persistence.createEntityManagerFactory("ansattPersistenceUnit");
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName(JDBC_DRIVER);
		
		Scanner s = new Scanner(System.in);
		boolean done = false;
		String action = "";
		
		while(!done) {
			
			action = s.nextLine();
			
			switch(action) {
			case "done":
				done = true;
				break;
			default:
				finnAnsatt(action).skrivUt();
				break;
			}
			
		}
		
		s.close();
		emf.close();
		
	}
	
	public static Ansatt finnAnsatt(String brukernavn) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			
			List<Integer> resultat = em.createQuery("SELECT a.aid FROM Ansatt a WHERE a.brukernavn LIKE :value").setParameter("value", brukernavn).getResultList();
			
			return em.find(Ansatt.class, resultat.get(0));
			
		}
		
		finally {
			
			em.close();
			
		}
		
	}

}
