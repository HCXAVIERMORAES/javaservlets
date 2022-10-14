package fillter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**

 *@WebFilterIntercepta todas as requisi��es que vierem do projeto 
 * Servlet Filter implementation class FilterAutenticacao Intercepta todas as
 * requisi��es que vierem do projeto ou mapeamento, por�m o index n�o pode
 * passar por ele, pois deve-se logar no sistema. Na anota��o interceptar� tudo
 * que vier do pacote principa como se segui o padr�o
 * (urlPatterns={"/principal/*})
 */
@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao implements Filter {

	// Declarando a conex�o
	private static Connection connection; // objeto de conex�o

	/**
	 * Construtor padr�o.
	 */
	public FilterAutenticacao() {

	}

	/**
	 * @see Filter#destroy(), encerra os processo quando o servidor � parado. Ex:
	 *      Mataria os processos de conex�o com o BD
	 */
	public void destroy() {

		try {
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} // fechar a conexa

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain).
	 *      Intercepta as requisi��es e da as respostas do sistema, logo tudo que
	 *      for feito passa ppor ele Ex: valida��o de autentica��o, comit, rolbak de
	 *      transa�oes do BD, validar e fazer redirecionamento de paginas, etc
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// devido ao BD,coloca-se o conteudo em um try catch,e usa-se um
		try {
				/* Pega-se o HttpServletRequest do servletLogin */// no debug cai aqui usa-se F6
				HttpServletRequest req = (HttpServletRequest) request; // pega-se o http. cast (HttpServletRequest) para
																		// convers�o
				HttpSession session = req.getSession(); // pegar a ses�o
	
				String usuarioLogado = (String) session.getAttribute("usuario"); // vindo do getAttribute do
																					// ServletLogin.pegando o atributo
	
				String urlParaAutenticar = req.getServletPath();// url que est� sendo acessada
	
				// fazendo a valida��o. Validar se esta logado, sen�o retornar para a tela de
				// login (index.jsp)
				if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase(
						"/principal/ServletLogin")) { /*
														 * igual a null ou diferente de null e n�o contem ServletLogin
														 */
					// sem o '/principal' cria-se um bug e n�o entra na servlet
	
					// redirecionar para o index e passar a url que estava tentando acessar
					RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
					request.setAttribute("msg", "Por favor realize o login!");// memsagem
					redireciona.forward(request, response);
					return; // parar a execu��o e redireciona para o login
					
				} else {	
					// acima dele � feita as valida��es e ele deixa o processo continuar
					chain.doFilter(request, response);	
					// no index.jsp, passa-se a url
					
					connection.commit();//comita as altera��es no BD se tudo estiver certo
				}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// se houver uma excess�o  retorna erro do rolback
				e1.printStackTrace();
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig), inicia os processos quando o servidor sobe o
	 *      projeto. Ex: iniciar a conex�o com o Banco de dados
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// inicializando
		connection = SingleConnectionBanco.getConnection();
	}

}
