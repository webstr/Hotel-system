<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.ponikarchuk.resources.text" />

<c:if test="${requestScope.incorrect}">
    <c:out value="fill all fields"/>
</c:if>

<c:if test="${sessionScope.incorrectLogin}">
    <% session.setAttribute("incorrectLogin", false); %>
    <c:out value="login incorrect"/>
</c:if>

<form class="form-horizontal" role="form" action="controllerLogin" method="post">
    <input type="hidden" name="action" value="login"/>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="login.label.username" />:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="login"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="login.label.password" />:</label>
        <div class="col-sm-5">
            <input type="password" class="form-control" name="password"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <fmt:message key="login.label.username" var="buttonValue" />
            <input type="submit" name="submit" class="btn btn-default" value="${buttonValue}"/>
        </div>
    </div>
</form>

<jsp:include page="footer.jsp" />
