<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib" %>
<%@ attribute name="colorMenu" description="color for menu"%>

<nav class="navbar navbar-inverse navbar-fixed-top navbar-default ${colorMenu}" role="navigation">
	<div class="container-fluid">
		<mylib:menuSelection colorMenu="${colorMenu}" />
	    <div class="col-md-2 navbar-header">
			<ul class="nav navbar-nav">
				<li class="dropdown">
				<a href="#" class="dropdown-toggle white-color ${colorMenu}" data-toggle="dropdown" role="button" aria-expanded="false">
					<spring:message code="flag" var="flag" />
						<img src="/webapp/fonts/flags/${flag}.png" alt="uk flag" style="width:25px;height:20px">
					<spring:message code="button.language" />
					<span class="caret"></span>
				</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="?language=en"><img src="${pageContext.request.contextPath}/fonts/flags/uk.png" alt="uk flag" style="width:25px;height:20px"> English</a></li>
					<li><a href="?language=fr"><img src="${pageContext.request.contextPath}/fonts/flags/fr.png" alt="fr flag" style="width:25px;height:20px"> Français</a></li>
				</ul></li>
			</ul>
	    </div>    
	    <security:authorize access="isAuthenticated()">
		    <div class="col-md-1 navbar-header">
				<a class="white-color navbar-brand btn-logout" href="${pageContext.request.contextPath}/global/view/logout"><i class="fa fa-power-off"></i> <spring:message code="log.out"/></a>
		    </div> 
	    </security:authorize>
	</div>
</nav>