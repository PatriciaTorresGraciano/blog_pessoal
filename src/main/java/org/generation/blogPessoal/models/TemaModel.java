package org.generation.blogPessoal.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "tb_tema")
public class TemaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long idTema;
	
	@NotBlank
	public String tema;

	@OneToMany(mappedBy = "temaRelacionado", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("temaRelacionado")
	private List<PostagemModel> postagens = new ArrayList<>();
	
	
	public long getId() {
		return idTema;
	}

	public void setId(long id) {
		this.idTema = id;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public long getIdTema() {
		return idTema;
	}

	public void setIdTema(long idTema) {
		this.idTema = idTema;
	}
	
	
}
