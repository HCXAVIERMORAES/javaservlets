<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>receber nome por par�metro</title>
</head>
<body>
	<!-- receber nome por parametro de outra tela. Sempre que criar uma nova tela ou fazer uma modifica��o, deve-se
	parar o servidor e rodar de novo. No navegador adicionar "/nome_nova_tela.jsp" 
	Para passar o par�metro, na barra do navegador ap�s o .jsp digita-se: ?variavel = valor, ous eja, ?nome=Helton
	obs: se houver espa�o, por padr�o ser� subistituido por '%20'. Para pegar mais de uma par�metro usa-se o '&'como separador
	, isso por url.
	Para passar de uma tela para outra se passa por formul�rio, criado no index.jsp-->
	<%
		//para ceber par�metro
		String nome = request.getParameter("nome");
		//mostrar na tela
		out.println("Nome: "+nome);
		//novo par�metro
		String idade = request.getParameter("idade");		
		out.println("idade: "+idade);
	
	%>

</body>
</html>