<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tela de Erros do Sistema</title>
</head>
<body>

	<h1>Erro no sistema, entere em contato com o suporte do sistema</h1>
	<%
		out.print(request.getAttribute("msg")); //pegar a mesagem do console e mostrar na tela
	%>

</body>
</html>