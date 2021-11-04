package org.generation.blogPessoal.repositories;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

	public Optional<UsuarioModel> findByUsuario(String usuario);
	public Optional<UsuarioModel> findByEmail (String email);
	public List<UsuarioModel> findAllByNomeContainingIgnoreCase(String nome);
	
}
