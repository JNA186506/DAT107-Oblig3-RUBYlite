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
		
		System.out.println("Starter");
		
		Class.forName(JDBC_DRIVER);
		
		Scanner s = new Scanner(System.in);
		boolean done = false;
		String action = "";
		
		while(!done) {
			
			System.out.println("done -> avslutter programmet\nid -> søk etter id\nbrukernavn -> søk etter brukernavn");
			
			action = s.nextLine();
			
			Ansatt ansatt = null;
			
			switch(action) {
			case "done":
				done = true;
				break;
			case "id":
				System.out.println("Skriv inn id: ");
				Integer id = s.nextInt();
				s.nextLine();
				ansatt = finnAnsattId(id);
				if(ansatt != null) ansatt.skrivUt();
				break;
			case "brukernavn":
				System.out.println("Skriv inn brukernavn: ");
				String brukernavn = s.nextLine();
				ansatt = finnAnsattBrukernavn(brukernavn);
				if(ansatt != null) ansatt.skrivUt();
				break;
			default:
				System.out.println("Ugyldig input");
				break;
			}
			
		}
		
		System.out.println("Avslutter");
		
		s.close();
		emf.close();
		
	}
	
	public static Ansatt finnAnsattId(Integer id) {
		
		EntityManager em = emf.createEntityManager();
		
		try {
			
			return em.find(Ansatt.class, id);
			
		}
		
		catch(Exception e) {
			
			System.out.println("Søk gav ingen resultater");
			return null;
			
		}
		
	}
	
	public static Ansatt finnAnsattBrukernavn(String brukernavn) {
		
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

}
