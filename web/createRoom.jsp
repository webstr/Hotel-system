<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.ponikarchuk.resources.text" />

<c:if test="${sessionScope.incorrect}">
    <% session.setAttribute("incorrect", false); %>
    <c:out value="Login first!"/>
</c:if>

<form class="form-horizontal" role="form" action="controllerCreateRoom" method="post">
    <input type="hidden" name="action" value="createRoom"/>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="status" /></label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="status"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="number" />:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="room_number"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="floor" />:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="floor"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="type" />:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="type"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="size" />:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="size"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <fmt:message key="create" var="buttonValue" />
            <input type="submit" name="submit" class="btn btn-default" value="${buttonValue}"/>
        </div>
    </div>
</form>

<jsp:include page="footer.jsp" />