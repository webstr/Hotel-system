<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<p>Your applications</p>
<table class="table table-striped">
    <tr>
        <th>Id</th>
        <th>Size</th>
        <th>Type</th>
        <th>Duration</th>
        <th>Status</th>
        <th>Pay</th>
        <c:if test="${sessionScope.user_role==2}">
            <th>Check application</th>
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
                    <a href="payApplication?id_application=${application.getId()}"> Pay this </a>
                </c:if>
            </td>
            <td>
                <c:if test="${sessionScope.user_role==2 && application.getStatus().toString()=='OPEN'}">
                    <a href="checkApplication?id_application=${application.getId()}"> Check
                        application ${application.getId()}</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp"/>