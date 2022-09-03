package com.catalogo.service;

import java.util.List;

import com.catalogo.model.Categoria;

public interface CategoriaService {

	List<Categoria> getAll();
	Categoria getById(Long id);
	Categoria create(Categoria categoria);
	Categoria update(Long id, Categoria categoria);
	void delete(Long id);
	
}
