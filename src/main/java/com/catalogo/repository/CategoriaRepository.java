package com.catalogo.repository;

import com.catalogo.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	boolean existsByNome(String nome);
	Categoria getByNome(String nome);
}
