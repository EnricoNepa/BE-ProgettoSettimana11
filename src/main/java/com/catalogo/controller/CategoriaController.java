package com.catalogo.controller;

import java.util.List;

import com.catalogo.model.Categoria;
import com.catalogo.service.CategoriaService;

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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categoria")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "Categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(categoriaService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Categoria> getById(@PathVariable long id){
		return ResponseEntity.ok(categoriaService.getById(id));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Categoria> create(@RequestBody Categoria categoria){
		return new ResponseEntity<Categoria>(categoriaService.create(categoria), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Categoria> update(@PathVariable long id, @RequestBody Categoria categoria){
		return ResponseEntity.ok(categoriaService.update(id, categoria));
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable long id){
		categoriaService.delete(id);
		return ResponseEntity.ok("Categoria eliminata correttamente");
	}
}
