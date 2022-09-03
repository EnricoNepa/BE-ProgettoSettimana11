package com.catalogo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.xml.catalog.CatalogException;

import com.catalogo.exception.CatalogoException;
import com.catalogo.model.Autore;
import com.catalogo.model.Categoria;
import com.catalogo.model.Libro;
import com.catalogo.repository.AutoreRepository;
import com.catalogo.repository.CategoriaRepository;
import com.catalogo.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements LibroService{

	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private AutoreRepository autoreRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@Override
	public List<Libro> getAll() {
		
		return libroRepository.findAll();
	}

	@Override
	public Libro getById(Long id) {
		Optional<Libro> libro = libroRepository.findById(id);
		if(libro.isEmpty()) {
			throw new CatalogoException("Libro inesistente!");
		}
		return libro.get();
	}

	@Override
	public Libro create(Libro libro) {
		List<Autore> autori = libro.getAutori();
		List<Categoria> categorie = libro.getCategorie();
		List<Autore> autoriDB =  new ArrayList<>();
		List<Categoria> categorieDB = new ArrayList<>();
		for(Autore autore : autori) {
			if(!autoreRepository.existsByNomeAndCognome(autore.getNome(), autore.getCognome())) {
				throw new CatalogoException("Autore inesistente!");
			}else {
				autoriDB.add(autoreRepository.getByNomeAndCognome(autore.getNome(), autore.getCognome()));
			}
		}
		for(Categoria categoria : categorie) {
			if(!categoriaRepository.existsByNome(categoria.getNome())) {
				throw new CatalogoException("Categoria inesistente!");
			}else {
				categorieDB.add(categoriaRepository.getByNome(categoria.getNome()));
			}
		}
		libro.setAutori(autoriDB);
		libro.setCategorie(categorieDB);
		return libroRepository.save(libro);
	}

	@Override
	public Libro update(Long id, Libro libro) {
		
		if(!libroRepository.existsById(id)) {
			throw new CatalogoException("Libro inesistente!");
		}
		libro.setId(id);
		
		return libroRepository.save(libro);
	}

	@Override
	public void delete(Long id) {
		if(!libroRepository.existsById(id)) {
			throw new CatalogoException("Libro inesistente!");
		}
		libroRepository.deleteById(id);
	}

	@Override
	public Set<Libro> getByAutori(Set<Autore> autori) {
		Set<Autore> autoriDB =  new HashSet<>();
		for(Autore autore : autori) {
			if(!autoreRepository.existsByNomeAndCognome(autore.getNome(), autore.getCognome())) {
				throw new CatalogoException("Autori inesistenti!");
			}else {
				autoriDB.add(autoreRepository.getByNomeAndCognome(autore.getNome(), autore.getCognome()));
			}
		}
		return libroRepository.findByAutoriIn(autoriDB);
	}

	@Override
	public Set<Libro> getByCategoria(Set<Categoria> categorie) {
		Set<Categoria> categoriaDB =  new HashSet<>();
		for(Categoria categoria : categorie) {
			if(!categoriaRepository.existsByNome(categoria.getNome())) {
				throw new CatalogoException("Categorie inesistenti!");
			}else {
				categoriaDB.add(categoriaRepository.getByNome(categoria.getNome()));
			}
		}
		return libroRepository.findByCategorieIn(categoriaDB);
	}

}
