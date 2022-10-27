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
													<span>${msg}</span>
													</div>
													<div class="card-block">

														<form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="Post">
															<div class="form-group form-default">
																<input type="text" name="id" id="id"
																	class="form-control" readonly=readonly value="${modolLogin.id}"> 
																	<span class="form-bar"></span> <label class="float-label">ID: </label>
															</div>

															<div class="form-group form-default">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required" value="${modolLogin.nome}">
																	<span class="form-bar"></span> <label class="float-label">Name: </label>
															</div>
															
															<div class="form-group form-default">
																<input type="email" name="email" id="email"
																	class="form-control" autocomplete="off" required="required" value="${modolLogin.email}"> <span
																	class="form-bar"></span> <label class="float-label">Email:
																</label>
															</div>
															
															<div class="form-group form-default">
																<input type="text" name="login" id="login"
																	class="form-control" required="required" value="${modolLogin.login}">
																	<span class="form-bar"></span> <label class="float-label">Login: </label>
															</div>

															<div class="form-group form-default">
																<input type="password" name="senha" id="senha"
																	class="form-control" autocomplete="off" required="required" value="${modolLogin.senha}"> <span
																	class="form-bar"></span> <label class="float-label">Password:
																</label>
															</div>
															<!--  
															<div class="form-group form-default">
																<textarea class="form-control" required=""></textarea>
																<span class="form-bar"></span> <label
																	class="float-label">Anotações: </label>
															</div>
															-->
															<!-- button Rounded -->

															<button
																class="btn btn-primary btn-round waves-effect waves-light">Novo</button>
															<button
																class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
															<!--  	<button class="btn btn-info btn-round waves-effect waves-light">Excluir</button>-->
															<button
																class="btn btn-danger btn-round waves-effect waves-light">Excluir</button>
														</form> <!-- fim form cadastro -->
												</div>
											</div>
										</div>
									</div><!--fecha linha -row  -->
									
								</div>	
							</div>
							<!-- Page-body end -->
						</div>

						<div id="styleSelector"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	</div>
<div></div>
	
	<!-- java script -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>


</body>

</html>
<!-- retirado para colar o código do dashbord mega-able-life
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title> Tela Principal</title>
</head>
<body>
	<h1>Tela principal após o login</h1>
	
</body>
</html>
-->
