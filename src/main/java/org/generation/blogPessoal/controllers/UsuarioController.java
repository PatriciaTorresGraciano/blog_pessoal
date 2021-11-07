package org.generation.blogPessoal.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.blogPessoal.models.UsuarioLogin;
import org.generation.blogPessoal.models.UsuarioModel;
import org.generation.blogPessoal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(tags = "Controlador de Usuário", description = "Utilitário de Usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@ApiOperation(value = "Busca lista de usuários no sistema")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna com usuário"),
			@ApiResponse(code = 204, message = "Retorno sem usuário") })
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> buscarTodos() {
		List<UsuarioModel> objetoLista = usuarioService.buscarTodos();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Busca usuário por nome")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna usuário existente ou inexistente"),
			@ApiResponse(code = 204, message = "Retorno inexistente") })
	@GetMapping("/nome/{nome_usuario}")
	public ResponseEntity<List<UsuarioModel>> buscarPorNome(@PathVariable(value = "nome_usuario") String nome) {
		List<UsuarioModel> objetoLista = usuarioService.buscarPorNome(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}

	@ApiOperation(value = "Busca usuário por Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna usuário existente ou inexistente"),
			@ApiResponse(code = 400, message = "Retorno inexistente") })
	@GetMapping("/{id_usuario}")
	public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		return usuarioService.buscarPorId(idUsuario).map(resp -> ResponseEntity.status(200).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"ID inexistente, passe um ID valido para pesquisa!.");
				});
	}

	@ApiOperation(value = "Logar usuário no sistema")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Retorna usuário cadastrado"),
			@ApiResponse(code = 400, message = "Erro na requisição") })
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> logar(@RequestBody @Valid Optional<UsuarioLogin> user) {
		return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@ApiOperation(value = "Cadastrar novo usuário no sistema")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Retorna usuário cadastrado"),
			@ApiResponse(code = 400, message = "Erro na requisição") })
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> postar(@RequestBody @Valid UsuarioModel usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.cadastrarUsuario(usuario));
	}

	@ApiOperation(value = "Deletar usuário existente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Caso deletado!"),
			@ApiResponse(code = 400, message = "Id de usuário inválido") })
	@DeleteMapping("/deletar/{id_usuario}")
	public void deletar(@PathVariable(value = "id_usuario") long idUsuario) {

		usuarioService.deletar(idUsuario);

	}
}
