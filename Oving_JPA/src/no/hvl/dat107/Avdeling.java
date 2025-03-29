package no.hvl.dat107;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(schema = "oblig3")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdelingsid;
    private String navn;
    private int leder;

    public Avdeling() {}
    public Avdeling(String navn, int leder) {
        this.navn = navn;
        this.leder = leder;
    }

    @OneToMany(mappedBy = "avdeling", fetch = FetchType.LAZY)
    private List<Ansatt> ansatte;

    public int getAvdelingsid() {
        return avdelingsid;
    }

    public void setAvdelingsid(int avdelingsid) {
        this.avdelingsid = avdelingsid;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getLeder() {
        return leder;
    }

    public void setSjef(int leder) {
        this.leder = leder;
    }

    public String ansatteToString() {
        return ansatte.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        return  "Avdeling{" +
                "avdelignsid=" + avdelingsid +
                ", navn='" + navn + '\'' +
                ", sjef='" + leder + '\'' +
                ", ansatte=" + ansatteToString() +
                '}';
    }


    //Dette er på den måte et svar på iterasjon 4, "... utheving av hvem som er sjef"
	public void skrivUt() {

		AnsattDAO a = new AnsattDAO();

		StringBuilder sb = new StringBuilder();
		sb.append("Id: " + avdelingsid + "\n");
		sb.append("Navn: " + navn + "\n");
		sb.append("Leder: " + a.finnAnsattId(leder).getBrukernavn() + "\n");

		System.out.println(sb.toString());

	}

}
