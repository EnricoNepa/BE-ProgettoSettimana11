package com.catalogo.repository;

import com.catalogo.model.Autore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoreRepository extends JpaRepository<Autore, Long>{

	boolean existsByNomeAndCognome(String nome, String cognome);
	
	Autore getByNomeAndCognome(String nome, String cognome);

}
