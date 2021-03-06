<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<a href="#x" class="overlay" id="resetPassword"></a>
<div class="popup">
    <form:form modelAttribute="userDetailDto" id="addUserForm" action="/webapp/user/crud/resetPassword" method="POST">
	    <div>
	        <form:input id="usernameResetPassword" path="username" class="hidden form-control" type="text" value=""/>
	    </div>
	    <div>
	    	<spring:message code="password" var="_password"/>
	        <form:input path="password" class="form-control" type="password" id="password" value="" placeholder="${_password}"/>
	    </div>
	    <spring:message code="reset.password" var="_reset"/>
	    <input class="btn btn-success pull-right" onclick="setUsername()" type="submit" value="${_reset}"/>
	</form:form>
    <a class="close" href="#"></a>
</div>
