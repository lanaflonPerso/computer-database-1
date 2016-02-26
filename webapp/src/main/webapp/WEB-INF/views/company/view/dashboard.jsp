<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>


<!DOCTYPE html>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"/>
	<body>
		<mylib:commonHead colorMenu="company-color"/>
	    <section id="main">
	        <div class="container">
	            <h1 id="homeTitle"> ${page.getCompanyList().size()} <spring:message code="company.found"/> </h1>
	            <div id="actions" class="form-horizontal">
	                <div class="pull-right">
	                    <a class="btn btn-success" id="addComputer" href="#addCompany"><spring:message code="add.company"/></a> 
	                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="button.delete"/></a>
	                </div>
	            </div>
	        </div>
	        <form:form id="deleteForm" action="/webapp/company/crud/delete" method="POST">
	            <input type="hidden" name="selection" value="">
	        </form:form>
	        <div class="container" style="margin-top: 10px;">
	            <table class="table table-striped table-bordered">
	                <thead>
	                    <tr>
							<th class="editMode" style="width: 60px; height: 22px;">
	                            <input type="checkbox" id="selectall" /> 
	                            <span style="vertical-align: top;">
	                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
	                                        <i class="fa fa-trash-o fa-lg"></i>
	                                    </a>
	                            </span>
	                        </th>
	                        <th><spring:message code="company.name"/></th>
	                    </tr>
	                </thead>
	                <tbody id="list">
	                	<c:forEach var="i" begin="0" end="${page.getCompanyList().size() - 1}">
		                    <tr>
								<td class="editMode"><input type="checkbox" name="cb" class="cb" value="${page.getCompanyList().get(i).getId()}"></td>
								<c:set var="nameForInput" value="tmp_${page.getCompanyList().get(i).getId()}_1548664" />
								<td class="hidden">
									<input id="${nameForInput}" value="${nameForInput}"/>
									<input id="${nameForInput}_companyId" value="${page.getCompanyList().get(i).getId()}"/>
									<input id="${nameForInput}_companyName" value="${page.getCompanyList().get(i).getName()}"/>
								</td>
		                        <td><a id="editCompany_${i}" href="#editCompany" onclick="save(${nameForInput})">${page.getCompanyList().get(i).getName()}</a> </td>
		                    </tr>
						</c:forEach>               
	                </tbody>
	            </table>
	        </div>       
	    </section>    
	    <!-- Popup -->
	   	<jsp:include page="/WEB-INF/views/company/popup/addCompany.jsp"/>
	   	<jsp:include page="/WEB-INF/views/company/popup/editCompany.jsp"/>
	   	<!-- Javascript -->
		<jsp:include page="/WEB-INF/views/import/common_js_import.jsp"/>
		<script src="${pageContext.request.contextPath}/js/company.js"></script>
		<script type="text/javascript">
			var button_view = "<spring:message code='button.cancel'/>";
			var button_edit = "<spring:message code='button.delete'/>";
			var alert_message = "<spring:message code='delete.company.message'/>";
		</script>
		<script src="${pageContext.request.contextPath}/js/dashboard.js"></script>
	</body>
</html>
