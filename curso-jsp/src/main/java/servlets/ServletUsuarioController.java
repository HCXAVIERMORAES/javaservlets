package servlets;

import java.io.IOException;

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

    
    public ServletUsuarioController() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath()); retira-se do original
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		//1ª intercepta os atributos do form. do usuario;
		String id = request.getParameter("id");             
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
	    String login = request.getParameter("login");
	    String senha = request.getParameter("senha");
		
	    //iniciar objeto
	    ModelLogin modelLogin = new ModelLogin();
	    
	    //os dados vem da tela em forma de string, logo o que não for deve-se converter para o formato corretor
	    modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null); //expressão de if(?) else(:)
	    modelLogin.setNome(nome);
	    modelLogin.setEmail(email);
	    modelLogin.setLogin(login);
	    modelLogin.setSenha(senha);
	    
	    //faze o redirecionamento para a pgina de cadastro de usuario
	   // RequestDispatcher redireciona = request.getRequestDispatcher("principal/usuario.jsp");
	   // request.setAttribute("modelLogin", modelLogin);
	   // redireciona.forward(request, response);
	    
	  //para o lado do servidor manter dados na tela, na tela usa-se o value="${modolLogin.atributo do imput}" (string usado no setAttribute
	    request.setAttribute("modolLogin", modelLogin);
	    
	    //deixando o codigo em uma linha
	    request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	    
	}

}
