<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>

<%@ attribute name="pageAll" description="Current page number"%>

<%@ attribute name="page" description="Current page number"%>
<%@ attribute name="size" description="Element by page"%>
<%@ attribute name="search" description="Word to search"%>
<%@ attribute name="name" description="Column name"%>
<%@ attribute name="sortColumn" description="Column ID"%>
<%@ attribute name="currentColumn" description="Current Column"%>
<%@ attribute name="currentDirection" description="Current Direction"%>


<th>
	<table>
		<tr>
			<td rowspan="2">${name}</td>
			<td><a
				<c:choose>
				  <c:when test="${(currentColumn == sortColumn) && (currentDirection == \"ASC\") }">
				   style="color:red"
				  </c:when>
				  <c:otherwise>
				  style="color:#A4A4A4"
				  </c:otherwise>
				</c:choose>
				href="<mylib:link target="dashboard" page="${page}" size="${size}" search="${search}" sortColumn="${sortColumn}" sortDirection="ASC"/>"><span>&#9650;</span></a></td>
		</tr>
		<tr>
			<td><a
				<c:choose>
				  <c:when test="${(currentColumn == sortColumn) && (currentDirection == \"DESC\") }">
					style="color:red"
				  </c:when>
				  <c:otherwise>
				  style="color:#A4A4A4"
				  </c:otherwise>
				</c:choose>
				href="<mylib:link target="dashboard" page="${page}" size="${size}" search="${search}" sortColumn="${sortColumn}" sortDirection="DESC"/>"><span>&#9660;</span></a></td>
		</tr>
	</table>
</th>