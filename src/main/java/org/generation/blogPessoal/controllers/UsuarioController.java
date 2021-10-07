package org.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.models.UsuarioModel;
import org.generation.blogPessoal.repositories.UsuarioRepository;
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
@RequestMapping("blog/usuarios")
@CrossOrigin("*")

public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Optional<UsuarioModel>> getById(@PathVariable long id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@GetMapping("{usuario}")
	public ResponseEntity<List<UsuarioModel>> getByTitulo(@PathVariable String nome){
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioModel> post (@Valid @RequestBody UsuarioModel novoUsuario){
		return ResponseEntity.status(201).body(repository.save(novoUsuario));
	}
	
	@PutMapping
	public ResponseEntity<UsuarioModel> put (@Valid @RequestBody UsuarioModel usuario){
		return ResponseEntity.status(201).body(repository.save(usuario));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
