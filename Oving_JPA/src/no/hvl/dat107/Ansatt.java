package no.hvl.dat107;

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
	private Integer maanedslonn;

	
	public Ansatt() {}
	public Ansatt(int aid, String brukernavn, String fornavn, String etternavn, String ansettelsesdato, String stilling, Integer maanedslonn) {
		
		this.aid = aid;
		this.brukernavn = brukernavn;
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.ansettelsesdato = ansettelsesdato;
		this.stilling = stilling;
		this.maanedslonn = maanedslonn;
		
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
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

	public String getAnsettelsesdato() {
		return ansettelsesdato;
	}

	public void setAnsettelsesdato(String ansettelsesdato) {
		this.ansettelsesdato = ansettelsesdato;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public Integer getMaanedslonn() {
		return maanedslonn;
	}

	public void setMaanedslonn(Integer maanedslonn) {
		this.maanedslonn = maanedslonn;
	}

	public void skrivUt() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(aid + ", ");
		sb.append(brukernavn + ", ");
		sb.append(fornavn + ", ");
		sb.append(etternavn + ", ");
		sb.append(ansettelsesdato + ", ");
		sb.append(stilling + ", ");
		sb.append(maanedslonn);
		
		System.out.println(sb.toString());
		
	}
}
