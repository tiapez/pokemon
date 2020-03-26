package com.ale.collezione.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Carta_Utente implements Serializable{
	
	private static final long serialVersionUID = 5025812397121831098L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String utente;
	@Column
	private String carta;
	@Column
	private String lingua;
	@Column
	private String condizione;
	@Column
	private int qta;
	
	public int getQta() {
		return qta;
	}
	public void setQta(int qta) {
		this.qta = qta;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public String getCarta() {
		return carta;
	}
	public void setCarta(String carta) {
		this.carta = carta;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public String getCondizione() {
		return condizione;
	}
	public void setCondizione(String condizione) {
		this.condizione = condizione;
	}
	@Override
	public String toString() {
		return "Carta_Utente [id=" + id + ", utente=" + utente + ", carta=" + carta + ", lingua=" + lingua
				+ ", condizione=" + condizione + ", qta=" + qta + "]";
	}
	
	
	
	
}
