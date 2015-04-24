<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<!DOCTYPE HTML>
<html>
	<jsp:include page="/WEB-INF/views/import/head.jsp"></jsp:include>
	<body>
		<mylib:commonHead colorMenu="computer-color"/>
		<section id="main">
			<div class="container">			
				<h1 id="homeTitle">${page.getNumberOfComputer()} <spring:message code="computer.found"/></h1>
				<div id="actions" class="form-horizontal">
					<div class="pull-left">
						<form:form id="searchForm" action="dashboard" method="GET"
							class="form-inline">					
							<input type="hidden" name="page" value="1" /> 
							<input type="hidden" name="size" value="${page.getSize()}" />
							<input type="search" id="searchbox" name="search" class="form-control"
								<c:if test="${page.getSearch() == null || page.getSearch() == \"\"}">
									placeholder="<spring:message code="search.name"/>" 
								</c:if>
								<c:if test="${page.getSearch() != null}">
									value="${page.getSearch()}" 
								</c:if> />
							<input type="submit" id="searchsubmit" value="<spring:message code="filter.by.name"/>"
								class="btn btn-primary" />
						</form:form>
					</div>
					<security:authorize access="hasRole('ROLE_ADMIN')">
						<div class="pull-right">
							<a class="btn btn-success" id="addComputer" href="add"><spring:message code="add.computer"/></a>
							<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="button.delete"/></a>
						</div>
					</security:authorize>
				</div>
			</div>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<form:form id="deleteForm" action="/webapp/computer/crud/delete" method="POST">
					<input type="hidden" name="selection" value="">
				</form:form>
			</security:authorize>
			<div class="container" style="margin-top: 10px;">
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
							<security:authorize access="hasRole('ROLE_ADMIN')">
								<th class="editMode" style="width: 60px; height: 22px;">
									<input type="checkbox" id="selectall" />
									<span style="vertical-align: top;">
									 	- <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
									 		<i class="fa fa-trash-o fa-lg"></i>
										</a>
									</span>
								</th>
							</security:authorize>
							<spring:message code="computer.name" var="computer_name"/>
							<mylib:dashboardTitle page="${page}" name="${computer_name}" sortColumn="COMPUTER_NAME" />
							<spring:message code="introduced.date" var="introduced_date"/>
							<mylib:dashboardTitle page="${page}" name="${introduced_date}" sortColumn="INTRODUCED_DATE" />
							<spring:message code="discontined.date" var="discontinued_date"/>
							<mylib:dashboardTitle page="${page}" name="${discontinued_date}" sortColumn="DISCONTINUED_DATE" />
							<spring:message code="company.name" var="company_name"/>
							<mylib:dashboardTitle page="${page}" name="${company_name}" sortColumn="COMPANY_NAME" />
						</tr>
					</thead>
					<!-- Browse attribute computers -->
					<tbody id="results">
						<c:forEach var="i" begin="0" end="${page.getComputers().size()}">
							<tr>
								<c:if test="${page.getComputers().size() > i}">
									<security:authorize access="hasRole('ROLE_ADMIN')">
										<td class="editMode">
											<input type="checkbox" name="cb" id="selected_${i}" class="cb" value="${page.getComputers().get(i).getId()}">
										</td>
									</security:authorize>
									<security:authorize access="hasRole('ROLE_ADMIN')">
										<td><a id="name_${i}" href="edit?computerId=${page.getComputers().get(i).getId()}" onclick="">${page.getComputers().get(i).getName()}</a></td>
									</security:authorize>
									<security:authorize access="!hasRole('ROLE_ADMIN')">
										<td>${page.getComputers().get(i).getName()}</td>
									</security:authorize>
									<td id="introduced_${i}">${page.getComputers().get(i).getIntroduced()}</td>
									<td id="discontinued_${i}">${page.getComputers().get(i).getDiscontinued()}</td>
									<td id="companyName_${i}">${page.getComputers().get(i).getCompanyName()}</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
		<footer class="navbar-fixed-bottom">
			<mylib:pagination page="${page}" />			
		</footer>
		<jsp:include page="/WEB-INF/views/import/footer.jsp"></jsp:include>
	</body>
</html>