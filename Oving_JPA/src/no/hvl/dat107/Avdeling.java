package no.hvl.dat107;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;

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
    public Avdeling(int avdelingsid, String navn, int leder) {
        this.avdelingsid = avdelingsid;
        this.navn = navn;
        this.leder = leder;
    }

    @OneToMany(mappedBy = "avdeling", fetch = FetchType.LAZY)
    private List<Ansatt> ansatte;


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
}
