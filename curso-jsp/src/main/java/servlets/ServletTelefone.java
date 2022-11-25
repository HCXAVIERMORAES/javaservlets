package servlets;

import java.io.IOException;
import java.util.List;

import dao.DAOTelefoneRepository;
import dao.DAOUsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

//@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();

	public ServletTelefone() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// excluir telefone
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {

				String idfone = request.getParameter("id");
				daoTelefoneRepository.deleteFone(Long.parseLong(idfone));

				String userpai = request.getParameter("userpai");
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(userpai));

				// para recarregar apos deletar
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);

				request.setAttribute("msg", "TELEFONE Excluido com sucesso");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

				return; // para n�o proseguir para baixo
			}

			// a tag <a> dispara um get
			String iduser = request.getParameter("iduser");

			if (iduser != null && !iduser.isEmpty()) {

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(iduser));

				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);

				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

			} else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response); // fluxo normal

			} // fim if else
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// fim doget

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String usuario_pai_id = request.getParameter("id");
			String numero = request.getParameter("numero");

			if (!daoTelefoneRepository.existeFone(numero, Long.valueOf(usuario_pai_id))) {

				ModelTelefone modelTelefone = new ModelTelefone();

				modelTelefone.setNumero(numero);
				modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioID(Long.parseLong(usuario_pai_id)));
				modelTelefone.setUsuario_cad_id(super.getUserLogadoObjet(request));

				daoTelefoneRepository.gravaTelefone(modelTelefone);

				request.setAttribute("msg", "TELEFONE salvo com sucesso!");

			} else {
				request.setAttribute("msg", "Telefone J� EXISTE!");
			}

			List<ModelTelefone> modelTelefones = daoTelefoneRepository.listFone(Long.parseLong(usuario_pai_id));

			// retorno
			ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(usuario_pai_id));// consulta o usuario pai

			request.setAttribute("modelLogin", modelLogin);
			request.setAttribute("modelTelefones", modelTelefones);
			request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}