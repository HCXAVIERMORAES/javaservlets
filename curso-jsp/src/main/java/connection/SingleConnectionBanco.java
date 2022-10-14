package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	/*Classe de conexão com o Bnco de dados PostgreSql 13. A conexão deve ser única, logo o que se abre e fecha são 
	seções e transações com o banco
	As definiçoes de conexão são: ter uma url com o BD, um usuario e uma senha
	**/
	
	//variaveis staticas pois deve-se ter uma única conexão
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres"; 
	private static String senha = "Bel2107";
	private static Connection connection = null;
	
	/*Método para retornar uma conexão existente*/
	public static Connection getConnection() {
		return connection;
	}
	
	/*Para garantir que sempre se terá uma conexão usa-se os dois metodos um direto e outro atraves de um objeto
	será usado quando for chamado a classe de modo direta */
	static {
		conectar();
	}
	
	/*Sempre que seja invocada a classe SingleConnectionBanco o metodo deve ser invocado, logo cria-se um construtor*/
	public SingleConnectionBanco() { //Quando tiver uma instáncia  vai conectar
		conectar(); //chamada do método de conexão
	}
	
	
	//Metodo de conexão
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); //carrega o drive de conexão com o BD
				connection = DriverManager.getConnection(banco,user,senha); // faz a conexão com o BD
				connection.setAutoCommit(false); //para não efetuar mudanças no BD sem nosso comando
			}
			
		} catch (Exception e) {
			e.printStackTrace(); //mostrar qualquer erro no momento de conectar
		}
		
	}
	
	//teste é feito no FilterAutenticação atraves de debuguer

}
