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
										<div class="row">
									
										<h1>Cadastro de Usuário</h1>

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
