<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="addComputer"
						onsubmit="return checkPostForm(document)" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" class="form-control" id="computerName"
									name="computerName"
									<c:if test="${page.getComputerDto().getName() != null}">
										value="${page.getComputerDto().getName()}"
									</c:if>
									placeholder="Computer name">
							</div>
							<c:if test="${!page.getCorrectField().isComputerNameTrue()}">
								<font id="serviceNameException" color="red"> Wrong
									computer name : computer name can not be empty</font>
							</c:if>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="text" class="form-control" id="introduced"
									name="introduced"
									<c:if test="${page.getComputerDto().getIntroduced() != null}">
										value="${page.getComputerDto().getIntroduced()}"
									</c:if>
									placeholder="yyyy-MM-dd hh:mm:ss">
							</div>
							<c:if test="${!page.getCorrectField().isIntroducedDateTrue()}">
								<font id="serviceIntroducedException" color="red">
									Invalid introduced date : respect yyyy-MM-dd HH:mm:ss</font>
							</c:if>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="text" class="form-control" id="discontinued"
									name="discontinued"
									<c:if test="${page.getComputerDto().getDiscontinued() != null}">
										value="${page.getComputerDto().getDiscontinued()}"
									</c:if>
									placeholder="yyyy-MM-dd hh:mm:ss">
							</div>
							<c:if test="${!page.getCorrectField().isDiscontinuedDateTrue()}">
								<font id="serviceDiscontinuedException" color="red">
									Invalid discontinued date : respect yyyy-MM-dd HH:mm:ss</font>
							</c:if>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
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
							<input id="addButton" type="submit" value="Add"
								class="btn btn-primary"> or <a href="dashboard"
								class="btn btn-default">Cancel</a>

						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
</body>
</html>