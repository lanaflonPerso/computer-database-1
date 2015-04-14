<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<jsp:include page="/WEB-INF/views/import/header_css.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/import/header_menu.jsp"></jsp:include>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="add.computer" />
					</h1>
					<form:form modelAttribute="computerDto" action="addComputer"
						onsubmit="return checkPostForm(document)" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="computer.name" /></label>
								<input 	type="text"
										class="form-control"
										id="computerName"
										name="name"
									<c:if test="${page.getComputerDto().getName() != null}">
										value="${page.getComputerDto().getName()}"
									</c:if>
									placeholder="<spring:message code="computer.name"/>">
							</div>
							<c:if test="${!page.getCorrectField().isComputerNameTrue()}">
								<font id="serviceNameException" color="red"> <spring:message
										code="error.invalid.computer.name" />
								</font>
							</c:if>
							<div class="form-group">
								<label for="introduced"><spring:message
										code="introduced.date" /></label> <input type="text"
									class="form-control" id="introduced" name="introduced"
									<c:if test="${page.getComputerDto().getIntroduced() != null}">
										value="${page.getComputerDto().getIntroduced()}"
									</c:if>
									placeholder="<spring:message code="date.format"/>">
							</div>
							<c:if test="${!page.getCorrectField().isIntroducedDateTrue()}">
								<font id="serviceIntroducedException" color="red"> <spring:message
										code="error.invalid.introduced.date" /> <spring:message
										code="date.format" /></font>
							</c:if>
							<div class="form-group">
								<label for="discontinued"><spring:message
										code="discontined.date" /></label> <input type="text"
									class="form-control" id="discontinued" name="discontinued"
									<c:if test="${page.getComputerDto().getDiscontinued() != null}">
										value="${page.getComputerDto().getDiscontinued()}"
									</c:if>
									placeholder="<spring:message code="date.format"/>">
							</div>
							<c:if test="${!page.getCorrectField().isDiscontinuedDateTrue()}">
								<font id="serviceDiscontinuedException" color="red"> <spring:message
										code="error.invalid.discontined.date" /> <spring:message
										code="date.format" /></font>
							</c:if>
							<div class="form-group">
								<label for="companyId"><spring:message code="company" /></label>
								<select class="form-control" id="companyId" name="companyId">
									<option value=>--</option>
									<c:forEach var="i" begin="0"
										end="${page.getCompanies().size() - 1}">
										<option value="${page.getCompanies().get(i).getId()}">${page.getCompanies().get(i).getName()}</option>
									</c:forEach>
								</select>
							</div>
							<c:if test="${!page.getCorrectField().isCompanyIdTrue()}">
								<font color="red"> Invalid company id</font>
							</c:if>
						</fieldset>
						<div class="actions pull-right">
							<input id="addButton" type="submit"
								value="<spring:message code="button.add"/>"
								class="btn btn-primary">
							<spring:message code="or" />
							<a href="dashboard" class="btn btn-default"><spring:message
									code="button.cancel" /></a>
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