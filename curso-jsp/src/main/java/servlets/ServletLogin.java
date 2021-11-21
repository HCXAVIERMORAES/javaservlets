	package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * O chamado controller são as servlets. Poderia ser ServletLoginController**/

@WebServlet("/ServletLogin")/*Mapeamento de URL que vem da tela*/	
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletLogin() {
        
    }

	/*Recebe os dados pela URL em parâmetros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/*Recebe os dados enviados por formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//teste se esta interceptando da tela
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("idade"));
		
	}

}
