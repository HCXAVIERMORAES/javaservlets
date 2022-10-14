package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	/*Classe de conex�o com o Bnco de dados PostgreSql 13. A conex�o deve ser �nica, logo o que se abre e fecha s�o 
	se��es e transa��es com o banco
	As defini�oes de conex�o s�o: ter uma url com o BD, um usuario e uma senha
	**/
	
	//variaveis staticas pois deve-se ter uma �nica conex�o
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres"; 
	private static String senha = "Bel2107";
	private static Connection connection = null;
	
	/*M�todo para retornar uma conex�o existente*/
	public static Connection getConnection() {
		return connection;
	}
	
	/*Para garantir que sempre se ter� uma conex�o usa-se os dois metodos um direto e outro atraves de um objeto
	ser� usado quando for chamado a classe de modo direta */
	static {
		conectar();
	}
	
	/*Sempre que seja invocada a classe SingleConnectionBanco o metodo deve ser invocado, logo cria-se um construtor*/
	public SingleConnectionBanco() { //Quando tiver uma inst�ncia  vai conectar
		conectar(); //chamada do m�todo de conex�o
	}
	
	
	//Metodo de conex�o
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); //carrega o drive de conex�o com o BD
				connection = DriverManager.getConnection(banco,user,senha); // faz a conex�o com o BD
				connection.setAutoCommit(false); //para n�o efetuar mudan�as no BD sem nosso comando
			}
			
		} catch (Exception e) {
			e.printStackTrace(); //mostrar qualquer erro no momento de conectar
		}
		
	}
	
	//teste � feito no FilterAutentica��o atraves de debuguer

}
