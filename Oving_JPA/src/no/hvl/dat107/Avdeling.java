package no.hvl.dat107;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;

import java.util.List;

@Entity
@Table(schema = "oblig3")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdelignsid;
    private String navn;
    private String leder;

    public Avdeling() {}
    public Avdeling(int avdelignsid, String navn, String leder) {
        this.avdelignsid = avdelignsid;
        this.navn = navn;
        this.leder = leder;
    }

    @OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
    private List<HorerTilAvdeling> ansatte;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getLeder() {
        return leder;
    }

    public void setSjef(String leder) {
        this.leder = leder;
    }

    @Override
    public String toString() {
        return "Avdeling{" +
                "avdelignsid=" + avdelignsid +
                ", navn='" + navn + '\'' +
                ", sjef='" + leder + '\'' +
                ", ansatte=" + ansatte +
                '}';
    }
}
