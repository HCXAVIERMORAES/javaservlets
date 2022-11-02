package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		try {			
			// metodo de deletar
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				
				String idUser = request.getParameter("id"); // operar na tele
				
				daoUsuarioRepository.deletarUser(idUser); //chama o metodo deletar
				
				request.setAttribute("msg", "Dados EXCLUIDOS com sucesso!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			} 
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				
				String idUser = request.getParameter("id"); 
				
				daoUsuarioRepository.deletarUser(idUser); 
				response.getWriter().write("Dados EXCLUIDOS com sucesso!"); //mensagem de resposta
				
			}	
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				
				String nomeBusca = request.getParameter("nomeBusca"); 
					//System.out.println(nomeBusca); para testar 1ª partedo teste
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca); 
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json); //mensagem de resposta
				
			}			
			
			else {
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response); //fluxo normal
			}
		
		} catch (Exception e) {
			e.printStackTrace();			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erros.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		try {
			
			String msg = "Dados salvos com sucesso!";
			
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
			
			//validação
			if(daoUsuarioRepository.validaLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				
				msg = "Este -- LOGIN JÁ EXISTE --, por favor informe outro login!";
			} else {
				
				if(modelLogin.isNovo()) {
					msg ="Dados -- GRAVADOS -- com sucesso!";
				} else {
					msg = "Dados -- ATUALIZADOS -- com sucesso!";
				}
				
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin); //chama o metodo pra gravar no BD
			}
			
			
			//menssagem de aviso
			request.setAttribute("msg", msg );
			
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
