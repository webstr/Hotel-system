<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.ponikarchuk.resources.text" />

<p><fmt:message key="yourApplications" /></p>
<table class="table table-striped">
    <tr>
        <th>Id</th>
        <th><fmt:message key="size" /></th>
        <th><fmt:message key="type" /></th>
        <th><fmt:message key="duration" /></th>
        <th><fmt:message key="status" /></th>
        <th><fmt:message key="pay" /></th>
        <c:if test="${sessionScope.user_role==2}">
            <th><fmt:message key="checkApplication" /></th>
        </c:if>
    </tr>
    <c:forEach var="application" items="${sessionScope.applications}">
        <tr>
            <td>${application.getId()}</td>
            <td>${application.getSize()}</td>
            <td>${application.getType()}</td>
            <td>${application.getDuration()}</td>
            <td>${application.getStatus().toString()}</td>
            <td>
                <c:if test="${application.getStatus().toString()=='INPROGRESS'}">
                    <a href="payApplication?id_application=${application.getId()}"> <fmt:message key="pay" /> </a>
                </c:if>
            </td>
            <td>
                <c:if test="${sessionScope.user_role==2 && application.getStatus().toString()=='OPEN'}">
                    <a href="checkApplication?id_application=${application.getId()}"> <fmt:message key="checkApplication" /> ${application.getId()}</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp"/>