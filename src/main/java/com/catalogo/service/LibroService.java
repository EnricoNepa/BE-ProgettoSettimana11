package com.catalogo.service;

import java.util.List;
import java.util.Set;

import com.catalogo.model.Autore;
import com.catalogo.model.Categoria;
import com.catalogo.model.Libro;

public interface LibroService {

	List<Libro> getAll();
	Set<Libro> getByAutori(Set<Autore> autori);
	Set<Libro> getByCategoria(Set<Categoria> categorie);
	Libro getById(Long id);
	Libro create(Libro libro);
	Libro update(Long id, Libro libro);
	void delete(Long id);
	
}
