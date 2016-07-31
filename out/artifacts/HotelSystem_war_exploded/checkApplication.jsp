<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<p>Choosen application</p>
<table class="table table-striped">
    <tr>
        <th>Id</th>
        <th>Size</th>
        <th>Type</th>
        <th>Duration</th>
        <th>Status</th>
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
        <th>Status</th>
        <th>Number</th>
        <th>Floor</th>
        <th>Type</th>
        <th>Size</th>
        <c:if test="${sessionScope.user_role==2}">
            <th>Choose room</th>
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
                <td><a href="chooseRoom?id_application=${sessionScope.application.getId()}&id_room=${room.getId()}"> Choose room ${room.getId()}</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<jsp:include page="footer.jsp" />