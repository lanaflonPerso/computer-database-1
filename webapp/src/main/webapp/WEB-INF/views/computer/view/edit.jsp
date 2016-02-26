<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<!DOCTYPE HTML>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"/>
	<body>
		<nav class="navbar navbar-inverse navbar-fixed-top navbar-default computer-color" role="navigation">
			<div class="container-fluid">
			    <mylib:menuSelection colorMenu="computer-color"/>
			    <div class="col-md-2 navbar-header">
					<ul class="nav navbar-nav">
						<li class="dropdown">
						<a href="#" class="dropdown-toggle white-color computer-color" data-toggle="dropdown" role="button" aria-expanded="false">
							<spring:message code="flag" var="flag" />
								<img src="/webapp/fonts/flags/${flag}.png" alt="uk flag" style="width:25px;height:20px">
							<spring:message code="button.language" />
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="?language=en&computerId=${page.getComputerDto().getId()}"><img src="${pageContext.request.contextPath}/fonts/flags/uk.png" alt="uk flag" style="width:25px;height:20px"> English</a></li>
							<li><a href="?language=fr&computerId=${page.getComputerDto().getId()}"><img src="${pageContext.request.contextPath}/fonts/flags/fr.png" alt="fr flag" style="width:25px;height:20px"> Français</a></li>
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
		<section id="main">
			<div class="container">
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2 box">
						<div id="computer_Id" class="label label-default pull-right">id:${page.getComputerDto().getId()}</div>
						<h1><spring:message	code="edit.computer" /></h1>
						<form:form modelAttribute="editComputerDto" action="/webapp/computer/crud/edit" method="POST" onsubmit="return checkPostForm(document)" >
							<input type="hidden" name="id" id="computerId" value="${page.getComputerDto().getId()}" />
							<fieldset>
								<div class="form-group">
									<spring:message code="computer.name" var="computer_name"/>
									<form:label path="name" for="computerName"><spring:message code="computer.name" /></form:label>
									<form:input onkeyup="checkName(document)" path="name" name="name" type="text" class="form-control" id="computerName" value="${page.getComputerDto().getName()}" placeholder="${date_format}"/>
									<form:errors path="name" id="serviceNameException" style="color:red"/>
								</div>
								<div id="nameError" class="alert alert-danger" role="alert"><spring:message code="error.invalid.computer.name"/></div>
								<div class="form-group">
									<spring:message code="date.format" var="date_format"/>
									<form:label path="introduced" for="introduced"><spring:message code="introduced.date" /></form:label>
									<form:input onkeyup="checkIntroduced(document)" path="introduced" name="introduced" type="text" class="form-control" id="introduced" value="${page.getComputerDto().getIntroduced()}" placeholder="${date_format}"/>
									<form:errors path="introduced" id="serviceIntroducedException" style="color:red"/>
								</div>
								<div id="introducedError" class="alert alert-danger" role="alert"><spring:message code="error.invalid.introduced.date"/> <spring:message code="date.format"/></div>
								<div class="form-group">
									<spring:message code="date.format" var="date_format"/>
									<form:label path="discontinued" for="discontinued"><spring:message code="discontinued.date" /></form:label>
									<form:input	onkeyup="checkDiscontinued(document)" path="discontinued" name="discontinued" type="text" class="form-control" id="discontinued" value="${page.getComputerDto().getDiscontinued()}" placeholder="${date_format}"/>
									<form:errors path="discontinued" id="serviceDiscontinuedException" style="color:red"/>
								</div>
								<div id="discontinuedError" class="alert alert-danger" role="alert"><spring:message code="error.invalid.discontinued.date"/> <spring:message code="date.format"/></div>
								<div class="form-group">
									<form:label path="companyId" for="companyId"><spring:message code="company" /></form:label>
									<form:select path="companyId" class="form-control" id="companyId" name="companyId">
									<option value=>--</option>
										<c:forEach var="i" begin="0" end="${page.getCompanies().size() - 1}">
											<option
												<c:if test="${page.getCompanies().get(i).getName() == page.getComputerDto().getCompanyName()}">
													selected
												</c:if>
												value="${page.getCompanies().get(i).getId()}">${page.getCompanies().get(i).getName()}
											</option>
										</c:forEach>
									</form:select>
								</div>
							</fieldset>
							<div class="actions pull-right">
								<input id="editButton" type="submit" value="<spring:message	code="button.edit" />" class="btn btn-primary"> 
								<spring:message code="or" />
								<a href="${pageContext.request.contextPath}/computer/view/dashboard" class="btn btn-default"><spring:message code="button.cancel" /></a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/views/import/validator_js.jsp"/>
		<jsp:include page="/WEB-INF/views/import/common_js_import.jsp"/>
	</body>
</html>