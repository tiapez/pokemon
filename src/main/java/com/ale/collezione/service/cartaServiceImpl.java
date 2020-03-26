package com.ale.collezione.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ale.collezione.model.Carta;
import com.ale.collezione.repository.CartaRepository;
import com.ale.collezione.utility.Filtro;


@Service("cartaService")
public class cartaServiceImpl implements CartaService {
	@Autowired
	private CartaRepository cr;
	
	@Override
	public List<Carta> findBySet(String set) {
		// TODO Auto-generated method stub
		return cr.findBySet(set);
	}

	@Override
	public List<Carta> cardFiltered(String set, Filtro filtro) {
		// TODO Auto-generated method stub
		return cr.cardFiltered(set, filtro.getRarita());
	}
	
	@Override
	public String raritaCarta(String id) {
		// TODO Auto-generated method stub
		return cr.raritaCarta(id);
	}
	


/*	@Autowired
	private StatRepository sr;

	@Override
	public List<Contatto> findByUsername(String username) {
		return sr.findByUsername(username);
	}

	@Override
	public Long conteggioContatti() {
		return sr.conteggioContatti();
	}*/

}
