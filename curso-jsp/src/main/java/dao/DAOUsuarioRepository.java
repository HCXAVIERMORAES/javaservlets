package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	//classe de manipulação do banco de dados	
	
	private Connection connection;
	
	// construtor
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	//metodo de inserção no Banco (insert)
	public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws Exception{		
		
		if(objeto.isNovo()) { /*gravar um novo*/
			String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, "
					+ "cep, logradouro, bairro, localidade, uf, numero)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";// script sql
	
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			preparedSql.setString(1, objeto.getLogin()); // seguir a ordem da tabela do banco
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.setLong(5, userLogado);
			preparedSql.setString(6, objeto.getPerfil());
			preparedSql.setString(7, objeto.getSexo());
			preparedSql.setString(8, objeto.getCep());
			preparedSql.setString(9, objeto.getLogradouro());
			preparedSql.setString(10, objeto.getBairro());
			preparedSql.setString(11, objeto.getLocalidade());
			preparedSql.setString(12, objeto.getUf());
			preparedSql.setString(13, objeto.getNumero());
			
			preparedSql.execute(); //executa
			connection.commit(); //salva	
			
			/*para a foto deixar fazer o cadastro e depois fazer um update parra foto,
			 * para o sql não ficar mto complexo*/
			if (objeto.getFotouser() !=null && !objeto.getFotouser().isEmpty()) {
				sql = "UPDATE model_login set fotouser=?, extensaofotouser=? where login = ?";
				
				preparedSql = connection.prepareStatement(sql);
				preparedSql.setString(1, objeto.getFotouser()); 
				preparedSql.setString(2, objeto.getExtensaofotouser());
				preparedSql.setString(3, objeto.getLogin());
				
				preparedSql.execute(); 
				connection.commit();
			}
			
		} else {
			
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?,"
					+" cep=?, logradouro=?, bairro=?, localidade=?, uf=?, numero=?"
					+"  WHERE id = "+objeto.getId()+";";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString(1, objeto.getLogin());
			prepareSql.setString(2,objeto.getSenha());
			prepareSql.setString(3,objeto.getNome());
			prepareSql.setString(4,objeto.getEmail());
			prepareSql.setString(5, objeto.getPerfil());
			prepareSql.setString(6, objeto.getSexo());
			
			prepareSql.setString(7, objeto.getCep());
			prepareSql.setString(8, objeto.getLogradouro());
			prepareSql.setString(9, objeto.getBairro());
			prepareSql.setString(10, objeto.getLocalidade());
			prepareSql.setString(11, objeto.getUf());
			prepareSql.setString(12, objeto.getNumero());			
			
			prepareSql.executeUpdate();
			connection.commit();
			
			/*para atualizar  foto do usuario ja cadastrado*/
			if (objeto.getFotouser() !=null && !objeto.getFotouser().isEmpty()) {
				sql = "UPDATE model_login set fotouser=?, extensaofotouser=? where id = ?";
				
				prepareSql = connection.prepareStatement(sql);
				prepareSql.setString(1, objeto.getFotouser()); 
				prepareSql.setString(2, objeto.getExtensaofotouser());
				prepareSql.setLong(3, objeto.getId());
				
				prepareSql.execute(); 
				connection.commit();
			}
			
		}
		//usando o metodo de consulta 
		return this.consultaUsuario(objeto.getLogin(), userLogado);		
	} //metodo gravar usuario
	
	/*Metódo para consultar todos os usuarios*/
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception {
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE useradmin is false and  usuario_id = "+ userLogado;
		
		PreparedStatement statemet = connection.prepareStatement(sql);
				
		ResultSet resultado = statemet.executeQuery();
		
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			//ModelLogin.setSenha(resultado.getString("senha"));
		/*	modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero")); */
			
			retorno.add(modelLogin);		
		}
		
		return retorno;
	}	
	
	//metodo de consultar usuario pelo ajax
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception {
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE upper(nome) like upper(?) AND useradmin is false and  usuario_id = ?";
		
		PreparedStatement statemet = connection.prepareStatement(sql);
		
		statemet.setString(1, "%"+ nome + "%");
		statemet.setLong(2, userLogado );
		
		ResultSet resultado =statemet.executeQuery();
		
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			//ModelLogin.setSenha(resultado.getString("senha"));
			retorno.add(modelLogin);		
		}
		
		return retorno;
	}	
	
	//metodo de consulta de usuario pelo login
		public ModelLogin consultaUsuario(String login) throws Exception 	{
			
			ModelLogin modelLogin = new ModelLogin(); //inicia o objeto, no inicio, para sempre te-lo para retornar
			
			//script de consulta
			String sql = "SELECT * from model_login where upper(login) = upper('"+login+"') AND useradmin is false ";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			//para receber o resultado
			ResultSet resultado = statement.executeQuery();
		
			//se houver resultado fazer
			while(resultado.next()) {
				modelLogin.setId(resultado.getLong("id")); //pega do resultado o id, etc
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setSenha(resultado.getString("senha"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				modelLogin.setFotouser(resultado.getNString("fotouser"));//mosrtrar foto
				
				modelLogin.setCep(resultado.getString("cep"));
				modelLogin.setLogradouro(resultado.getString("logradouro"));
				modelLogin.setBairro(resultado.getString("bairro"));
				modelLogin.setLocalidade(resultado.getString("localidade"));
				modelLogin.setUf(resultado.getString("uf"));
				modelLogin.setNumero(resultado.getString("numero"));
			}
			
			return modelLogin;					
		}
		
		//metodo de consulta de usuario logado
	public ModelLogin consultaUsuarioLogado(String login) throws Exception 	{
		
		ModelLogin modelLogin = new ModelLogin(); //inicia o objeto, no inicio, para sempre te-lo para retornar
		
		//script de consulta
		String sql = "SELECT * from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		//para receber o resultado
		ResultSet resultado = statement.executeQuery();
	
		//se houver resultado fazer
		while(resultado.next()) {
			modelLogin.setId(resultado.getLong("id")); //pega do resultado o id, etc
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));	
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));//para consultar se é admin
			modelLogin.setPerfil(resultado.getString("perfil"));//para retornar o perfil
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotouser(resultado.getString("fotouser"));
				
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}
		
		return modelLogin;					
	}
	
	//metodo de consulta de usuario pelo login com userLogado
	public ModelLogin consultaUsuario(String login, Long userLogado) throws Exception 	{
		
		ModelLogin modelLogin = new ModelLogin(); //inicia o objeto, no inicio, para sempre te-lo para retornar
		
		//script de consulta
		String sql = "SELECT * from model_login where upper(login) = upper('"+login+"') AND useradmin is false "
				+ "AND usuario_id = " + userLogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		//para receber o resultado
		ResultSet resultado = statement.executeQuery();
	
		//se houver resultado fazer
		while(resultado.next()) {
			modelLogin.setId(resultado.getLong("id")); //pega do resultado o id, etc
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));	
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotouser(resultado.getString("fotouser"));
			
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}
		
		return modelLogin;					
	}
	
	//metodo de consultar por id
	//metodo de consulta de usuario pelo login
		public ModelLogin consultaUsuarioID(String id, Long userLogado) throws Exception 	{
			
			ModelLogin modelLogin = new ModelLogin(); 
			
			//script de consulta
			String sql = "SELECT * from model_login where id = ? AND useradmin is false AND usuario_id = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1,Long.parseLong(id));
			statement.setLong(2, userLogado);
			
			//para receber o resultado
			ResultSet resultado = statement.executeQuery();
		
			//se houver resultado fazer
			while(resultado.next()) {
				modelLogin.setId(resultado.getLong("id")); //pega do resultado o id, etc
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setSenha(resultado.getString("senha"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				modelLogin.setFotouser(resultado.getString("fotouser"));
				modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
				
				modelLogin.setCep(resultado.getString("cep"));
				modelLogin.setLogradouro(resultado.getString("logradouro"));
				modelLogin.setBairro(resultado.getString("bairro"));
				modelLogin.setLocalidade(resultado.getString("localidade"));
				modelLogin.setUf(resultado.getString("uf"));
				modelLogin.setNumero(resultado.getString("numero"));
			}
			
			return modelLogin;					
		}
	
	//metodo de validação do login
	public boolean validaLogin(String login) throws Exception{
		
		String sql = "SELECT count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"');";
		
		PreparedStatement statement = connection.prepareStatement(sql);		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next(); /*para ele entrar nos resultados do sql*/ 
		return resultado.getBoolean("existe");
				
	} //fim validaLogin
	
	//metodo de delete
	public void deletarUser(String idUser) throws Exception {
		
		String sql = "DELETE FROM model_login WHERE id = ? AND useradmin is false;";		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		prepareSql.setLong(1, Long.parseLong(idUser));
		prepareSql.executeUpdate();
		connection.commit();		
		
	}
	

} //fim classe DAOUsuarioRepository
