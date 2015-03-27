<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ attribute name="target" description="Current page"%>
<%@ attribute name="page" description="Current page"%>
<%@ attribute name="size" description="Number of element for each page"%>
<%@ attribute name="search" description="Searched element"%>
<%@ attribute name="sortColumn" description="Column to sort"%>
<%@ attribute name="sortDirection" description="Direction for sorting"%>


<c:url value="${target}?page=${page}&size=${size}&search=${search}&sortColumn=${sortColumn}&sortDirection=${sortDirection}" />
