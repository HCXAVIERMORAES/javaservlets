package servlets;

import java.io.Serializable;

import dao.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import model.ModelLogin;

public class ServletGenericUtil extends HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	//metodo  que retorna o id do usuario logado
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession(); // vem do FilterAutenticacao

		String usuarioLogado = (String) session.getAttribute("usuario");	
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();
	}
	
	//metodo que retorna o objeto usuario logado inteiro
public ModelLogin getUserLogadoObjet(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession(); // vem do FilterAutenticacao

		String usuarioLogado = (String) session.getAttribute("usuario");	
		
		return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado);
	}
	
}
