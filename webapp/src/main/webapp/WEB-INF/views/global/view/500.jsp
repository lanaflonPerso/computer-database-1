<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<!DOCTYPE html>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"/>
	<body>
		<mylib:commonHead />
		<section id="main">
			<div class="container">	
				<div class="alert alert-danger">
					<spring:message code="error.500" />
					<br/>
					${exception}
				</div>
			</div>
		</section>
		<jsp:include page="/WEB-INF/views/import/common_js_import.jsp"/>
	</body>
</html>