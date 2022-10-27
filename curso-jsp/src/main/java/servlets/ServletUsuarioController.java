package servlets;

import java.io.IOException;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/**
 * classe Servle de controle do arquivo usuarios.jsp (chamada por ele)
 */
public class ServletUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//inicializando a classe DAOUsuarioRepository
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    
    public ServletUsuarioController() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath()); retira-se do original
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		try {
			
			// 1ª intercepta os atributos do form. do usuario;
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			// iniciar objeto
			ModelLogin modelLogin = new ModelLogin();

			// os dados vem da tela em forma de string, logo o que não for deve-se converter
			// para o formato corretor
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null); // expressão de if(?) else(:)
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			daoUsuarioRepository.gravarUsuario(modelLogin); //chama o metodo pra gravar no BD
			
			//menssagem de aviso
			request.setAttribute("msg", "Dados salvos com sucesso!");
			
// para o lado do servidor manter dados na tela, na tela usa-se o// value="${modolLogin.atributo do imput}" (string usada no setAttribute
			request.setAttribute("modolLogin", modelLogin);
// deixando o codigo em uma linha
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	    
		} catch (Exception e) {
			e.printStackTrace();			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erros.jsp");//redireciona para o erros.jsp
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	    
	}

}
