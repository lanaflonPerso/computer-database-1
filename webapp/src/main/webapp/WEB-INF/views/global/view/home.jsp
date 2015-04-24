<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<!Doctype HTML>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"></jsp:include>
	<body>
		<div>
			<mylib:commonHead /> 
			<security:authorize access="hasRole('ROLE_SUPER_ADMIN')">
			<div class="col-md-2"></div>
				<div id="user-icone" class="col-md-4">
					<a href="/webapp/user/view/dashboard"><i class="fa fa-users home-icone user-home-icone"></i></a>
				</div>
			</security:authorize>
			<security:authorize access="!hasRole('ROLE_SUPER_ADMIN')">
			<div class="col-md-4"></div>
			</security:authorize>
			<div id="computer-icone">
				<a href="/webapp/computer/view/dashboard"><i class="fa fa-laptop home-icone computer-home-icone"></i></a>
			</div>
		</div>
		<script src="/webapp/js/jquery.min.js"></script>
		<script src="/webapp/js/bootstrap.min.js"></script>
	</body>
</html>