package org.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.models.PostagemModel;
import org.generation.blogPessoal.repositories.PostagemRepository;
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
@RequestMapping("/postagens")
@CrossOrigin("*")

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<PostagemModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<PostagemModel>> getById(@PathVariable long id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@GetMapping("titulos/{titulo}")
	public ResponseEntity<PostagemModel> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findByTitulo(titulo));
	}
	
	@PostMapping
	public ResponseEntity<PostagemModel> post (@Valid @RequestBody PostagemModel novaPostagem){
		return ResponseEntity.status(201).body(repository.save(novaPostagem));
	}
	
	@PutMapping
	public ResponseEntity<PostagemModel> put (@Valid @RequestBody PostagemModel novaPostagem){
		return ResponseEntity.status(201).body(repository.save(novaPostagem));
	}
}
