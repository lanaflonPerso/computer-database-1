<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<%@ attribute name="page" 			required="true" type="java.lang.Long"	description="Current page"%>
<%@ attribute name="pageMax"		required="true" type="java.lang.Long"	description="Maximum page"%>
<%@ attribute name="size" 			required="true" type="java.lang.String"	description="Number of element for each page"%>
<%@ attribute name="search" 		required="true" type="java.lang.String"	description="Searched element"%>
<%@ attribute name="sortColumn" 	required="true" type="java.lang.String" description="Column to sort"%>
<%@ attribute name="sortDirection" 	required="true" type="java.lang.String" description="Direction for sorting"%>


<div class="container text-center">
	<ul class="pagination">
		<c:if test="${page != 1}">
			<li><a href="<mylib:link target="dashboard" page="${page-1}" size="${size}" search="${search}" sortColumn="${sortColumn}"/>" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:if test="${page-2 > 0}">
			<li><a id="switchPageMalus2"
				href="<mylib:link target="dashboard" page="${page-2}" size="${size}" search="${search}" sortColumn="${sortColumn}"/>">${page-2}</a></li>
		</c:if>
		<c:if test="${page-1 > 0}">
			<li><a id="switchPageMalus1"
				href="<mylib:link target="dashboard" page="${page-1}" size="${size}" search="${search}" sortColumn="${sortColumn}"/>">${page-1}</a></li>
		</c:if>
		<li><a id="switchCurrentPage" style="background:#D8D8D8" href="#">${page}</a></li>
		<c:if test="${page+1 <= pageMax}">
			<li>
			<a id="switchPageAdd1" href="<mylib:link target="dashboard" page="${page+1}" size="${size}" search="${search}" sortColumn="${sortColumn}"/>">${page+1}</a>
			</li>
		</c:if>
		<c:if test="${page+2 <= pageMax}">
			<li><a id="switchPageAdd2"
				href="<mylib:link target="dashboard" page="${page+2}" size="${size}" search="${search}" sortColumn="${sortColumn}"/>">${page+2}</a></li>
		</c:if>

		<c:if test="${page != pageMax}">
			<li><a href="<mylib:link target="dashboard" page="${page+1}" size="${size}" search="${search}" sortColumn="${sortColumn}"/>" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>

	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default" 
			<c:if test="${size == 10}">	style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?size=10&sortColumn=${sortColumn}&sortDirection=${sortDirection}'">10</button>
		<button type="button" class="btn btn-default"
			<c:if test="${size == 50}">	style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?size=50&sortColumn=${sortColumn}&sortDirection=${sortDirection}'">50</button>
		<button type="button" class="btn btn-default"
			<c:if test="${size == 100}">style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?size=100&sortColumn=${sortColumn}&sortDirection=${sortDirection}'">100</button>
	</div>
</div>