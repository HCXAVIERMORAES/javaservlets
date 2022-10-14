<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Curso JSP</title>
</head>
<body>

	<h1>Cuso JSP modulo 22 aula 22.5</h1>
	
	<%
		out.println("Testando o  jsp");
	%>
	<br>
	<!-- criando formulário para receber nome da tela 'receber-nome.jsp, onde o nme do input recebe o parametro, variavel.
	não pode haver espaço entre os dados do input 
	Para interceptar os dados vindos da tela usa-se no form um metho=post
	Para organizar a tela coloca-se dentro de uma tabela-->
	
	<form action="ServletLogin" method="post">
		<!-- url que veio do filtter. O hidden esconde e o '=' é para passar a url, imprimir o valor passado no html,
		 ele se pega no ServletLogin -->
		<input type="hidden" <%= request.getParameter("url") %>>		
	
		<table>
		<!-- tr em html  é linha e  td é coluna. label escreve na tela-->
			<tr>
			<!--	<td><label>nome</label></td>
				<td><input name="nome" type="text">-->
			  
				<td><label>Login</label></td>
				<td><input name="login" type="text"> </td>
			</tr>			
			<tr>
			<!--	<td><label>idade</label> </td>
				<td><input name="idade" type="text"></td>-->
			  
				<td><label>Senha</label> </td>
				<td><input name="senha" type="password"></td> 
			</tr>
			
			<tr>
				<td/>
				<td><input type="submit" value="enviar" style="width:40%; border-radius: 5px; color: blue;"></td>
			</tr>
		
		</table>
	</form>
	<!-- informando a mensagem de erro do login padrão para pegar atributos ${atributo} -->
	<h4>${msg}</h4>
</body>
</html>