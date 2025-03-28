package no.hvl.dat107;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
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


		AvdelingDAO av = new AvdelingDAO();

		Avdeling i = av.finnAvdelingMedId(1);

		System.out.println(i.toString());

		AnsattDAO a = new AnsattDAO();
		
		Class.forName(JDBC_DRIVER);
		
		Scanner s = new Scanner(System.in).useLocale(Locale.US);
		boolean done = false;
		String action = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

		while(!done) {
			
			System.out.println("done -> avslutter programmet\nid -> søk etter id\nbrukernavn -> søk etter brukernavn\nliste -> skriv ut liste av ansatte\nendre -> endre stilling og/eller månedslønn for en ansatt\nlegg til -> legg til ansatt\navdeling id -> finn avdeling med id");
			
			action = s.nextLine();
			
			Ansatt ansatt = null;
			Integer id = null;
			String brukernavn = "";
			String fornavn = "";
			String etternavn = "";
			String datoStr = "";
			String stilling = "";
			double lonn = 0.0;
			int avdelingsid = 0;

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
				brukernavn = s.nextLine();
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
				System.out.println("Send inn ny stilling (la være tomt for ikke å endre) og deretter ny månedslønn (negativt tall for ikke å endre: )");
				a.oppdaterAnsatt(id, s.nextLine(), BigDecimal.valueOf(s.nextDouble()));
				s.nextLine();
				break;
			case "legg til":
				System.out.println("Skriv inn følgende, separat: brukernavn (maks 4 tegn), fornavn, etternavn");
				brukernavn = s.nextLine();
				fornavn = s.nextLine();
				etternavn = s.nextLine();
				System.out.println("Skriv inn følgende, separat: dato (dd-MM-yyyy), stilling, lønn (xyz.abc)");
				Date dato = null;
				while(dato == null) {
					try {

						datoStr = s.nextLine();
						dato = new java.sql.Date(formatter.parse(datoStr).getTime());

					}

					catch (ParseException e) {

						System.out.println("Ugyldig dato (dd-MM-yyyy)");

					}
				}
				stilling = s.nextLine();
				lonn = s.nextDouble();
				s.nextLine();
				a.leggTilAnsatt(brukernavn, fornavn, etternavn, dato, stilling, BigDecimal.valueOf(lonn));
				break;
			case "avdeling id":
				System.out.println("Skriv inn avdelingsid: ");
				avdelingsid = s.nextInt();
				s.nextLine();
				av.finnAvdelingMedId(avdelingsid).skrivUt();
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
