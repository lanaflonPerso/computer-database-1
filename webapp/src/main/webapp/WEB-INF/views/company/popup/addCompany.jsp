<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<a href="#x" class="overlay" id="addCompany"></a>
<div class="popup">
    <form:form modelAttribute="companyDto" id="addCompanyForm" action="/webapp/company/crud/add" method="POST">
	    <div>
	    	<spring:message code="company.name" var="company_name"/>
	        <form:input path="name" class="form-control" type="text" placeholder="${company_name}"/>
	    </div>
	    <spring:message code="create" var="_create"/>
	    <input class="btn btn-success pull-right" type="submit" value="${_create}"/>
	</form:form>
    <a class="close" href="#"></a>
</div>
