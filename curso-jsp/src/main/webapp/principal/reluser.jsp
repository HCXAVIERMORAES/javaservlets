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

									<!-- Page-body start -->
									<div class="page-body">

										<!-- copiado do form-elementes-component -->
										<div class="row">
											<!--  <div class="col-md-12">-->
											<div class="col-sm-12">
												<!-- Basic Form Inputs Card Start -->
												<div class="card">
													<div class="card-block">
														<h3 class="sub-title">Rel. Usuários</h3>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="get" id="formUser">
															<!-- manda o parametro escondido pelo hidden -->
															<input type="hidden" name="acao" value="imprimirRelatorioUser">

															<div class="form-row align-items-center">
															
																<div class="col-sm-3 my-1">
																	<label class="sr-only" for="dataInicial">Data Inicial</label>
																	<input type="text" class="form-control" id="dataInicial" name="dataInicial" value="${dataInicial}">
																</div>
																
																<div class="col-sm-3 my-1">
																	<label class="sr-only" for="dataFinal">Data Final</label>																													
																		<input type="text" class="form-control"	id="dataFinal" name="dataFinal" value="${dataFinal}">																	
																</div>
																
																<div class="col-auto my-1">
																<!-- 	<button type="submit" class="btn btn-primary mb-2">Imprimir</button> -->
																	<button type="submit" class="btn btn-primary btn-round waves-effect waves-light mb-2">Imp. Relatório</button>
																</div>
																
															</div>

														</form>
														<!-- pegando o listeUser -->
														<!-- scroll -->						
				<div style="height: 300px; overflow: scroll;">
					<table class="table" id="tabelaresultadosview">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th scope="col">Name</th>	
							</tr>
						</thead>
						<tbody> <!-- corpo da tabela -->
							<c:forEach items='${listaUser}' var='ml'>
								<tr>
									<td><c:out value="${ml.id }"></c:out></td>
									<td><c:out value="${ml.nome }"></c:out></td> 						    	
								</tr>
								<c:forEach items="${ml.telefones}" var="fone">
									<tr>
										<td style="font-size: 10px;"><c:out value="${fone.numero}"></c:out> </td>
									</tr>
								</c:forEach>							
							</c:forEach>
						</tbody>
					</table>					
				</div>

														<span id="msg">${msg}</span>
													</div>
												</div>
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
	</div>

	<!-- java script -->
	<jsp:include page="javascriptfile.jsp"></jsp:include>
<script type="text/javascript">
/*usando calendario Jquery para transformar os campos em datas*/
$( function() {
	  
	  $("#dataInicial").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );

$( function() {
	  
	  $("#dataFinal").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );




</script>

</body>

</html>

