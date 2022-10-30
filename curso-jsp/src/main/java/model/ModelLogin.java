package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{ //sempre se implementa o Serializable do java.io
	//� para a parte de compila��o das classes
	private static final long serialVersionUID = 1L;
	
	//campos do cadastro
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	
	//metodo para testar se existe id
	public boolean isNovo() {
		if(this.id == null) {
			return true; /*inserir um novo*/		
		} else if(this.id != null && this.id > 0) {
			return false;/*atualizar*/
		}
		 return id == null;
	}
	
	//get e set
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
		
}
