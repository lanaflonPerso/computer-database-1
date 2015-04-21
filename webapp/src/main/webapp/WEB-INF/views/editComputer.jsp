<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"></jsp:include>
	<body>
		<header class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
				<div id="logout_div" class="collapse navbar-collapse pull-right">
					<a class="navbar-brand" href="logout" >
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
								<li><a href="?language=en&computerId=${page.getComputerDto().getId()}"><img src="fonts/uk.png" alt="uk flag" style="width:25px;height:20px"> English</a></li>
								<li><a href="?language=fr&computerId=${page.getComputerDto().getId()}"><img src="fonts/fr.png" alt="fr flag" style="width:25px;height:20px"> Français</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</header>
		<section id="main">
			<div class="container">
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2 box">
						<div id="computer_Id" class="label label-default pull-right">id:${page.getComputerDto().getId()}</div>
						<h1><spring:message	code="edit.computer" /></h1>
						<form:form modelAttribute="editComputerDto" action="computer/edit" method="POST"	onsubmit="return checkPostForm(document)" >
							<input type="hidden" name="id" id="computerId" value="${page.getComputerDto().getId()}" />
							<fieldset>
								<div class="form-group">
									<spring:message code="computer.name" var="computer_name"/>
									<form:label path="name" for="computerName"><spring:message code="computer.name" /></form:label>
									<form:input path="name" name="name" type="text" class="form-control" id="computerName" value="${page.getComputerDto().getName()}" placeholder="${date_format}"></form:input>
									<form:errors path="name" id="serviceNameException" style="color:red"></form:errors>
								</div>
								<div class="form-group">
									<spring:message code="date.format" var="date_format"/>
									<form:label path="introduced" for="introduced"><spring:message code="introduced.date" /></form:label>
									<form:input path="introduced" name="introduced" type="text" class="form-control" id="introduced" value="${page.getComputerDto().getIntroduced()}" placeholder="${date_format}"></form:input>
									<form:errors path="introduced" id="serviceIntroducedException" style="color:red"></form:errors>
								</div>
								<div class="form-group">
									<spring:message code="date.format" var="date_format"/>
									<form:label path="discontinued" for="discontinued"><spring:message code="discontined.date" /></form:label>
									<form:input	path="discontinued" name="discontinued" type="text" class="form-control" id="discontinued" value="${page.getComputerDto().getDiscontinued()}" placeholder="${date_format}"></form:input>
									<form:errors path="discontinued" id="serviceDiscontinuedException" style="color:red"></form:errors>
								</div>
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
								<a href="dashboard" class="btn btn-default">
								<spring:message code="button.cancel" /></a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/views/import/validator_js.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/import/footer.jsp"></jsp:include>
	</body>
</html>