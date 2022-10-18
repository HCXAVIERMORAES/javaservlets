package servlets;

import java.io.IOException;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;


/*
 * as pastas e dados em src/main/java, s�o o back end. As pastas e dados em src - main -webapp s�o front end.
 * Deve-se seguir o tipo de pastas da se��o. ex: pacotes(dao,filter, etc) ou folders(principal, web-inf, etc)
 * O chamado controller s�o as servlets. Poderia ser ServletLoginController
 * o mapeamento deve ter duas url,("/principal/ServletLogin", "/ServletLogin")**/

@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})/*Mapeamento de URL que vem da tela*/
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//instancia��o da classe DAOLogimRepository
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();

    public ServletLogin() {

    }
	/*Recebe os dados pela URL em par�metros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {

	}

	/*Recebe os dados enviados por um 	formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println(request.getParameter("nome"));
		//System.out.println(request.getParameter("idade"));
		
		//Os parametros devem  ser guardados em um objetos para serem passados todos juntos
		String login = (request.getParameter("login"));
		String senha = (request.getParameter("senha"));
		String url = request.getParameter("url");//pegando a url do index
		
		try {		
				/*fazer valida��o do objeto se os dados foram mesmo informados. sen�o redirecionar com uma mensagem 
				 * para a pagina do index.jsp*/
				if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
		
					/*instanciar a classe model(objeto que receber� os par�metros)*/
					ModelLogin modelLogin = new ModelLogin();
					modelLogin.setLogin(login);
					modelLogin.setSenha(senha);
					
					//simulando login
					/*if(modelLogin.getLogin().equalsIgnoreCase("admin") && 
							modelLogin.getSenha().equalsIgnoreCase("admin")) {*/
					//retirando a condi��o de simula��o ecolocandono lugar
					if(daoLoginRepository.validarAutenticao(modelLogin)) {
						
						/*passa-se o usuario e o objeto da se��, podendo ser apenas o atributo para n�o ficar a senha carregada
						 * na se��o*/
						request.getSession().setAttribute("usuario", modelLogin.getLogin());//atributo de se��o
						
						//antes de redirecionar fazer a valida��o da url
						if (url == null || url.equals("null")) {
							
							url ="principal/principal.jsp"; //pag. principal
							
						}
						
						//RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/principal.jsp");
						RequestDispatcher redirecionar = request.getRequestDispatcher(url);//deixar din�mico
						redirecionar.forward(request, response);
						
					} else {
						
						RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");//redireciona para o index,com '/' volta uma pag	
						request.setAttribute("msg", "Erro! Informe login e senha");//informa mensagem
						redirecionar.forward(request, response);//faz o redirecionamento para o index.jsp
						
					}
					
				} else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");//redireciona para o index
					request.setAttribute("msg", "Erro! Informe login e senha");//informa mensagem
					redirecionar.forward(request, response);//faz o redirecionamento para o index.jsp
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	} //fim do doPost

}//fim @WebServlet
