<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
			<div id="logout_div" class="collapse navbar-collapse pull-right">
				<a class="navbar-brand" href="/webapp/logout" >
					<font style="font-size:12px">
						<i class="fa fa-power-off"></i> Log out
					</font>
				</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse pull-right">
				<ul class="nav navbar-nav">
					<li class="dropdown">
					<a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-expanded="false">
						<spring:message code="flag" var="flag" />
							<img src="/webapp/fonts/flags/${flag}.png" alt="uk flag" style="width:25px;height:20px">
						<spring:message code="button.language" />
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="?language=en"><img src="/webapp/fonts/flags/uk.png" alt="uk flag" style="width:25px;height:20px"> English</a></li>
						<li><a href="?language=fr"><img src="/webapp/fonts/flags/fr.png" alt="fr flag" style="width:25px;height:20px"> Fran�ais</a></li>
					</ul></li>
				</ul>
			</div>
		</div>
	</header>