package no.hvl.dat107;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "oblig3")
public class Avdeling {

    @Id
    private int avdelignsid;
    private String navn;
    private String sjef;

    public Avdeling() {}
    public Avdeling(int avdelignsid, String navn, String sjef) {
        this.avdelignsid = avdelignsid;
        this.navn = navn;
        this.sjef = sjef;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getSjef() {
        return sjef;
    }

    public void setSjef(String sjef) {
        this.sjef = sjef;
    }

    @Override
    public String toString() {
        return "Avdeling{" +
                "avdelignsid=" + avdelignsid +
                ", navn='" + navn + '\'' +
                ", sjef='" + sjef + '\'' +
                '}';
    }
}
