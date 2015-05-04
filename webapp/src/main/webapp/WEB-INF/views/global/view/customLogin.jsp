<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<!Doctype HTML>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"></jsp:include>
	<link href="/webapp/css/signin.css" rel="stylesheet" media="screen">
	<body>
		<mylib:commonHead />
		<div id="login-box">
			<form class="login-form" name="f" action="/webapp/j_spring_security_check" method="POST">
				<spring:message code="password" var="password"/>
				<spring:message code="username" var="username"/>
				<h2 class="form-signin-heading"><spring:message code="please.log.in" /></h2>
				<div class="form-group input-group">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-user"></i>
					</span>
					<input type="text" name="username" id="username" class="form-control" placeholder="${username}" required autofocus>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon">
						<i class="glyphicon glyphicon-lock"></i>
					</span>
					<input type="password" name="password" id="password" class="form-control" placeholder="${password}" required autofocus>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<c:if test="${auth == false}">
					<div id="loginError" class="alert alert-danger" role="alert">
						<spring:message code="error.auth.fail" />
					</div>
				</c:if>
				<button id="login" class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="log.in" /></button>
			</form>
		</div>
		<script src="/webapp/js/jquery.min.js"></script>
		<script src="/webapp/js/bootstrap.min.js"></script>
	</body>
</html>
