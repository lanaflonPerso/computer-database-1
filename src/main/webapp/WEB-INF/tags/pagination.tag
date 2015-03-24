<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="page" required="true" type="java.lang.Integer"	description="Current page"%>
<%@ attribute name="size" required="true" type="java.lang.String"	description="Number of element for each page"%>

<div class="container text-center">
	<ul class="pagination">
		<c:if test="${page != 1}">
			<li><a href="dashboard?page=${page-1}" aria-label="Previous">
					<span aria-hidden="true">&laquo;</span>
			</a></li>
		</c:if>

		<c:if test="${page-2 > 0}">
			<li><a id="switchPageMalus2"
				href="dashboard?page=${page-2}&size=${size}">${page-2}</a></li>
		</c:if>
		<c:if test="${page-1 > 0}">
			<li><a id="switchPageMalus1"
				href="dashboard?page=${page-1}&size=${size}">${page-1}</a></li>
		</c:if>
		<li><a id="switchCurrentPage" href="#">${page}</a></li>
		<c:if test="${page+1 <= pageMax}">
			<li><a id="switchPageAdd1"
				href="dashboard?page=${page+1}&size=${size}">${page+1}</a></li>
		</c:if>
		<c:if test="${page+2 <= pageMax}">
			<li><a id="switchPageAdd2"
				href="dashboard?page=${page+2}&size=${size}">${page+2}</a></li>
		</c:if>

		<c:if test="${page != pageMax}">
			<li><a href="dashboard?page=${page+1}" aria-label="Next"> <span
					aria-hidden="true">&raquo;</span>
			</a></li>
		</c:if>
	</ul>

	<div class="btn-group btn-group-sm pull-right" role="group">
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?size=10'">10</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?size=50'">50</button>
		<button type="button" class="btn btn-default"
			onclick="document.location.href='dashboard?size=100'">100</button>
	</div>
</div>