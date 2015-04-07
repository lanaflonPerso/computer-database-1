<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${page.getNumberOfComputer()} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="dashboard" method="GET"
						class="form-inline">
						<input type="hidden" name="page" value="1" /> <input
							type="hidden" name="size" value="${page.getSize()}" /> <input
							type="search" id="searchbox" name="search" class="form-control"
							<c:if test="${page.getSearch() == null || page.getSearch() == \"\"}">
								placeholder="Search name" 
							</c:if>
							<c:if test="${page.getSearch() != null}">
								value="${page.getSearch()}" 
							</c:if> />
						<input type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<mylib:dashboardTitle page="${page}" name="Computer name"
							sortColumn="COMPUTER_NAME" />
						<mylib:dashboardTitle page="${page}" name="Introduced date"
							sortColumn="INTRODUCED_DATE" />
						<mylib:dashboardTitle page="${page}" name="Discontinued date"
							sortColumn="DISCONTINUED_DATE" />
						<mylib:dashboardTitle page="${page}" name="Company Name"
							sortColumn="COMPANY_NAME" />
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="i" begin="0" end="${page.getComputers().size()}">
						<tr>
							<c:if test="${page.getComputers().size() > i}">
								<td class="editMode"><input type="checkbox" name="cb"
									id="selected_${i}" class="cb" value="${i}"></td>
								<td><a id="name_${i}"
									href="editComputer?computerId=${page.getComputers().get(i).getId()}"
									onclick="">${page.getComputers().get(i).getName()}</a></td>
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

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>


</body>
</html>