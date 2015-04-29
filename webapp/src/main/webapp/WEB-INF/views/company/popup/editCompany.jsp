<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<a href="#x" class="overlay" id="editCompany"></a>
<div class="popup">
    <form:form modelAttribute="companyDto" id="editCompanyForm" action="/webapp/company/crud/edit" method="POST">
	    <div>
	        <form:input id="companyId" path="id" class="hidden form-control" type="text" value=""/>
	    </div>
	    <div>
	        <form:input id="companyName" path="name" class="form-control" value=""/>
	    </div>
	    <spring:message code="button.edit" var="button_edit"/>
	    <input class="btn btn-success pull-right" type="submit" value="${button_edit}"/>
	</form:form>
    <a class="close" href="#"></a>
</div>
