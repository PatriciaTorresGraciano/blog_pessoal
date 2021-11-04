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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> buscarTodos() {
		List<UsuarioModel> objetoLista = usuarioService.buscarTodos();

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@GetMapping("/nome/{nome_usuario}")
	public ResponseEntity<List<UsuarioModel>> buscarPorNome(@PathVariable(value = "nome_usuario") String nome) {
		List<UsuarioModel> objetoLista = usuarioService.buscarPorNome(nome);

		if (objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable(value = "id_usuario") Long idUsuario) {
		return usuarioService.buscarPorId(idUsuario).map(resp -> ResponseEntity.status(200).body(resp))
				.orElseThrow(() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,
							"ID inexistente, passe um ID valido para pesquisa!.");
				});
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> logar(@RequestBody @Valid Optional<UsuarioLogin> user){
		return usuarioService.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> postar(@RequestBody @Valid UsuarioModel usuario){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.cadastrarUsuario(usuario));
	}
	
	@DeleteMapping("/deletar/{id_usuario}")
	public void deletar(@PathVariable(value = "id_usuario") long idUsuario) {
	
		usuarioService.deletar(idUsuario);
	
	

}
}
