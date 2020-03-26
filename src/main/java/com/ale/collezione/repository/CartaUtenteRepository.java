package com.ale.collezione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ale.collezione.model.Carta;
import com.ale.collezione.model.Carta_Utente;
import com.ale.collezione.utility.Filtro;

public interface CartaUtenteRepository extends  JpaRepository<Carta_Utente, Integer>{
	
	@Query(value="Select * from carta where id like ?1",nativeQuery = true)
	List<Carta> findBySet(String set);
	
	@Query(value="Select * from carta_utente where carta like ?1 and utente like ?2  order by carta,condizione,lingua",nativeQuery = true)
	List<Carta_Utente> findBySetUser(String set,String user);
	
	@Query(value="Select * from carta_utente where carta like ?1 and utente like ?2 and lingua like ?3 and condizione like ?4 and "
			+ "carta in (select id from carta where rarita like ?5) order by carta,condizione,lingua",nativeQuery = true)
	List<Carta_Utente> findBySetUserFiltered(String set,String user,String lingua,String cond,String rarita);
	
	@Query(value="Select * from carta_utente where carta like ?1 and utente like ?2 and lingua like ?3 and condizione like ?4",nativeQuery = true)
	Carta_Utente findByCartaUser(String carta,String user,String lingua,String cond);
	
}
