package com.catalogo.service;

import java.util.List;
import java.util.Optional;

import javax.xml.catalog.CatalogException;

import com.catalogo.exception.CatalogoException;
import com.catalogo.model.Autore;
import com.catalogo.repository.AutoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoreServiceImpl implements AutoreService {

	@Autowired
	private AutoreRepository autoreRepository;

	@Override
	public List<Autore> getAll() {

		return autoreRepository.findAll();
	}

	@Override
	public Autore getById(Long id) {
		Optional<Autore> autore = autoreRepository.findById(id);
		if (autore.isEmpty()) {
			throw new CatalogoException("Autore inesistente!");
		}
		return autore.get();
	}

	@Override
	public Autore create(Autore autore) {
		return autoreRepository.save(autore);
	}

	@Override
	public Autore update(Long id, Autore autore) {

		if (!autoreRepository.existsById(id)) {
			throw new CatalogoException("Autore inesistente!");
		}
		autore.setId(id);

		return autoreRepository.save(autore);
	}

	@Override
	public void delete(Long id) {
		if (!autoreRepository.existsById(id)) {
			throw new CatalogoException("Autore inesistente!");
		}
		autoreRepository.deleteById(id);
	}

}
