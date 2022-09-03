package com.catalogo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Data
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private long id;
	@Column(nullable = false, unique = true)
	private String titolo;
	@Column(nullable = false)
	private int anno_pubblicazione;
	@Column(nullable = false)
	private double prezzo;
	@ManyToMany
	private List<Autore> autori;
	@ManyToMany
	private List<Categoria> categorie;

}
