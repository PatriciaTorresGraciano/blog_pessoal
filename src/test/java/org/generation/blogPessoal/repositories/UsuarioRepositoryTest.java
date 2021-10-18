package org.generation.blogPessoal.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.Lifecycle;
import org.generation.blogPessoal.models.UsuarioModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class UsuarioRepositoryTest {

	@Autowired 
	private UsuarioRepository repository;
	
	@BeforeEach
	void start() {
		
		UsuarioModel objetoUsuario1 = new UsuarioModel("usuario1", "usuario1@email.com", "654321");
		if(!repository.findByEmail(objetoUsuario1.getEmail()).isPresent())
			repository.save(objetoUsuario1);
		
		UsuarioModel objetoUsuario2 = new UsuarioModel("usuario2", "usuario1@email.com", "134652");
		if(!repository.findByEmail(objetoUsuario2.getEmail()).isPresent())
			repository.save(objetoUsuario2);
	}
	
	@Test
	void findByEmailExistReturnTrue() {
		Optional<UsuarioModel> optionalUsuario1 = repository.findByEmail("usuario1@email.com");
		
		assertTrue(optionalUsuario1.get().getEmail().equals("usuario1@email.com"));		
	}
	
	@Test
	void findAllByNomeContainingIgnoreCaseReturnDoisUsuario() {
		List<UsuarioModel> objetoLista = repository.findAllByNomeContainingIgnoreCase("usuario");
		
		assertEquals(2, objetoLista.size());
	}
	
	@AfterAll
	void end() {
		System.out.println("Teste FINALIZADO!");
	}

}
