<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ attribute name="colorMenu" description="color for menu"%>

<nav class="navbar navbar-inverse navbar-fixed-top navbar-default ${colorMenu}" role="navigation">
	<div class="container-fluid">
	    <div class="col-md-5 navbar-header ">	
	     	<a class="navbar-brand white-color" href="/webapp"> Application computer database</a>
	    </div>
	    <div class="col-md-4 navbar-header">
		    <security:authorize access="isAuthenticated()">
		      	<ul class="nav navbar-nav">
			        <li class="dropdown">
				          <a href="#" class="dropdown-toggle white-color ${colorMenu}" data-toggle="dropdown" role="button" aria-expanded="false">Selection <span class="caret"></span></a>
				          <ul class="dropdown-menu dropdown-menu-right" role="menu">
				          		<security:authorize access="hasRole('ROLE_SUPER_ADMIN')">
					            	<li><a class="btn-warning btn-user" href="/webapp/user/view/dashboard"><spring:message code="user" /></a></li>
					            </security:authorize>
					            <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
					            	<li><a class="btn-warning btn-computer " href="/webapp/computer/view/dashboard"><spring:message code="computer" /></a></li>
					            </security:authorize>
				          		<security:authorize access="hasRole('ROLE_ADMIN')">
					            	<li><a class="btn-warning btn-company" href="/webapp/company/view/dashboard"><spring:message code="company" /></a></li>
					            </security:authorize>
				          </ul>
			        </li>
		      	</ul>
	      	</security:authorize>
		</div>	 
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
					<li><a href="?language=en"><img src="/webapp/fonts/flags/uk.png" alt="uk flag" style="width:25px;height:20px"> English</a></li>
					<li><a href="?language=fr"><img src="/webapp/fonts/flags/fr.png" alt="fr flag" style="width:25px;height:20px"> Français</a></li>
				</ul></li>
			</ul>
	    </div>    
	    <security:authorize access="isAuthenticated()">
		    <div class="col-md-1 navbar-header">
				<a class="white-color navbar-brand btn-logout" href="/webapp/global/view/logout"><i class="fa fa-power-off"></i> <spring:message code="log.out"/></a>
		    </div> 
	    </security:authorize>
	</div>
</nav>