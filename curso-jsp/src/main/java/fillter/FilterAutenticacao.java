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

 *@WebFilterIntercepta todas as requisições que vierem do projeto 
 * Servlet Filter implementation class FilterAutenticacao Intercepta todas as
 * requisições que vierem do projeto ou mapeamento, porém o index não pode
 * passar por ele, pois deve-se logar no sistema. Na anotação interceptará tudo
 * que vier do pacote principa como se segui o padrão
 * (urlPatterns={"/principal/*})
 */
@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutenticacao implements Filter {

	// Declarando a conexão
	private static Connection connection; // objeto de conexão

	/**
	 * Construtor padrão.
	 */
	public FilterAutenticacao() {

	}

	/**
	 * @see Filter#destroy(), encerra os processo quando o servidor é parado. Ex:
	 *      Mataria os processos de conexão com o BD
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
	 *      Intercepta as requisições e da as respostas do sistema, logo tudo que
	 *      for feito passa ppor ele Ex: validação de autenticação, comit, rolbak de
	 *      transaçoes do BD, validar e fazer redirecionamento de paginas, etc
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// devido ao BD,coloca-se o conteudo em um try catch,e usa-se um
		try {
				/* Pega-se o HttpServletRequest do servletLogin */// no debug cai aqui usa-se F6
				HttpServletRequest req = (HttpServletRequest) request; // pega-se o http. cast (HttpServletRequest) para
																		// conversão
				HttpSession session = req.getSession(); // pegar a sesão
	
				String usuarioLogado = (String) session.getAttribute("usuario"); // vindo do getAttribute do
																					// ServletLogin.pegando o atributo
	
				String urlParaAutenticar = req.getServletPath();// url que está sendo acessada
	
				// fazendo a validação. Validar se esta logado, senão retornar para a tela de
				// login (index.jsp)
				if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase(
						"/principal/ServletLogin")) { /*
														 * igual a null ou diferente de null e não contem ServletLogin
														 */
					// sem o '/principal' cria-se um bug e não entra na servlet
	
					// redirecionar para o index e passar a url que estava tentando acessar
					RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
					request.setAttribute("msg", "Por favor realize o login!");// memsagem
					redireciona.forward(request, response);
					return; // parar a execução e redireciona para o login
					
				} else {	
					// acima dele é feita as validações e ele deixa o processo continuar
					chain.doFilter(request, response);	
					// no index.jsp, passa-se a url
					
					connection.commit();//comita as alterações no BD se tudo estiver certo
				}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// se houver uma excessão  retorna erro do rolback
				e1.printStackTrace();
			}
		}

	}

	/**
	 * @see Filter#init(FilterConfig), inicia os processos quando o servidor sobe o
	 *      projeto. Ex: iniciar a conexão com o Banco de dados
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// inicializando
		connection = SingleConnectionBanco.getConnection();
	}

}
