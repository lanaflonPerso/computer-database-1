<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>


<%@ attribute name="colorMenu" description="color for menu"%>

<div class="col-md-5 navbar-header ">
    <a class="navbar-brand white-color" href="${pageContext.request.contextPath}/view/home"> Application computer database</a>
</div>
<div class="col-md-4 navbar-header">
    <security:authorize access="isAuthenticated()">
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle white-color ${colorMenu}" data-toggle="dropdown" role="button" aria-expanded="false">Selection <span class="caret"></span></a>
                <ul class="dropdown-menu dropdown-menu-right" role="menu">
                    <security:authorize access="hasRole('ROLE_SUPER_ADMIN')">
                        <li><a class="btn-warning btn-user" href="${pageContext.request.contextPath}/user/view/dashboard"><spring:message code="user" /></a></li>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
                        <li><a class="btn-warning btn-computer " href="${pageContext.request.contextPath}/computer/view/dashboard"><spring:message code="computer" /></a></li>
                    </security:authorize>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a class="btn-warning btn-company" href="${pageContext.request.contextPath}/company/view/dashboard"><spring:message code="company" /></a></li>
                    </security:authorize>
                </ul>
            </li>
        </ul>
    </security:authorize>
</div>