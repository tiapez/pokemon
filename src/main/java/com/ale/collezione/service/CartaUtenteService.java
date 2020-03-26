package com.ale.collezione.service;

import java.util.List;

import com.ale.collezione.model.Carta_Utente;
import com.ale.collezione.utility.Filtro;


public interface CartaUtenteService {
	
	void Save(Carta_Utente card);
	
	public List<Carta_Utente> findBySetUser(String set, String user);
	public List<Carta_Utente> findBySetUserFiltered(String set,String user,Filtro filtro);
	public Carta_Utente findByCartaUser(String carta,String user,String lingua, String cond);
	

}
