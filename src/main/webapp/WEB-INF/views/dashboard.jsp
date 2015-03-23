<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${numberOfComputer} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
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
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach var="i" begin="0" end="${computers.size()}">
						<tr>
							<c:if test="${computers.size() > i}">
								<td class="editMode"><input type="checkbox" name="cb" id="selected_${i}"
									class="cb" value="0"></td>
								<td>
									<a id="name_${i}" href="editComputer?computerId=${computers.get(i).getId()}" onclick="">${computers.get(i).getName()}</a>
								</td>
								<td id="introduced_${i}">${computers.get(i).getIntroduced()}</td>
								<td>${computers.get(i).getDiscontinued()}</td>
								<td id="companyName_${i}">${computers.get(i).getCompany().getName()}</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:if test="${page != 1}">
					<li><a href="dashboard?page=${page-1}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>

				<c:if test="${page-2 > 0}">
					<li><a id="switchPageMalus2" href="dashboard?page=${page-2}&size=${size}">${page-2}</a></li>
				</c:if>
				<c:if test="${page-1 > 0}">
					<li><a id="switchPageMalus1" href="dashboard?page=${page-1}&size=${size}">${page-1}</a></li>
				</c:if>
				<li><a id="switchCurrentPage" href="#">${page}</a></li>
				<c:if test="${page+1 <= pageMax}">
					<li><a id="switchPageAdd1" href="dashboard?page=${page+1}&size=${size}">${page+1}</a></li>
				</c:if>
				<c:if test="${page+2 <= pageMax}">
					<li><a id="switchPageAdd2" href="dashboard?page=${page+2}&size=${size}">${page+2}</a></li>
				</c:if>

				<c:if test="${page != pageMax}">
					<li><a href="dashboard?page=${page+1}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default" onclick="document.location.href='dashboard?size=10'">10</button>
				<button type="button" class="btn btn-default" onclick="document.location.href='dashboard?size=50'">50</button>
				<button type="button" class="btn btn-default" onclick="document.location.href='dashboard?size=100'">100</button>
			</div>
		</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>