package com.catalogo.controller;

import java.util.List;
import java.util.Set;

import com.catalogo.model.Autore;
import com.catalogo.model.Categoria;
import com.catalogo.model.Libro;
import com.catalogo.model.LibroDTO;
import com.catalogo.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/libro/")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "Libro")
public class LibroController {

	@Autowired
	private LibroService libroService;
	
	@GetMapping
	@Operation(summary = "Lista di tutti i Libri")
	public ResponseEntity<List<Libro>> getAll(){
		return ResponseEntity.ok(libroService.getAll());
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Libro per ID")
	public ResponseEntity<Libro> getById(@PathVariable long id){
		return ResponseEntity.ok(libroService.getById(id));
	}
	
	@PostMapping("autori/")
	@Operation(summary = "Libro per Autori")
	public ResponseEntity<Set<Libro>> getByAutori(@RequestBody Set<Autore> autori){
		return ResponseEntity.ok(libroService.getByAutori(autori));
	}
	
	@PostMapping("categorie/")
	@Operation(summary = "Libro per Categorie")
	public ResponseEntity<Set<Libro>> getByCategoria(@RequestBody Set<Categoria> categorie){
		return ResponseEntity.ok(libroService.getByCategoria(categorie));
	}
	
	@PostMapping
	@Operation(summary = "Aggiungi un nuovo Libro")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> create(@RequestBody Libro libro){
		return new ResponseEntity<Libro>(libroService.create(libro), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Aggiorna un Libro esistente")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Libro> update(@PathVariable long id, @RequestBody Libro libro){
		return ResponseEntity.ok(libroService.update(id, libro));
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Elimina un Libro")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable long id){
		libroService.delete(id);
		return ResponseEntity.ok("Libro eliminato correttamente");
	}
}
