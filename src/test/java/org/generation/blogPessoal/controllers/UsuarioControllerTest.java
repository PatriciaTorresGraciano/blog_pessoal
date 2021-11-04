package org.generation.blogPessoal.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.generation.blogPessoal.models.UsuarioModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControladorTest {

	private @Autowired TestRestTemplate testRestTemplate;

	private UsuarioModel usuarioParaCriar;
	@BeforeEach
	void start() {

		usuarioParaCriar = new UsuarioModel("Patrícia", "patricia@email.com", "134652");
	}

	@Test
	@Order(1)
	void salvarUsuarioRetornaStatus201() {

		HttpEntity<UsuarioModel> requisicao = new HttpEntity<UsuarioModel>(usuarioParaCriar);

		ResponseEntity<UsuarioModel> response = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				requisicao, UsuarioModel.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());

	}

}