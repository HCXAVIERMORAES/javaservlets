<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

<body>

	<!-- Pre-loader start -->
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<!-- Barra de navegação -->
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- menu da barra de navegação -->
					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">

						<!-- Page-header start - Dashboard da tela principal -->
						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->

						<div class="pcoded-inner-content">

							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">

									<!-- Page-body start -->
									<div class="page-body">

										<!-- copiado do form-elementes-component -->
										<div class="row">
											<div class="col-md-10">
												<div class="card">
													<div class="card-header">
														<h5>Cadastro de Usuários</h5>
														<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
														<span id="msg">${msg}</span>
													</div>
													<div class="card-block">

														 <form class="form-material" enctype="multipart/form-data" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser" >	
															<!-- usando um input q ficará escondido e será enviado junto para o delete de dados -->
															<input type="hidden" name="acao" id="acao" value="">
															
															<div class="form-group form-default form-static-label"> <!-- form-static-label deixa o label parado -->
																<input type="text" name="id" id="id"
																	class="form-control" readonly=readonly
																	value="${modolLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:
																</label>
															</div>
															
															<!-- campo para a foto -->
															<div class="form-group form-default input-group	mb-4">
																<div class="input-group-prepend">
																	<c:if test="${modolLogin.fotouser != '' && modolLogin.fotouser != null}">
																		<a href="<%= request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${modolLogin.id}">
																			<img alt="Imagem User" id="fotoembase64" src="${modolLogin.fotouser }" width="70px">
																		</a>
																	</c:if>
																	<c:if test="${modolLogin.fotouser == '' || modolLogin.fotouser == null}" > <!-- imagem padrao-->
																		<img alt="Imagem User" id="fotoembase64" src="assets/images/user.png" width="70px">
																	</c:if>
																	
																</div>													
																<input type="file" id="fileFoto" name="fileFoto" accept="image/*" onchange="visualizarImg('fotoembase64', 'fileFoto');" class="form-control-file" style="margin-top: 15px; margin-left: 5px;">
															</div>

															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${modolLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Name:
																</label>
															</div>

															<div class="form-group form-default">
																<input type="email" name="email" id="email"
																	class="form-control" autocomplete="off"
																	required="required" value="${modolLogin.email}">
																<span class="form-bar"></span> <label
																	class="float-label">Email: </label>
															</div>
															
															<!-- colocando um select para escolha de opçao de tipo de usuario -->
															<div class="form-group form-default form-static-label">
																<select class="form-control"
																	aria-label="Default select example" name="perfil">
																	<option disabled="disabled">[Selecione o perfil]</option>
																	<!-- para aparecer quando estiver selecionado na tela -->
																	<option value="ADMIN" 
																		<% ModelLogin modelLogin = (ModelLogin) request.getAttribute("modolLogin");
																		if(modelLogin != null && modelLogin.getPerfil().equals("ADMIN")){
																		out.print(" ");
																		out.print("selected=\"selected\"");
																		out.print(" ");} %> 
																	>Admin</option>
																	
																	<option value="SECRETARIA"
																		<% modelLogin = (ModelLogin) request.getAttribute("modolLogin");
																		if(modelLogin != null && modelLogin.getPerfil().equals("SECRETARIA")){
																		out.print(" ");
																		out.print("selected=\"selected\"");
																		out.print(" ");}%>																	
																	>Secretária</option>
																	<option value="AUXILIAR" 
																		<% modelLogin = (ModelLogin) request.getAttribute("modolLogin");
																		if(modelLogin != null && modelLogin.getPerfil().equals("AUXILIAR")){
																		out.print(" ");
																		out.print("selected=\"selected\"");
																		out.print(" ");}%>
																	>Auxiliar</option>
																</select>
																<span class="form-bar"></span> <label
																	class="float-label">Perfil: </label>
															</div>
															

															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	value="${modolLogin.login}"> <span
																	class="form-bar"></span> <label class="float-label">Login:
																</label>
															</div>

															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"
																	class="form-control" autocomplete="off"
																	required="required" value="${modolLogin.senha}">
																<span class="form-bar"></span> <label
																	class="float-label">Password: </label>
															</div>
															
															<!-- radio buton de sexo -->
															<div class="form-group form-default form-static-label">
																 <input type="radio" name="sexo" checked="checked" value="MASCULINO"
																 	<% modelLogin = (ModelLogin) request.getAttribute("modolLogin");
																		if(modelLogin != null && modelLogin.getSexo().equals("MASCULINO")){
																		out.print(" ");
																		out.print("checked=\"checked\"");
																		out.print(" ");}%>																 
																 >Masculino</>
																 <input type="radio" name="sexo" value="FEMININO"
																 	<% modelLogin = (ModelLogin) request.getAttribute("modolLogin");
																		if(modelLogin != null && modelLogin.getSexo().equals("FEMININO")){
																		out.print(" ");
																		out.print("checked=\"checked\"");
																		out.print(" ");}%>
																 >Feminino</>
															</div>

															<!-- button Rounded -->

															<button type="button" class="btn btn-primary btn-round waves-effect waves-light" onclick="limparForm();">Novo</button>
															
															<button class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
															
															<button type="button" class="btn btn-danger btn-round waves-effect waves-light" onclick="criarDeleteComAjax()">Excluir</button>
															<!-- botão do modal  Button trigger modal-->															
															<button type="button" class="btn btn-secondary btn-round" data-toggle="modal" data-target="#exampleModalUsuario">Pesquisar</button>
														
														</form>
														<!-- fim form cadastro -->

													</div>
												</div>
											</div>
										</div>	<!--fecha linha -row  -->
										
										<div style="height: 300px; overflow: scroll;">
					<table class="table" id="tabelaresultadosview">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th scope="col">Name</th>
								<th scope="col">Select</th>
								
							</tr>
						</thead>
						<tbody> <!-- corpo da tabela -->
							<c:forEach items="${modelLogins}" var="ml">
								<tr>
									<td><c:out value="${ml.id }"></c:out></td>
									<td><c:out value="${ml.nome }"></c:out></td> <!-- btn-outline-sucess -->
								<!-- <td><button class="btn btn-success btn-round btn-sm" type="button">sel.</button></td>  trocar por -->
								    <td>
								    	<a class="btn btn-success btn-round btn-sm" href="<%= request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id}">
								    		sel.
								    	</a>
								    </td>
								</tr>
							
							</c:forEach>
						</tbody>
					</table>
					
				</div>
										
									</div>
								</div>

							</div>
						</div>
						<!-- Page-body end -->

						<div id="styleSelector"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="javascriptfile.jsp"></jsp:include>
	
	<!-- o modal deve ficar fora da estrutura da pagina e antes do scrip ou antes do body -->
	<!-- Modal -->
<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisa de Usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">

					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Name" aria-label="nome" id="nomeBusca" aria-describedby="basic-addon2">
						<div class="input-group-append">
							<button class="btn btn-success" type="button" onclick="buscarUsuario();">Buscar</button>
						</div>
					</div>
					
				<div style="height: 300px; overflow: scroll;">
					<table class="table" id="tabelaresultados">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th scope="col">Name</th>
								<th scope="col">Select</th>
								
							</tr>
						</thead>
						<tbody>
							
						</tbody>
					</table>					
				</div>
				
				<span id="totalresultados"></span>

				</div> <!-- fim modal-body -->
				
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary btn-round" data-dismiss="modal">Fechar</button>
      <!--    <button type="button" class="btn btn-primary">Save changes</button> -->
      </div>
    </div>
  </div>
</div>
	
<script type="text/javascript">

/*javascipt para adicionar foto por upload
 * função para o onchange da imagem, q recebe 2 parametros
 */
 function visualizarImg(fotoembase64, filefoto) {
	    
	    
	    var preview = document.getElementById(fotoembase64); // campo IMG html
	    var fileUser = document.getElementById(filefoto).files[0];
	    var reader = new FileReader();
	    
	    reader.onloadend = function (){
		    preview.src = reader.result; /*Carrega a foto na tela*/
	    };
	    
	    if (fileUser) {
		  reader.readAsDataURL(fileUser); /*Preview da imagem*/
	    }else {
		 preview.src=  '';
	    }
	    
	}
	
		/*funções do modal, metod debusca por ajax, modal não pode haver redirecionamento de tela*/
		function buscarUsuario() {
			
			var nomeBusca = document.getElementById('nomeBusca').value;
			
			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {/*validando q tem q ter valor para buscar no banco. .trim() retira espaços vazios*/
				
				var urlAction = document.getElementById('formUser').action;	/*pega o action do form*/
				
				$.ajax({
					method : "get",
					url : urlAction,
					data : "nomeBusca=" + nomeBusca +'&acao=buscarUserAjax',
					success : function (response) {
						//alert(response);
						var json = JSON.parse(response);
						
						$('#tabelaresultados > tbody > tr').remove(); //remove linhas  de pesquisa anterior
						
						for (var p = 0; p < json.length; p++) {
							$('#tabelaresultados > tbody').append('<tr> <td>'+json[p].id+
									'</td><td>'+json[p].nome+
									'</td><td> <button onclick="verEditar('+json[p].id+')"  type="button" class="btn btn-round btn-outline-info btn-sm">Selct</button> </td></tr>');
						}
						
						document.getElementById('totalresultados').textContent= 'resultados: '+json.length;
						
					}
					
				}).fail(function (xhr, status, errorThrown) {/*se houver algum erro - xhr é padrao -*/
					
					alert('Erro ao buscar usuário por nome' + xhr.responseText);
					
				});	
				
			}
		} /*fim fuction modal*/
		
		/*função para pegar o id da pesquisa dentro do modal*/
	function verEditar(id) {
			//alert(id); 
		var urlAction = document.getElementById('formUser').action;
		window.location.href = urlAction + '?acao=buscarEditar&id='+id; /*faz um get*/
		
	}
		
		
		function limparForm() {
			var elementos = document.getElementById('formUser').elements;	/*retorna os elemmentos de dentro do form*/
			for(p= 0; p < elementos.length; p++) {
				elementos[p].value ='';
				}
			}
		
		/* 1ª maneira de fazer o delete de dados. tracar o nome da fuction no onClick do botao excluir */
		function criarDelete() {
			if(confirm('Deseja realmente EXCLUIR os dados?!')){
				var elementos = document.getElementById('formUser').method = 'get';	/*passa o metodod para get*/
				var elementos = document.getElementById('acao').value = 'deletar';
				var elementos = document.getElementById('formUser').submit(); //envia o formulario
			}
		}
		
		/*2ª maneira de deletar dados, usando o ajax*/
		function criarDeleteComAjax() {
			if(confirm('Deseja realmente EXCLUIR os dados?!')){
				
				var urlAction = document.getElementById('formUser').action;	/*pega o action do form*/
				var idUser = document.getElementById('id').value; /*id do input escondido, acao*/								
				
				$.ajax({
					method : "get",
					url : urlAction,
					data : "id=" + idUser +'&acao=deletarajax',
					success : function (response) {
						limparForm();
						/*alert(response); ou*/
						document.getElementById('msg').textContent = response;
					}
					
				}).fail(function (xhr, status, errorThrown) {/*se houver algum erro - xhr é padrao -*/
					
					alert('Erro ao deletar usuário por id: ' + xhr.responseText);
					
				});				
			}			
		}
		
		


</script>


</body>

</html>

