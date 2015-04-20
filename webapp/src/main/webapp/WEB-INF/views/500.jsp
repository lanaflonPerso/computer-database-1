<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"></jsp:include>
	<body>
		<jsp:include page="/WEB-INF/views/import/header_menu.jsp"></jsp:include>
		<section id="main">
			<div class="container">	
				<div class="alert alert-danger">
					<spring:message code="error.500" />
					<br/>
					${exception}
				</div>
			</div>
		</section>
	<jsp:include page="/WEB-INF/views/import/footer.jsp"></jsp:include>
	</body>
</html>