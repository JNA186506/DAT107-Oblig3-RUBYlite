package no.hvl.dat107;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
	
	//@OneToMany(mappedBy = "Ansatt", fetch = FetchType.EAGER)
	//private List<Ansatt> Ansatte;
	
	public Ansatt() {}
	public Ansatt(String brukernavn, String fornavn, String etternavn, Date ansettelsesdato, String stilling, BigDecimal maanedslonn) {
		
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesdato = ansettelsesdato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		
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

	public void skrivUt() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Id: " + aid + "\n");
		sb.append("Brukernavn: " + brukernavn + "\n");
		sb.append("Fornavn: " + fornavn + "\n");
		sb.append("Etternavn: " + etternavn + "\n");
		sb.append("Dato for ansettelse: " + ansettelsesdato + "\n");
		sb.append("Stilling: " + stilling + "\n");
		sb.append("Månedslønn: " + maanedslonn + "\n");
		
		System.out.println(sb.toString());
		
	}
	/*
	public List<Ansatt> getAnsatt() {
		return Ansatte;
	}
	*/
}
