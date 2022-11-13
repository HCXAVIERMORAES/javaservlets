package servlets;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;
/**
 * classe Servle de controle do arquivo usuarios.jsp (chamada por ele)
 */

@MultipartConfig /*para trabalhar com imagem no banco*/
@WebServlet(urlPatterns = {"/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {
	
	private static final long serialVersionUID = 1L;	
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
    
   public ServletUsuarioController() {
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {			
			// metodo de deletar
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				
				String idUser = request.getParameter("id"); // operar na tele
				
				daoUsuarioRepository.deletarUser(idUser); //chama o metodo deletar
				
				//para recarregar apos rediredionar
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				
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
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request)); 
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.getWriter().write(json); //mensagem de resposta
				
			} /*metodo para buscar o id da pesquisa do modal*/
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {				
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id, super.getUserLogado(request));
				
				//para recarregar apos redirecionamento
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "Usuário em EDIÇÃO!");
				request.setAttribute("modolLogin", modelLogin); //redireciona
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);	
				
			}	/*metodo q utiliza o jstl , para lista dinamica*/
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				//criando lista dinamica com todos os usuarios 
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				//padrao, autera-se a msg e as variaves
				request.setAttribute("msg", "Usuário CARREGADOS!");
				request.setAttribute("modelLogins", modelLogins); //deve haver uma variavel so para carregar a lista
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			
			else {
				//para recarregar apos redirecionar
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
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
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");

			// iniciar objeto
			ModelLogin modelLogin = new ModelLogin();

			// os dados vem da tela em forma de string, logo o que não for deve-se converter
			// para o formato corretor
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null); // expressão de if(?) else(:)
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			
			/*fazer a imagem escolhida em upload chegar no banco. É neceessario usar classes especificas 
			 * da servlet
			 * */
			if (ServletFileUpload.isMultipartContent(request)){
				Part part = request.getPart("fileFoto"); /*pega foto da tela*/
				byte [] foto = IOUtils.toByteArray(part.getInputStream());/*converte a imagem para byte*/
				/*convertendo a imagem de byte para  string. Isso é padrão para o html entender */
				String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," +				
						new Base64().encodeBase64String(foto);
				
				modelLogin.setFotouser(imagemBase64);
				modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);				
			}
			
			//validação
			if(daoUsuarioRepository.validaLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				
				msg = "Este -- LOGIN JÁ EXISTE --, por favor informe outro login!";
			} else {
				
				if(modelLogin.isNovo()) {
					msg ="Dados -- GRAVADOS -- com sucesso!";
				} else {
					msg = "Dados -- ATUALIZADOS -- com sucesso!";
				}
				
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request)); //chama o metodo pra gravar no BD
			}
			//para recarregar apos redirecionar
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			
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
