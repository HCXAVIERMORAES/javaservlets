package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	//classe de manipula��o do banco de dados	
	
	private Connection connection;
	
	// construtor
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	//metodo de inser��o no Banco (insert)
	public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws Exception{		
		
		if(objeto.isNovo()) { /*gravar um novo*/
			String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id) VALUES (?,?, ?, ?, ?);";// script sql
	
			PreparedStatement preparedSql = connection.prepareStatement(sql);
			preparedSql.setString(1, objeto.getLogin()); // seguir a ordem da tabela dobanco
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			preparedSql.setLong(5, userLogado);
			
			preparedSql.execute(); //executa
			connection.commit(); //salva			
			
		} else {
			
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id = "+objeto.getId()+";";
			
			PreparedStatement prepareSql = connection.prepareStatement(sql);
			
			prepareSql.setString(1, objeto.getLogin());
			prepareSql.setString(2,objeto.getSenha());
			prepareSql.setString(3,objeto.getNome());
			prepareSql.setString(4,objeto.getEmail());
			
			prepareSql.executeUpdate();
			connection.commit();
			
		}
		//usando o metodo de consulta 
		return this.consultaUsuario(objeto.getLogin(), userLogado);		
	} //metodo gravar usuario
	
	/*Met�do para consultar todos os usuarios*/
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
			//ModelLogin.setSenha(resultado.getString("senha"));
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
			}
			
			return modelLogin;					
		}
	
	//metodo de valida��o do login
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
