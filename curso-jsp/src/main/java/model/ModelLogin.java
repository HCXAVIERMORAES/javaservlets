package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{ //sempre se implementa o Serializable do java.io
	//È para a parte de compilação das classes
	private static final long serialVersionUID = 1L;
	
	//campos do cadastro
	private Long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String sexo;
	private boolean useradmin; //coluna criada no banco para saber quem é o usuário admin
	private String perfil; //para o select de perfil.
	private String fotouser;
	private String extensaofotouser;
	
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
	
	
	public String getSexo() {
		return sexo;
	}
	
	public String getFotouser() {
		return fotouser;
	}

	public void setFotouser(String fotouser) {
		this.fotouser = fotouser;
	}

	public String getExtensaofotouser() {
		return extensaofotouser;
	}

	public void setExtensaofotouser(String extensaofotouser) {
		this.extensaofotouser = extensaofotouser;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public void setUseradmin(boolean useradmin) {
		this.useradmin = useradmin;
	}
	
	public boolean getUseradmin() {
		return useradmin;
		
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
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
