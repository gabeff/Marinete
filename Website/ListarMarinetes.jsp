<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="br.com.marineteapp.dao.MarineteDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Marinete - Listar Marinetes</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="./js/jquery.barrating.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
<!-- Custom styles for this template -->
<link href="./css/general.css" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/fontawesome-stars.css">
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" data-expanded="false"
					data-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/Marinete/home.jsp">Marinete</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Marinetes</a></li>
					<li><a href="/#">Perfil</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<jsp:useBean id="marineteDAO"
		class="br.com.marineteapp.dao.MarineteDAO" />
	<div class="container">
		<div class="form-general">
			<h2 class="form-general-heading">Ranking - AL</h2>
			<c:forEach var="marinete" items="${marineteDAO.listarMarinetes()}">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-2">
								<b>${marinete.nome}</b><br />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">${marinete.idade} anos</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<script type="text/javascript">
									$(function() {
										$('#example${marinete.id}').barrating({
											theme : 'fontawesome-stars',
											initialRating : ${marinete.avaliacao}
										});
									});
								</script>
								<select id="example${marinete.id}">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
								</select>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>
</html>