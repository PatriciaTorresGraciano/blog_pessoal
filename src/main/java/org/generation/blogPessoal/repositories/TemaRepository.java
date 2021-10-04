package org.generation.blogPessoal.repositories;

import java.util.List;

import org.generation.blogPessoal.models.TemaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<TemaModel, Long>{
	
	public List<TemaModel> findAllByTemaContainingIgnoreCase(String tema);
	
}
