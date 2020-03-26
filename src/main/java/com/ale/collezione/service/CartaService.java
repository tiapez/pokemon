package com.ale.collezione.service;

import java.util.List;

import com.ale.collezione.model.Carta;
import com.ale.collezione.utility.Filtro;


public interface CartaService {
	
	List<Carta> findBySet(String username);
	List<Carta> cardFiltered(String set,Filtro filtro);
	String raritaCarta(String id);

}
