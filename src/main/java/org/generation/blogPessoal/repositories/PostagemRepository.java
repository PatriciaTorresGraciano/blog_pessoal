package org.generation.blogPessoal.repositories;

import java.util.List;

import org.generation.blogPessoal.models.PostagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel, Long>{

	public List<PostagemModel> findAllByTituloContainingIgnoreCase (String titulo);
	
	
	public PostagemModel findByTitulo(String titulo);
}
