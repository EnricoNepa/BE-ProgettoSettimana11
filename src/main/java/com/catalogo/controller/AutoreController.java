package com.catalogo.controller;

import java.util.List;

import com.catalogo.model.Autore;
import com.catalogo.service.AutoreService;

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
@RequestMapping("/api/autore")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "Autore")
public class AutoreController {

	@Autowired
	private AutoreService autoreService;
	
	@GetMapping
	public ResponseEntity<List<Autore>> getAll(){
		return ResponseEntity.ok(autoreService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Autore> getById(@PathVariable long id){
		return ResponseEntity.ok(autoreService.getById(id));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Autore> create(@RequestBody Autore autore){
		return new ResponseEntity<Autore>(autoreService.create(autore), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Autore> update(@PathVariable long id, @RequestBody Autore autore){
		return ResponseEntity.ok(autoreService.update(id, autore));
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> delete(@PathVariable long id){
		autoreService.delete(id);
		return ResponseEntity.ok("Autore eliminato correttamente");
	}
}
