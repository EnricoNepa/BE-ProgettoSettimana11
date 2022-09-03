package com.catalogo.service;

import java.util.List;

import com.catalogo.model.Autore;

public interface AutoreService {

	List<Autore> getAll();
	Autore getById(Long id);
	Autore create(Autore autore);
	Autore update(Long id, Autore autore);
	void delete(Long id);
}
