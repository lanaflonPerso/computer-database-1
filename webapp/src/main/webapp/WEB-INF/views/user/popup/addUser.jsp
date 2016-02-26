<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<a href="#x" class="overlay" id="addUser"></a>
<div class="popup">
    <form:form modelAttribute="userDetailDto" id="addUserForm" action="/webapp/user/crud/add" method="POST">
	    <div>
	    	<spring:message code="username" var="_username"/>
	        <form:input path="username" class="form-control" type="text" id="username" value="" placeholder="${_username}"/>
	    </div>
	    <div>
	    	<spring:message code="password" var="_password"/>
	        <form:input path="password" class="form-control" type="password" id="password" value="" placeholder="${_password}"/>
	    </div>
	    <spring:message code="create" var="_create"/>
	    <input class="btn btn-success pull-right" type="submit" value="${_create}"/>
	</form:form>
    <a class="close" href="#"></a>
</div>
