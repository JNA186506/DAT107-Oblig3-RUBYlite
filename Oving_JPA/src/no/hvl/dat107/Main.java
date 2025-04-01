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
		AnsattDAO a = new AnsattDAO();
		ProsjektDAO p = new ProsjektDAO();
		
		Class.forName(JDBC_DRIVER);
		
		Scanner s = new Scanner(System.in).useLocale(Locale.US);
		boolean done = false;
		String action = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

		while(!done) {
			
			System.out.println("done -> avslutter programmet\nid ansatt -> søk etter id\nbrukernavn ansatt -> søk etter brukernavn\nansatt liste -> skriv ut liste av ansatte\nendre -> endre stilling og/eller månedslønn for en ansatt\nlegg til ansatt -> legg til ansatt\navdeling id -> finn avdeling med id\nopprett avdeling -> opprett ny avdeling\noppdater avdeling -> flytt ansatt til en annen avdeling\nnytt prosjekt -> opprett nytt prosjekt\nfinn prosjekt -> finn prosjekt med id");
			
			action = s.nextLine().toLowerCase();
			
			Ansatt ansatt = null;
			Integer id = null;
			Integer pId = null;
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
			case "id ansatt":
				System.out.println("Skriv inn id: ");
				id = s.nextInt();
				s.nextLine();
				ansatt = a.finnAnsattId(id);
				if(ansatt != null) ansatt.skrivUt();
				break;
			case "brukernavn ansatt":
				System.out.println("Skriv inn brukernavn: ");
				brukernavn = s.nextLine();
				ansatt = a.finnAnsattBrukernavn(brukernavn);
				if(ansatt != null) ansatt.skrivUt();
				break;
			case "ansatt liste":
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
			case "legg til ansatt":
				System.out.println("Skriv inn følgende, separat: brukernavn (maks 4 tegn), fornavn, etternavn");
				brukernavn = s.nextLine();
				fornavn = s.nextLine();
				etternavn = s.nextLine();
				System.out.println("Skriv inn følgende, separat: dato (dd-MM-yyyy), stilling, lønn (xyz.abc), id til avdelingen de hører til");
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
				id = s.nextInt();
				s.nextLine();
				a.leggTilAnsatt(brukernavn, fornavn, etternavn, dato, stilling, BigDecimal.valueOf(lonn), av.finnAvdelingMedId(id));
				break;
			case "avdeling id":
				System.out.println("Skriv inn avdelingsid: ");
				avdelingsid = s.nextInt();
				s.nextLine();
				av.finnAvdelingMedId(avdelingsid).skrivUt();
				break;
			case "opprett avdeling":
				System.out.println("Skriv inn navn på ny avdeling og id til leder, separat: ");
				String navn = s.nextLine();
				id = s.nextInt();
				s.nextLine();
				ansatt = a.finnAnsattId(id);
				if(av.leggTilAvdeling(navn, ansatt)) System.out.println("Avdeling lagt til\n");
				else System.out.println("Ugyldig input\n");
				break;
			case "oppdater avdeling":
				System.out.println("Skriv inn brukernavn på ansatt som skal overføres og id på avdeling de skal flyttes til, separat: ");
				brukernavn = s.nextLine();
				Avdeling avdeling = av.finnAvdelingMedId(s.nextInt());
				s.nextLine();
				if(av.oppdaterAnsattAvedeling(brukernavn, avdeling)) System.out.println("Ansatt ble flyttet\n");
				else System.out.println("Ugyldig input\n");
				break;
			case "nytt prosjekt":
				System.out.println("Skriv inn navn på nytt prosjekt");
				if(p.leggTilProsjekt(s.nextLine())) System.out.println("Nytt prosjekt opprettet\n");
				else System.out.println("Ugyldig input\n");
				break;
			case "finn prosjekt":
				System.out.println("Skriv inn id til prosjekt");
				pId = s.nextInt();
				s.nextLine();
				p.finnProsjektMedId(pId).skrivUt();
				break;
			default:
				System.out.println("Ugyldig input\n");
				break;
			}
			
		}
		
		System.out.println("Avslutter");
		
		s.close();
		emf.close();
	}
	
}
