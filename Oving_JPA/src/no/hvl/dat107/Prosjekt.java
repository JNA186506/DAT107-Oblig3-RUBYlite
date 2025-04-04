package no.hvl.dat107;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(schema = "oblig3")
public class Prosjekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjektid;
    private String prosjektnavn;

    @ManyToMany(mappedBy = "prosjekt", fetch = FetchType.LAZY)
    private List<Ansatt> ansatte;

    public Prosjekt() {
    }

    public Prosjekt(String prosjektnavn) {
        this.prosjektnavn = prosjektnavn;
    }

    public int getProsjektid() {
        return prosjektid;
    }

    public void setProsjektid(int prosjektid) {
        this.prosjektid = prosjektid;
    }

    public String getProsjektnavn() {
        return prosjektnavn;
    }

    public void setProsjektnavn(String prosjektnavn) {
        this.prosjektnavn = prosjektnavn;
    }

    public String ansatteToString() {
        return ansatte.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    @Override
    public String toString() {
        return "Prosjekt{" +
                "prosjektid=" + prosjektid +
                ", prosjektnavn='" + prosjektnavn + '\'' +
                ", ansatte='" + ansatteToString() +
                '}';
    }
    
    public void skrivUt() {
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("Id: " + prosjektid + "\n");
    	sb.append("Navn: " + prosjektnavn + "\n");
    	sb.append("Ansatte: " + ansatteToString() + "\n");
    	
    	System.out.println(sb.toString());
    	
    }
    
}
