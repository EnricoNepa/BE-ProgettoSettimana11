package com.catalogo.config;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.catalogo.model.Autore;
import com.catalogo.model.Categoria;
import com.catalogo.model.Libro;
import com.catalogo.model.security.Role;
import com.catalogo.model.security.Roles;
import com.catalogo.model.security.User;
import com.catalogo.repository.AutoreRepository;
import com.catalogo.repository.CategoriaRepository;
import com.catalogo.repository.security.RoleRepository;
import com.catalogo.repository.security.UserRepository;
import com.catalogo.service.LibroService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationStartupRunner implements CommandLineRunner {
	
	@Autowired
	private LibroService libroService;
	@Autowired
	private AutoreRepository autorerepository;
	@Autowired
	private CategoriaRepository categoriarepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	
	@Override
	public void run(String... args) throws Exception {
	
	 
//	 initAutore();
//	 initCategoria();
//	 initLibro();
//	 
//	 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
//		
//		Role role = new Role();
//	    Role role2 = new Role();
//	   
//	    role2.setRoleName(Roles.ROLE_USER);
//		role.setRoleName(Roles.ROLE_ADMIN);
//	
//		User user = new User();
//		User user2 = new User();
//		Set<Role> rolesAdmin = new HashSet<>(); 
//		Set<Role> rolesUser = new HashSet<>(); 
//		rolesAdmin.add(role);
//		rolesUser.add(role2);
//		
//		user2.setUserName("user");
//		user2.setPassword(bCrypt.encode("user"));
//		user2.setEmail("user@gmail.com");
//		user2.setRoles(rolesUser);
//		user2.setActive(true);
//		
//		user.setUserName("admin");
//		user.setPassword(bCrypt.encode("admin"));
//		user.setEmail("admin@domain.com");
//		user.setRoles(rolesAdmin);
//		user.setActive(true);
//		
//		
//		roleRepository.save(role2);
//		roleRepository.save(role);
//	
//		userRepository.save(user);
//		userRepository.save(user2);
		
		}

	private Categoria initCategoria() {
		Categoria categoria=new Categoria();
		Categoria categoria2=new Categoria();
		
		categoria.setNome("Fantasy");
		categoria2.setNome("Horror");
		
		categoriarepository.save(categoria);
		categoriarepository.save(categoria2);
		
		log.info("Categoria salvata: {}",categoria.getNome());
		log.info("Categoria salvata: {}",categoria2.getNome());
		
		return categoria;
	}
	
private Autore initAutore() {
	Autore autore=new Autore();
	Autore autore2=new Autore();
	
	autore.setNome("Paolo");
	autore.setCognome("Bianchi");
	
	autore2.setNome("Maria");
	autore2.setCognome("Gialli");
	
	autorerepository.save(autore);
	autorerepository.save(autore2);
	
	log.info("Autore salvato: {}",autore.getNome()+autore.getCognome());
	log.info("Autore salvato: {}",autore2.getNome()+autore2.getCognome());
	
	return autore;
}

	private Libro initLibro() {
		
		List<Categoria> categorie = new ArrayList<>();
		List<Autore> autori = new ArrayList<>();
		
		
		Autore autore1=new Autore();
		Autore autore2=new Autore();
		
		autore1.setCognome("Bianchi");
		autore1.setNome("Paolo");
		
		autore2.setCognome("Gialli");
		autore2.setNome("Maria");
		
		autori.add(autore2);
		autori.add(autore1);
		
		Categoria categoria1=new Categoria();
		Categoria categoria2=new Categoria();
		
		categoria1.setNome("Fantasy");
		categoria2.setNome("Horror");
		
		categorie.add(categoria2);
		categorie.add(categoria1);
		
		Libro libro2 = new Libro();
		libro2.setAutori(autori);
		Libro libro = new Libro();
		libro.setTitolo("Harry Potter");
		libro.setAnno_pubblicazione(2022);
		libro.setPrezzo(9.50);
		libro.setAutori(autori);
		libro.setCategorie(categorie);
		libroService.create(libro);
		log.info("Libro salvato: {}",libro.getTitolo());
		return libro;
	}

}
