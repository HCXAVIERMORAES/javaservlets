<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>receber nome por parâmetro</title>
</head>
<body>
	<!-- receber nome por parametro de outra tela. Sempre que criar uma nova tela ou fazer uma modificação, deve-se
	parar o servidor e rodar de novo. No navegador adicionar "/nome_nova_tela.jsp" 
	Para passar o parâmetro, na barra do navegador após o .jsp digita-se: ?variavel = valor, ous eja, ?nome=Helton
	obs: se houver espaço, por padrão será subistituido por '%20'. Para pegar mais de uma parâmetro usa-se o '&'como separador
	, isso por url.
	Para passar de uma tela para outra se passa por formulário, criado no index.jsp-->
	<%
		//para ceber parâmetro
		String nome = request.getParameter("nome");
		//mostrar na tela
		out.println("Nome: "+nome);
		//novo parâmetro
		String idade = request.getParameter("idade");		
		out.println("idade: "+idade);
	
	%>

</body>
</html>