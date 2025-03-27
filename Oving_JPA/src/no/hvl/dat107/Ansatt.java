package no.hvl.dat107;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "oblig3")

public class Ansatt {
	
	@Id
	private int aid;
	private String brukernavn;
	private String fornavn;
	private String etternavn;
	private String ansettelsesdato;
	private String stilling;
	private BigDecimal maanedslonn;
	
	//@OneToMany(mappedBy = "Ansatt", fetch = FetchType.EAGER)
	//private List<Ansatt> Ansatte;
	
	public Ansatt() {}
	public Ansatt(int aid, String brukernavn, String fornavn, String etternavn, String ansettelsesdato, String stilling, BigDecimal maanedslonn) {
		
		this.aid = aid;
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesdato = ansettelsesdato;
		this.stilling = stilling;
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
		sb.append("Månedslønn: " + maanedslonn);
		
		System.out.println(sb.toString());
		
	}
	/*
	public List<Ansatt> getAnsatt() {
		return Ansatte;
	}
	*/
}
