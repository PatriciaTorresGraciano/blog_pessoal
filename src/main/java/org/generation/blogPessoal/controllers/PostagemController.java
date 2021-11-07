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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("blog/postagens")
@Api(tags = "Controlador de Postagem", description = "Utilitário de Postagens")
@CrossOrigin("*")

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@ApiOperation(value = "Busca lista de postagens no sistema")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Retorna lista de postagens")})
	@GetMapping
	public ResponseEntity<List<PostagemModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@ApiOperation(value = "Busca postagem por Id")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Retorna postagem existente"),
	@ApiResponse(code = 204, message = "Retorno inexistente")})
	@GetMapping("{id}")
	public ResponseEntity<Optional<PostagemModel>> getById(@PathVariable long id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@ApiOperation(value = "Busca postagem por título")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Retorna postagem existente"),
	@ApiResponse(code = 204, message = "Retorno inexistente")})
	@GetMapping("{titulo}")
	public ResponseEntity<PostagemModel> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findByTitulo(titulo));
	}
	
	@ApiOperation(value = "Salva nova postagem no sistema")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Retorna postagem cadastrada")})
	@PostMapping("/salvar")
	public ResponseEntity<PostagemModel> post (@Valid @RequestBody PostagemModel novaPostagem){
		return ResponseEntity.status(201).body(repository.save(novaPostagem));
	}
	
	@ApiOperation(value = "Atualizar postagem existente")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Retorna postagem atualizada")})
	@PutMapping
	public ResponseEntity<PostagemModel> put (@Valid @RequestBody PostagemModel novaPostagem){
		return ResponseEntity.status(201).body(repository.save(novaPostagem));
	}

	@ApiOperation(value = "Deletar postagem existente")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Caso deletada!"),
	@ApiResponse(code = 400, message = "Id de postagem invalida")})
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
