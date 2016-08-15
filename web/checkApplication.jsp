<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.ponikarchuk.resources.text" />

<p><fmt:message key="choosenApplication" /></p>
<table class="table table-striped">
    <tr>
        <th>Id</th>
        <th><fmt:message key="size" /></th>
        <th><fmt:message key="type" /></th>
        <th><fmt:message key="duration" /></th>
        <th><fmt:message key="status" /></th>
    </tr>
        <tr>
            <td>${sessionScope.application.getId()}</td>
            <td>${sessionScope.application.getSize()}</td>
            <td>${sessionScope.application.getType()}</td>
            <td>${sessionScope.application.getDuration()}</td>
            <td>${sessionScope.application.getStatus().toString()}</td>

        </tr>
</table>

<p>All rooms</p>
<table class="table table-striped">
    <tr>
        <th>Id</th>
        <th><fmt:message key="status" /></th>
        <th><fmt:message key="number" /></th>
        <th><fmt:message key="floor" /></th>
        <th><fmt:message key="type" /></th>
        <th><fmt:message key="size" /></th>
        <c:if test="${sessionScope.user_role==2}">
            <th><fmt:message key="chooseRoom" /></th>
        </c:if>
    </tr>
    <c:forEach var="room" items="${sessionScope.rooms}">
        <tr>
            <td>${room.getId()}</td>
            <td>${room.getStatus()}</td>
            <td>${room.getRoomNumber()}</td>
            <td>${room.getFloor()}</td>
            <td>${room.getType()}</td>
            <td>${room.getSize()}</td>
            <c:if test="${sessionScope.user_role==2}">
                <td><a href="chooseRoom?id_application=${sessionScope.application.getId()}&id_room=${room.getId()}"> <fmt:message key="chooseRoom" /> ${room.getId()}</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp" />