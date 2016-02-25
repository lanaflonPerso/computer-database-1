<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib" %>

<%@ attribute name="page" type="com.excilys.computerdatabase.page.model.ComputerDashboardPage" description="Current page number" %>
<%@ attribute name="name" description="Column name" %>
<%@ attribute name="sortColumn" description="Column ID" %>

<th>
    <table>
        <tr>
            <td rowspan="2">${name}</td>
            <td><a
                    <c:choose>
                        <c:when test="${page.getSortColumn() == sortColumn && page.getSortDirection() == \"ASC\" }">
                            style="color:red"
                        </c:when>
                        <c:otherwise>
                            style="color:#A4A4A4"
                        </c:otherwise>
                    </c:choose>
                    href="<mylib:link target="dashboard" page="${page.getPage()}" size="${page.getSize()}" search="${page.getSearch()}" sortColumn="${sortColumn}" sortDirection="ASC"/>"><span>&#9650;</span></a>
            </td>
        </tr>
        <tr>
            <td><a
                    <c:choose>
                        <c:when test="${page.getSortColumn() == sortColumn && page.getSortDirection() == \"DESC\" }">
                            style="color:red"
                        </c:when>
                        <c:otherwise>
                            style="color:#A4A4A4"
                        </c:otherwise>
                    </c:choose>
                    href="<mylib:link target="dashboard" page="${page.getPage()}" size="${page.getSize()}" search="${page.getSearch()}" sortColumn="${sortColumn}" sortDirection="DESC"/>"><span>&#9660;</span></a>
            </td>
        </tr>
    </table>
</th>