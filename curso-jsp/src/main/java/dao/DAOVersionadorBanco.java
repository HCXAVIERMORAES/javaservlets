package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;

public class DAOVersionadorBanco implements Serializable {

	private Connection connection;
	
	//construtor
	public DAOVersionadorBanco() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	//gravar sql
	public void gravaArquivoSqlRodado (String nome_file) throws Exception {
		
		String sql = "INSERT INTO versionadorbanco(arquivo_sql) VALUES (?);";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,nome_file);
		preparedStatement.execute();
		
	}
	
	
	public boolean arquivoSqlRodado (String nome_do_arquivo) throws Exception {
		
		String sql = "SELECT count(1) > 0 as rodado from versionadorbanco where arquivo_sql = ?";
		
		PreparedStatement prepStatement = connection.prepareStatement(sql);
		prepStatement.setString(1, nome_do_arquivo);
		
		ResultSet resultSet = prepStatement.executeQuery();
		
		resultSet.next();
		
		return resultSet.getBoolean("rodado"); //retorna se já foi rodado ou não
		
	}
	
}
