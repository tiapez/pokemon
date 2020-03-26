package com.ale.collezione.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ale.collezione.model.Carta;
import com.ale.collezione.utility.Filtro;

public interface CartaRepository extends  JpaRepository<Carta, Integer>{
	
	@Query(value="Select * from carta where id like ?1",nativeQuery = true)
	List<Carta> findBySet(String set);
	
	@Query(value="Select * from carta where id like ?1 and rarita like ?2",nativeQuery = true)
	List<Carta> cardFiltered(String set,String rarita);
	
	@Query(value="select rarita from carta where id like ?1",nativeQuery = true)
	String raritaCarta(String id);
	

	
}
