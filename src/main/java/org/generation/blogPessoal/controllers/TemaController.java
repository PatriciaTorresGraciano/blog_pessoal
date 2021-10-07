package org.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.models.PostagemModel;
import org.generation.blogPessoal.models.TemaModel;
import org.generation.blogPessoal.repositories.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("blog/temas")
@CrossOrigin("*")

public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<TemaModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<TemaModel>> getById(@PathVariable long id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@GetMapping("/{id_tema}")
	public ResponseEntity<List<TemaModel>> getByTitulo(@PathVariable String id_tema){
		return ResponseEntity.ok(repository.findAllByTemaContainingIgnoreCase(id_tema));
	}
	
	@PostMapping
	public ResponseEntity<TemaModel> post (@Valid @RequestBody TemaModel novaPostagem){
		return ResponseEntity.status(201).body(repository.save(novaPostagem));
	}
	
	@PutMapping
	public ResponseEntity<TemaModel> put (@Valid @RequestBody TemaModel tema){
		return ResponseEntity.status(201).body(repository.save(tema));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
