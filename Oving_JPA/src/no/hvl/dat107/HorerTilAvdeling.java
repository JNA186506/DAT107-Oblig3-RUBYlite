package no.hvl.dat107;

import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3")

public class HorerTilAvdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int avdelingid;

    @ManyToOne
    @JoinColumn(name = "ansattid", nullable = false)
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "avdelingid", insertable = false, updatable = false)
    private Avdeling avdeling;

}
