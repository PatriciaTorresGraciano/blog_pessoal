package org.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("blog/temas")
@Api(tags = "Controlador de Tema", description = "Utilitário de Temas")
@CrossOrigin("*")

public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@ApiOperation(value = "Busca lista de temas no sistema")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Retorna lista de temas"),
	@ApiResponse(code = 204, message = "Retorno sem tema")})
	@GetMapping
	public ResponseEntity<List<TemaModel>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@ApiOperation(value = "Busca tema por Id")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Retorna tema existente"),
	@ApiResponse(code = 204, message = "Retorno inexistente")})
	@GetMapping("{id}")
	public ResponseEntity<Optional<TemaModel>> getById(@PathVariable long id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@ApiOperation(value = "Salva novo tema no sistema")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Retorna tema cadastrado")})
	@PostMapping
	public ResponseEntity<TemaModel> post (@Valid @RequestBody TemaModel novaPostagem){
		return ResponseEntity.status(201).body(repository.save(novaPostagem));
	}
	
	@ApiOperation(value = "Atualizar tema existente")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Retorna tema atualizado")})
	@PutMapping
	public ResponseEntity<TemaModel> put (@Valid @RequestBody TemaModel tema){
		return ResponseEntity.status(201).body(repository.save(tema));
	}
	
	@ApiOperation(value = "Deletar tema existente")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Caso deletado!"),
	@ApiResponse(code = 400, message = "Id de tema invalido")})
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}
