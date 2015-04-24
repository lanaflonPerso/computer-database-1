<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<!DOCTYPE HTML>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"></jsp:include>
	<body>
		<mylib:commonHead colorMenu="computer-color"/>
		<section id="main">
			<div class="container">
				<div class="row">
					<div class="col-xs-8 col-xs-offset-2 box">
						<h1>
							<spring:message code="add.computer" />
						</h1>
						<form:form modelAttribute="addComputerDto" action="/webapp/computer/crud/add" onsubmit="return checkPostForm(document)" method="POST">
							<fieldset>
								<div class="form-group">
									<spring:message code="computer.name" var="computer_name"/>
									<form:label path="name" for="computerName"><spring:message code="computer.name" /></form:label>
									<form:input onkeyup="checkName(document)" path="name" name="name" type="text" class="form-control" id="computerName" value="${page.getComputerDto().getName()}" placeholder="${computer_name}"></form:input>
									<form:errors path="name" id="serviceNameException" style="color:red"></form:errors>
								</div>
								<div id="nameError" class="alert alert-danger" role="alert"><spring:message code="error.invalid.computer.name"/></div>
								<div class="form-group">
									<spring:message code="date.format" var="date_format"/>
									<form:label path="introduced" for="introduced"><spring:message code="introduced.date" /></form:label>
									<form:input onkeyup="checkIntroduced(document)" path="introduced" name="introduced" type="text" class="form-control" id="introduced" value="${page.getComputerDto().getIntroduced()}" placeholder="${date_format}"></form:input>
									<form:errors path="introduced" id="serviceIntroducedException" style="color:red"></form:errors>
								</div>
								<div id="introducedError" class="alert alert-danger" role="alert"><spring:message code="error.invalid.introduced.date"/><spring:message code="date.format"/></div>
								<div class="form-group">
									<spring:message code="date.format" var="date_format"/>
									<form:label path="discontinued" for="discontinued"><spring:message code="discontined.date" /></form:label>
									<form:input	onkeyup="checkDiscontinued(document)" path="discontinued" name="discontinued" type="text" class="form-control" id="discontinued" value="${page.getComputerDto().getDiscontinued()}" placeholder="${date_format}"></form:input>
									<form:errors path="discontinued" id="serviceDiscontinuedException" style="color:red"></form:errors>
								</div>
								<div id="discontinuedError" class="alert alert-danger" role="alert"><spring:message code="error.invalid.discontined.date"/><spring:message code="date.format"/></div>
								<div class="form-group">
									<form:label path="companyId" for="companyId"><spring:message code="company" /></form:label>
									<form:select path="companyId" class="form-control" id="companyId" name="companyId">
										<option value=>--</option>
										<c:forEach var="i" begin="0" end="${page.getCompanies().size() - 1}">
											<option 
												<c:if test="${page.getCompanies().get(i).getId() == page.getComputerDto().getCompanyId()}">
													selected
												</c:if>
											value="${page.getCompanies().get(i).getId()}">${page.getCompanies().get(i).getName()}</option>
										</c:forEach>
									</form:select>
								</div>
							</fieldset>
							<div class="actions pull-right">
								<input id="addButton" type="submit"	value="<spring:message code="button.add"/>" class="btn btn-primary">
								<spring:message code="or" />
								<a href="/webapp/computer/view/dashboard" class="btn btn-default"><spring:message	code="button.cancel" /></a>
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