<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

			<!-- Barra de navega��o -->
			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<!-- menu da barra de navega��o -->
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
														<h5>Cadastro de Usu�rios</h5>
														<!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
														<span id="msg">${msg}</span>
													</div>
													<div class="card-block">

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="Post" id="formUser">
															<!-- usando um input q ficar� escondido e ser� enviado junto para o delete de dados -->
															<input type="hidden" name="acao" id="acao" value="">
															
															<div class="form-group form-default form-static-label"> <!-- form-static-label deixa o label parado -->
																<input type="text" name="id" id="id"
																	class="form-control" readonly=readonly
																	value="${modolLogin.id}"> <span
																	class="form-bar"></span> <label class="float-label">ID:
																</label>
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

															<!-- button Rounded -->

															<button type="button" class="btn btn-primary btn-round waves-effect waves-light" onclick="limparForm();">Novo</button>
															
															<button class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
															
															<button type="button" class="btn btn-danger btn-round waves-effect waves-light" onclick="criarDeleteComAjax()">Excluir</button>
														</form>
														<!-- fim form cadastro -->

													</div>
												</div>
											</div>
										</div>
										<!--fecha linha -row  -->
										
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
	
	<script type="text/javascript">
	
		function limparForm() {
			var elementos = document.getElementById('formUser').elements;	/*retorna os elemmentos de dentro do form*/
			for(p= 0; p < elementos.length; p++) {
				elementos[p].value ='';
				}
			}
		
		/* 1� maneira de fazer o delete de dados. tracar o nome da fuction no onClick do botao excluir */
		function criarDelete() {
			if(confirm('Deseja realmente EXCLUIR os dados?!')){
				var elementos = document.getElementById('formUser').method = 'get';	/*passa o metodod para get*/
				var elementos = document.getElementById('acao').value = 'deletar';
				var elementos = document.getElementById('formUser').submit(); //envia o formulario
			}
		}
		
		/*2� maneira de deletar dados, usando o ajax*/
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
					
				}).fail(function (xhr, status, errorThrown) {/*se houver algum erro - xhr � padrao -*/
					
					alert('Erro ao deletar usu�rio por id: ' + xhr.responseText);
					
				});
				
			}
			
		}
		
	
	</script>


</body>

</html>
