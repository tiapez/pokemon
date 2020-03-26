package com.ale.collezione.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Carta implements Serializable{
	
	private static final long serialVersionUID = 5025812397121831098L;
	
	@Id
	private String id;
	@Column
	private String nome;
	@Column
	private String rarita;
	@Column
	private String fuoriSerie;
	@Column
	private String tipo;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRarita() {
		return rarita;
	}
	public void setRarità(String rarità) {
		this.rarita = rarità;
	}
	public String getFuoriSerie() {
		return fuoriSerie;
	}
	public void setFuoriSerie(String fuoriSerie) {
		this.fuoriSerie = fuoriSerie;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Carta [id=" + id + ", nome=" + nome + ", rarità=" + rarita + ", fuoriSerie=" + fuoriSerie + ", tipo="
				+ tipo + "]";
	}
	
	
	
	
	
}
