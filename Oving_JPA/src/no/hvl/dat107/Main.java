package no.hvl.dat107;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

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

		AnsattDAO a = new AnsattDAO();
		
		Class.forName(JDBC_DRIVER);
		
		Scanner s = new Scanner(System.in);
		boolean done = false;
		String action = "";
		
		while(!done) {
			
			System.out.println("\ndone -> avslutter programmet\nid -> søk etter id\nbrukernavn -> søk etter brukernavn\nliste -> skriv ut liste av ansatte");
			
			action = s.nextLine();
			
			Ansatt ansatt = null;
			Integer id = null;
			
			switch(action) {
			case "done":
				done = true;
				break;
			case "id":
				System.out.println("Skriv inn id: ");
				id = s.nextInt();
				s.nextLine();
				ansatt = a.finnAnsattId(id);
				if(ansatt != null) ansatt.skrivUt();
				break;
			case "brukernavn":
				System.out.println("Skriv inn brukernavn: ");
				String brukernavn = s.nextLine();
				ansatt = a.finnAnsattBrukernavn(brukernavn);
				if(ansatt != null) ansatt.skrivUt();
				break;
			case "liste":
				for(Ansatt temp : a.ansattListe()) temp.skrivUt();
				break;
			case "endre":
				System.out.println("Skriv inn ansattid: ");
				id = s.nextInt();
				s.nextLine();
				a.oppdaterAnsatt(id, null, BigDecimal.valueOf(60.00));
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
	
}
