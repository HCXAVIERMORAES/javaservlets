<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
	<title>Curso JSP</title>
	
	<style type="text/css">
		form{
			position: absolute;
			top: 40%;
			left: 33%;
			right: 33%
		}
		
		h5{
			position: absolute;
			top: 30%;
			left: 33%
		}
		
		.msg{
			position: absolute;
			top: 15%;
			left: 33%;
			font-size: 25Px;
			  color: #664d03;
    		  background-color: #fff3cd;
   			  border-color: #ffecb5;
		}
	
	
	</style>
	
</head>
<body>

	<h5>Cuso JDevTreinamentos - modulo 22 - JSP </h5>
	 
	<%
		//out.println("Testando o  jsp");
	%>
	
	<br>
	<!-- criando formulário para receber nome da tela 'receber-nome.jsp, onde o nme do input recebe o parametro, variavel.
	não pode haver espaço entre os dados do input 
	Para interceptar os dados vindos da tela usa-se no form um metho=post
	Para organizar a tela coloca-se dentro de uma tabela-->
	
	<form action="<%=request.getContextPath() %>/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
		<!-- url que veio do filtter. O hidden esconde e o '=' é para passar a url, imprimir o valor passado no html,
		 ele se pega no ServletLogin -->
		<input type="hidden" <%= request.getParameter("url") %>>		
			
			<div class="mb-3">
			    <label class="form-label">Login</label>
			    <input name="login" type="text" class="form-control" required="required">
			    <!-- validação Boostrap de aprovação -->
				<div class="valid-feedback">
	      			Ok!
	            </div>
	            <!-- validação Boostrap de não aprovação -->
	            <div class="invalid-feedback">
	      			Obrigatório!
	            </div>
	         </div>
	         <div class="mb-3">
				<label class="form-label">Senha</label>
				<input name="senha" type="password" class="form-control" required="required">	
				<div class="valid-feedback">
					Ok!
				</div>	
				<div class="invalid-feedback">
	      			Obrigatorio!
	            </div>	
			</div>
			 
			<input type="submit" value="Acessar" class="btn btn-primary">
			 
			<!-- botão acompanha a tela	 
			<button type="submit" class="btn btn-primary">Enviar</button>
			-->
			<!--  para usar botão pequeno
			<div class="col-12">
				<button type="submit" class="btn btn-primary">Enviar</button>
			</div>
		  	-->
			<!--  <div class="col-md-12"> </div> para lado a lado -->
	</form>
	<!-- informando a mensagem de erro do login padrão para pegar atributos ${atributo} -->
	<h5 class="msg">${msg}</h5>
	
	<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	
	<script type="text/javascript">
			// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function () {
		  'use strict'
		
		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  var forms = document.querySelectorAll('.needs-validation')
		
		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }
		
		        form.classList.add('was-validated')
		      }, false)
		    })
		})()

</script>
	
</body>
</html>