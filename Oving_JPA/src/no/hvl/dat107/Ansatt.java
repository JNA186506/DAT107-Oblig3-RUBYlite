package no.hvl.dat107;

import java.math.BigDecimal;
import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table(schema = "oblig3")

public class Ansatt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aid;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private Date ansettelsesdato;
	private String stilling;
	private BigDecimal maanedslonn;

	@ManyToOne
	@JoinColumn(name = "avdelingsid")
	private Avdeling avdeling;
	
	public Ansatt() {}
	public Ansatt(String brukernavn, String fornavn, String etternavn, Date ansettelsesdato, String stilling, BigDecimal maanedslonn, Avdeling avdeling) {
		
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesdato = ansettelsesdato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		this.avdeling = avdeling;
		
	}

	public int getAid() {
		return aid;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public Date getAnsettelsesdato() {
		return ansettelsesdato;
	}

	public void setAnsettelsesdato(Date ansettelsesdato) {
		this.ansettelsesdato = ansettelsesdato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public BigDecimal getMaanedslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(BigDecimal maanedslonn) {
		this.maanedslonn = maanedslonn;
	}

	public Avdeling getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	@Override
	public String toString() {
		return "Ansatt{" +
				"aid=" + aid +
				", brukernavn='" + brukernavn + '\'' +
				", fornavn='" + fornavn + '\'' +
				", etternavn='" + etternavn + '\'' +
				", ansettelsesdato=" + ansettelsesdato +
				", stilling='" + stilling + '\'' +
				", maanedslonn=" + maanedslonn +
				", avdelingsid=" + (avdeling != null ? avdeling.getNavn() : "NULL") +  // Unngå full rekursjon
				'}' + "\n";
	}

	public void skrivUt() {

		StringBuilder sb = new StringBuilder();
		sb.append("Id: " + aid + "\n");
		sb.append("Brukernavn: " + brukernavn + "\n");
		sb.append("Fornavn: " + fornavn + "\n");
		sb.append("Etternavn: " + etternavn + "\n");
		sb.append("Dato for ansettelse: " + ansettelsesdato + "\n");
		sb.append("Stilling: " + stilling + "\n");
		sb.append("Månedslønn: " + maanedslonn + "\n");
		sb.append("Avdeling: " + avdeling.getNavn() + "\n");

		System.out.println(sb.toString());

	}
	
}
