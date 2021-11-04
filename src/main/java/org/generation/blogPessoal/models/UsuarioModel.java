package org.generation.blogPessoal.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "tb_usuario")
public class UsuarioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;
	
	@NotBlank
	private String nome;
	
	private String usuario;
	
	@Email
	private String email;
	
	@NotBlank 
	@Size(min = 5, max = 100)
	private String senha;

	@OneToMany(mappedBy = "criadorPostagem", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("criadorPostagem")
	private List<PostagemModel> postagens = new ArrayList<>();
	
	public UsuarioModel(String nome,String email,String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public UsuarioModel() {
	}
	
	public UsuarioModel(long idUsuario, @NotBlank String nome, @Email String email,
			@NotBlank @Size(min = 5, max = 100) String senha) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<PostagemModel> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<PostagemModel> postagens) {
		this.postagens = postagens;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
