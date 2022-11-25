package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
//campos para consumo de API webservice via cep
	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
	private String numero;
//campo para trabalhar com data
	private Date dataNascimento;
//campo monetario
	private Double rendamensal;
//campo para lista de telefones dos objetos
	private List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();
			
	
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
	
	public void setTelefones(List<ModelTelefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<ModelTelefone> getTelefones() {
		return telefones;
	}
	
	public void setRendamensal(Double rendamensal) {
		this.rendamensal = rendamensal;
	}
	
	public Double getRendamensal() {
		return rendamensal;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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
	
	public String getSexo() {
		return sexo;
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
	
	//criando um objeto para mostra telefone
	public String getMostraTelefoneRel() {
		
		String fone = "Telefone:\n";
		for(ModelTelefone modelTelefone : telefones) {
			fone += modelTelefone.getNumero() + "\n";
		}
		
		return fone;
		
	}
		
}
