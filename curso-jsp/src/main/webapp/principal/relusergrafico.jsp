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
											<!--  <div class="col-md-12">-->
											<div class="col-sm-12">
												<!-- Basic Form Inputs Card Start -->
												<div class="card">
													<div class="card-block">
														<h3 class="sub-title">Rel. Usu�rios</h3>

														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="get" id="formUser">
															<!-- manda o parametro escondido pelo hidden -->
															<input type="hidden" id="acaoRelatorioImprimirTipo" name="acao" value="imprimirRelatorioUser">

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
																	<button type="button" onclick="gerarGrafico();" class="btn btn-primary btn-round waves-effect waves-light mb-2">Gerar Graf.</button>																	
																</div>
																
															</div>

														</form>
														<!-- pegando o listeUser -->
														<!-- scroll grafico-->						
														<div style="height: 500px; overflow: scroll;">
															<div>
																<canvas id="myChart"></canvas>
															</div>
														</div>

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
	
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		
<script type="text/javascript">

/*fun��o de gerar grafico*/
var myChart = new Chart(document.getElementById('myChart'));

function gerarGrafico() {    
    
     var urlAction = document.getElementById('formUser').action;
     var dataInicial = document.getElementById('dataInicial').value;
     var dataFinal = document.getElementById('dataFinal').value;
     
	 $.ajax({
	     
	     method: "get",
	     url : urlAction,
	     data : "dataInicial=" + dataInicial + '&dataFinal=' + dataFinal + '&acao=graficoSalario',
	     success: function (response) {
		 
		    var json = JSON.parse(response);
		    
		    myChart.destroy();
		
		    myChart = new Chart(
			    document.getElementById('myChart'),
			    {
				  type: 'line',
				  data: {
				      labels: json.perfils,
				      datasets: [{
				        label: 'Gr�fico de m�dia salarial por tipo',
				        backgroundColor: 'rgb(255, 99, 132)',
				        borderColor: 'rgb(255, 99, 132)',
				        data: json.salarios,
				      }]
				    },
				  options: {}
				}
			);
		  
	     }
	     
	 }).fail(function(xhr, status, errorThrown){
	    alert('Erro ao buscar dados para o grafico ' + xhr.responseText);
	 });
	 
}

/*exemplo do site https://www.chartjs.org/docs/latest/getting-started/
const ctx = document.getElementById('myChart');

  new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
      datasets: [{
        label: '# of Votes',
        data: [12, 19, 3, 5, 2, 3],
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
*/
/*usando calendario Jquery para transformar os campos em datas*/
$( function() {
	  
	  $("#dataInicial").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Pr�ximo',
		    prevText: 'Anterior'
		});
} );

$( function() {
	  
	  $("#dataFinal").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','S�bado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','S�b','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Pr�ximo',
		    prevText: 'Anterior'
		});
} );




</script>

</body>

</html>

