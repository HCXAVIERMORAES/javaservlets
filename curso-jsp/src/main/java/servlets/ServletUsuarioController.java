package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;
/**
 * classe Servle de controle do arquivo usuarios.jsp (chamada por ele)
 */

@MultipartConfig /*para trabalhar com imagem no banco*/
//@WebServlet(urlPatterns = {"/ServletUsuarioController"})
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
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			} 
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				
				String idUser = request.getParameter("id"); 
				
				daoUsuarioRepository.deletarUser(idUser); 
				response.getWriter().write("Dados EXCLUIDOS com sucesso!"); //mensagem de resposta
				
			}	
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				
				String nomeBusca = request.getParameter("nomeBusca"); 
					
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request)); 
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.addHeader(	"totalPagina", "" 
				+ daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, super.getUserLogado(request)));
				response.getWriter().write(json); //mensagem de resposta
				
			} 
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {
					
				String nomeBusca = request.getParameter("nomeBusca"); 
				String pagina = request.getParameter("pagina");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioListOffSet(nomeBusca, 
						super.getUserLogado(request),Integer.parseInt(pagina)); 
				
				ObjectMapper mapper = new ObjectMapper();
				
				String json = mapper.writeValueAsString(dadosJsonUser);
				
				response.addHeader("totalPagina", "" 
				+ daoUsuarioRepository.consultaUsuarioListTotalPaginaPaginacao(nomeBusca, super.getUserLogado(request)));
				response.getWriter().write(json); //mensagem de resposta
				
			} 			
			/*metodo para buscar o id da pesquisa do modal*/
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {				
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id, super.getUserLogado(request));
				
				//para recarregar apos redirecionamento
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				
				request.setAttribute("msg", "Usuário em EDIÇÃO!");
				request.setAttribute("modolLogin", modelLogin); //redireciona
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);	
				
			}	/*metodo q utiliza o jstl , para lista dinamica*/
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				//criando lista dinamica com todos os usuarios 
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				//padrao, autera-se a msg e as variaves
				request.setAttribute("msg", "Usuário CARREGADOS!");
				request.setAttribute("modelLogins", modelLogins); //deve haver uma variavel so para carregar a lista
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));	
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				String idUser = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(idUser, super.getUserLogado(request));
				
				if(modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()) {
					response.setHeader("Content-Disposition","attachment;filename=arquivo." + modelLogin.getExtensaofotouser());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotouser().split("\\,")[1]));
				}				
			}
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {
				Integer offset = Integer.parseInt(request.getParameter("pagina"));
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioListPaginada(this.getUserLogado(request), offset);
				
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
				
			}
			/*relatorio*/
			else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("imprimirRelatorioUser")) {
				
				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				//fazendo condição para impressao
				if(dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					request.setAttribute("listaUser", 
							daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request)));
					
				} else {
					
					request.setAttribute("listaUser", 
							daoUsuarioRepository.consultaUsuarioListRel(super.getUserLogado(request),
									dataInicial, dataFinal));
				}
				
				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);
				request.getRequestDispatcher("principal/reluser.jsp").forward(request, response);
			}
				
			
			
			else {
				//para recarregar apos redirecionar
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
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
		
		try {			
			String msg = "Operação realizada com sucesso!";
			
			// 1ª intercepta os atributos do form. do usuario;
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");
			String dataNascimento = request.getParameter("dataNascimento");
			String rendaMensal = request.getParameter("rendamensal"); //trazendo da tela
			
			//retirando o R$ e ,
			rendaMensal = rendaMensal.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");

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
			
			modelLogin.setCep(cep);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			modelLogin.setNumero(numero);
			/*bugue da data a ser arrumado assim
			Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento)));
			*/
			modelLogin.setDataNascimento(Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").
					format(new SimpleDateFormat("dd/mm/yyyy").parse(dataNascimento))));
			
			//convertendo
			modelLogin.setRendamensal(Double.valueOf(rendaMensal));
			
			/*fazer a imagem escolhida em upload chegar no banco. É neceessario usar classes especificas 
			 * da servlet
			 * */
			if (ServletFileUpload.isMultipartContent(request)){
				
				Part part = request.getPart("fileFoto"); /*pega foto da tela*/
				
				if (part.getSize() > 0) {
					byte [] foto = IOUtils.toByteArray(part.getInputStream());/*converte a imagem para byte*/
					/*convertendo a imagem de byte para  string. Isso é padrão para o html entender */
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," +				
							new Base64().encodeBase64String(foto);
					
					modelLogin.setFotouser(imagemBase64);
					modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);
				}
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
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
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
