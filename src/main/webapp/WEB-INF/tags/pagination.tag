<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<%@ attribute name="page" required="true" type="com.excilys.computerdatabase.dto.page.DashboardPage" description="Contains all the information of the page"%>


<div class="container text-center">
	<ul class="pagination">
		<c:if test="${page.getPage() != 1}">
			<li><a href="<mylib:link target="dashboard" page="${page.getPage()-1}" size="${page.getSize()}" search="${page.getSearch()}" sortColumn="${page.getSortColumn()}"/>" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>
		<c:if test="${page.getPage()-2 > 0}">
			<li><a id="switchPageMalus2"
				href="<mylib:link target="dashboard" page="${page.getPage()-2}" size="${page.getSize()}" search="${page.getSearch()}" sortColumn="${page.getSortColumn()}"/>">${page.getPage()-2}</a></li>
		</c:if>
		<c:if test="${page.getPage()-1 > 0}">
			<li><a id="switchPageMalus1"
				href="<mylib:link target="dashboard" page="${page.getPage()-1}" size="${page.getSize()}" search="${page.getSearch()}" sortColumn="${page.getSortColumn()}"/>">${page.getPage()-1}</a></li>
		</c:if>
		<li><a id="switchCurrentPage" style="background:#D8D8D8" href="#">${page.getPage()}</a></li>
		<c:if test="${page.getPage()+1 <= page.getPageMax()}">
			<li>
			<a id="switchPageAdd1" href="<mylib:link target="dashboard" page="${page.getPage()+1}" size="${page.getSize()}" search="${page.getSearch()}" sortColumn="${page.getSortColumn()}"/>">${page.getPage()+1}</a>
			</li>
		</c:if>
		<c:if test="${page.getPage()+2 <= page.getPageMax()}">
			<li><a id="switchPageAdd2"
				href="<mylib:link target="dashboard" page="${page.getPage()+2}" size="${page.getSize()}" search="${page.getSearch()}" sortColumn="${page.getSortColumn()}"/>">${page.getPage()+2}</a></li>
		</c:if>

		<c:if test="${page.getPage() != page.getPageMax()}">
			<li><a href="<mylib:link target="dashboard" page="${page.getPage()+1}" size="${page.getSize()}" search="${page.getSearch()}" sortColumn="${page.getSortColumn()}"/>" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>

	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default" 
			<c:if test="${size == 10}">	style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?size=10&sortColumn=${page.getSortColumn()}&sortDirection=${page.getSortDirection()}'">10</button>
		<button type="button" class="btn btn-default"
			<c:if test="${size == 50}">	style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?size=50&sortColumn=${page.getSortColumn()}&sortDirection=${page.getSortDirection()}'">50</button>
		<button type="button" class="btn btn-default"
			<c:if test="${size == 100}">style="background:#D8D8D8" </c:if>
			onclick="document.location.href='dashboard?size=100&sortColumn=${page.getSortColumn()}&sortDirection=${page.getSortDirection()}'">100</button>
	</div>
</div>