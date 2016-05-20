<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Marinete</title>

<!-- Bootstrap Core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="./css/scrolling-nav.css" rel="stylesheet">

<link href="./css/cover.css" rel="stylesheet">
</head>

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body id="home" data-spy="scroll" data-target=".navbar-fixed-top">
<section class="entenda-section" id="Home">
	<div class="site-wrapper" style="padding-top: 50px;">
		<div class="site-wrapper-inner">
			<div class="cover-container">
				<!-- Navigation -->
				<nav class="navbar navbar-inverse navbar-fixed-top"
					data-role="navigation">
					<div class="container">
						<div class="navbar-header page-scroll">
							<button type="button" class="navbar-toggle"
								data-toggle="collapse" data-target=".navbar-ex1-collapse">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a class="navbar-brand page-scroll" href="#home">Marinete</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse navbar-ex1-collapse">
							<ul class="nav navbar-nav">
								<!-- Hidden li included to remove active class from about link when scrolled up past about section -->
								<li><a class="page-scroll" href="#home">Home</a></li>
								<li><a class="page-scroll" href="#entenda">Entenda</a></li>
								<li><a class="page-scroll" href="#contato">Contato</a></li>
							</ul>
							<form class="navbar-form navbar-right" action="SinergiaServlet" method="post">
								<div class="form-group">
									<input type="text" name="login" placeholder="Login" class="form-control">
								</div>
								<div class="form-group">
									<input type="password" name="senha" placeholder="Senha" class="form-control">
								</div>
								<input type="hidden" name="acao" value="login">
								<button type="submit" class="btn btn-success">Logar</button>
							</form>
						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container -->
				</nav>

				<div class="inner cover">
					<h1 class="cover-heading">Marinete.</h1>
					<p class="lead">Sinergia é um app para android e ios para solicitar
					diaristas. Veja a lista das melhores diaristas da cidade, escolha a
					sua preferida e agende a data do serviço.</p>
					<p class="lead">
						<a href="#entenda" class="btn btn-lg btn-default page-scroll">Entenda
							melhor</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</section>

	<!-- Services Section -->
<!-- 	<section id="entenda" class="entenda-section"> -->
		<div id="entenda" class="container" id="Entenda" style="padding-top: 20%; padding-bottom: 20%">
			<div class="site-wrapper-inner">
				<div class="row">
					<div class="col-md-4">
						<h2>Escolha</h2>
						<p>Veja as melhores marinetes da cidade avaliadas de acordo
						com os usuários e escolha sua favorita.</p>
					</div>
					<div class="col-md-4">
						<h2>Agende</h2>
						<p>Agende o dia em que a Marinete irá realizar o serviço em
						sua residência com a maior comodidade e agilidade possível.</p>
					</div>
					<div class="col-md-4">
						<h2>Casa limpa</h2>
						<p>Aproveite sua casa limpa e avalie a Marinete escolhida
						de acordo com o serviço prestado, ajudando o resto da comunidade.</p>
					</div>
				</div>
			</div>
		</div>
<!-- 	</section> -->

	<!-- Contact Section -->
	<form id="contato" action="SinergiaServlet" method="post" class="contato-section">
		<div class="site-wrapper" id="Contato" style="padding-top: 50px;">
			<div class="site-wrapper-inner">
				<div class="cover-container">
					<div class="inner cover">
						<input name="nome" class="form-control" placeholder="Seu Nome"
							required><br /> <input type="email" name="email"
							class="form-control" placeholder="Seu E-mail" required><br />
						<textarea name="mensagem" class="form-control"
							placeholder="Envia sua mensagem, dúvida, sugestão..." required
							rows="15"></textarea>
						<input type="hidden" name="acao" value="enviarContato"><br/>
						<button class="btn btn-lg btn-primary btn-block" type="submit">Enviar</button>	
					</div>
				</div>
			</div>
		</div>
	</form>

	<!-- jQuery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="./js/bootstrap.min.js"></script>

	<!-- Scrolling Nav JavaScript -->
	<script src="./js/jquery.easing.min.js"></script>
	<script src="./js/scrolling-nav.js"></script>

</body>

</html>
