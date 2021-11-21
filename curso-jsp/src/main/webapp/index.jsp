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
	Para interceptar os dados vindos da tela usa-se no form um metho=post-->
	<form action="ServletLogin" method="post">
		<label>nome</label>
		<input name="nome">
		<br><br>
		<label>Idade</label>
		<input name="idade">
		<br><br>
		<input type="submit" value="enviar" style="width:14%; border-radius: 5px; color: blue;">
	</form>
</body>
</html>