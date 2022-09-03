package com.catalogo.service;

import java.util.List;
import java.util.Optional;

import com.catalogo.exception.CatalogoException;
import com.catalogo.model.Categoria;
import com.catalogo.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> getAll() {

		return categoriaRepository.findAll();
	}

	@Override
	public Categoria getById(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isEmpty()) {
			throw new CatalogoException("Categoria inesistente!");
		}
		return categoria.get();
	}

	@Override
	public Categoria create(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria update(Long id, Categoria categoria) {

		if (!categoriaRepository.existsById(id)) {
			throw new CatalogoException("Categoria inesistente!");
		}
		categoria.setId(id);

		return categoriaRepository.save(categoria);
	}

	@Override
	public void delete(Long id) {
		if (!categoriaRepository.existsById(id)) {
			throw new CatalogoException("Categoria inesistente!");
		}
		categoriaRepository.deleteById(id);
	}

}
