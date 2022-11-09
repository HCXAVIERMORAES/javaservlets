package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	/*
	 * Classe que tem a responsabilidade de trabalhar no banco (insert, delete, update e select)
	 *  */
	
	//fazendo a conex�o
	private Connection connetion;
	
	//fazendo construtor para conex�o
	public DAOLoginRepository() {
		// dentro do construtor, pois sempre que se intanciar um objeto dessa classe j� terar a conex�o
		connetion = SingleConnectionBanco.getConnection(); //fazendo conex�o com a classe que conecta ao banco
	}
	
	//metodo de valida��o do login, que devereceber um objeto com o login e senha, no caso classe ModelLogin
	public boolean validarAutenticao(ModelLogin modelLogin) throws Exception{
		//sql de consulta ao banco
		String sql = " select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?) ";
		
		//objeto para preparar o sql
		PreparedStatement statement = connetion.prepareStatement(sql);
		
		//passar os paramentros para o sql
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		//resultado 
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()) {
			return true; //autenticado
		}
		
		return false;//n�o autenticado
		
	} //fim metodo validarAutenticao

}// fim classe
