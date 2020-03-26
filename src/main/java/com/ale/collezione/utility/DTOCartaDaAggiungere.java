package com.ale.collezione.utility;

import com.ale.collezione.model.Carta_Utente;

public class DTOCartaDaAggiungere {
	
	String idCarta;
	String cond;
	String lingua;
	String username;
	int qta;
	String nomeCarta;
	String nomeCondizione;
	public String getIdCarta() {
		return idCarta;
	}
	public void setIdCarta(String idCarta) {
		this.idCarta = idCarta;
	}
	public String getCond() {
		return cond;
	}
	public void setCond(String cond) {
		this.cond = cond;
	}
	public String getLingua() {
		return lingua;
	}
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getQta() {
		return qta;
	}
	public void setQta(int qta) {
		this.qta = qta;
	}
	public String getNomeCarta() {
		return nomeCarta;
	}
	public void setNomeCarta(String nomeCarta) {
		this.nomeCarta = nomeCarta;
	}
	public String getNomeCondizione() {
		return nomeCondizione;
	}
	public void setNomeCondizione(String nomeCondizione) {
		this.nomeCondizione = nomeCondizione;
	}
	@Override
	public String toString() {
		return "DTOCartaDaAggiungere [idCarta=" + idCarta + ", cond=" + cond + ", lingua=" + lingua + ", username="
				+ username + ", qta=" + qta + ", nomeCarta=" + nomeCarta + ", nomeCondizione=" + nomeCondizione + "]";
	}
	
	
	public Carta_Utente getCartaUtente()
	{
		Carta_Utente carta = new Carta_Utente();
		carta.setCarta(idCarta);
		carta.setCondizione(cond);
		carta.setLingua(lingua);
		carta.setQta(qta);
		carta.setUtente(username);
	
		return carta;
		
	}
	
	
	

}
