package com.ale.collezione.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ale.collezione.model.Carta_Utente;
import com.ale.collezione.repository.CartaUtenteRepository;
import com.ale.collezione.utility.Filtro;


@Service("cartaUtenteService")
public class CartaUtenteServiceImpl implements CartaUtenteService {
	@Autowired
	private CartaUtenteRepository cur;

	@Override
	public void Save(Carta_Utente card) {
		cur.save(card);
		
	}

	@Override
	public List<Carta_Utente> findBySetUser(String set, String user) {
		return cur.findBySetUser(set, user);
	}

	@Override
	public List<Carta_Utente> findBySetUserFiltered(String set, String user, Filtro filtro) {
		
		return cur.findBySetUserFiltered(set, user, filtro.getLingua(), filtro.getCondizione(),filtro.getRarita());
	}

	@Override
	public Carta_Utente findByCartaUser(String carta, String user, String lingua, String cond) {
		
		return cur.findByCartaUser(carta, user, lingua, cond);
	}
	


}
