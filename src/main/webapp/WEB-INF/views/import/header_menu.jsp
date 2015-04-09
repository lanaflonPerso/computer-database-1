<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="dashboard"> Application - Computer
			Database </a>

		<div id="navbar" class="collapse navbar-collapse pull-right">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="#" class="dropdown-toggle "
					data-toggle="dropdown" role="button" aria-expanded="false"><spring:message
							code="button.language" /> <span class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="?language=en"><img src="fonts/uk.png" alt="uk flag" style="width:25px;height:20px"> English</a></li>
						<li><a href="?language=fr"><img src="fonts/fr.png" alt="fr flag" style="width:25px;height:20px"> Français</a></li>
					</ul></li>
			</ul>
		</div>

	</div>
</header>