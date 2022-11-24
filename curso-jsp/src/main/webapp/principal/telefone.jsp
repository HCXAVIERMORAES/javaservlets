<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  	
 
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
									<!-- Page Body Start -->
									<div class="page-body">
										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs Card Start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cadastro de Telefone</h4>
														
														<!-- criando o forulario para cadastro do telefone -->
														<form class="form-material" action="<%= request.getContextPath() %>/ServletTelefone" method="post" id="formFone">
															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" class="form-control" readonly="readonly"
																 value="${modelLogin.id}">
																 <span class="form-bar"></span> 
																 <label class="float-label">ID User:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" readonly="readonly" name="nome" id="nome" class="form-control" required="required"
																	value="${modelLogin.nome}">
																	 <span class="form-bar"></span>
																	 <label class="float-label">Name:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero" class="form-control" required="required">
																	 <span class="form-bar"></span>
																	 <label class="float-label">Numero:</label>
															</div>
															
															<!-- botao de salvar -->
															<button class="btn btn-success btn-round waves-effect waves-light">Salvar</button>
															
														</form>
													</div>
												</div>
											</div>
										</div>
										
										<!-- mesagem de retorno -->
										<span id="msg">${msg}</span>
										
				<div style="height: 300px; overflow: scroll;">
					<table class="table" id="tabelaresultadosview">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th scope="col">Numero</th>
								<th scope="col">Excluir</th>
								
							</tr>
						</thead>
						<tbody> <!-- corpo da tabela -->
							<c:forEach items='${modelTelefones}' var='f'>
								<tr>
									<td><c:out value="${f.id }"></c:out></td>
									<td><c:out value="${f.numero }"></c:out></td> <!-- btn-outline-sucess -->
								    <td>
								    	<a class="btn btn-success btn-round btn-sm" href="<%= request.getContextPath() %>/ServletTelefone?acao=excluir&id=${f.id}&userpai=${modelLogin.id}">
								    		excl.
								    	</a>
								    </td>
								</tr>							
							</c:forEach>
						</tbody>
					</table>					
				</div>
										
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
	<!-- java script -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>
	
	<script type="text/javascript">
	/*Para aceitar apenas numero*/
	 $("#numero").keypress(function (event) {
	return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
	</script>
</body>

</html>
