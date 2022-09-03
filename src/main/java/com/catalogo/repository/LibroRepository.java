package com.catalogo.repository;

import java.util.Set;

import com.catalogo.model.Autore;
import com.catalogo.model.Categoria;
import com.catalogo.model.Libro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

	Set<Libro> findByAutoriIn(Set<Autore> autori);
	
	Set<Libro> findByCategorieIn(Set<Categoria> categorie);
}
