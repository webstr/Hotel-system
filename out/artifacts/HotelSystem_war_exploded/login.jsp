<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <label class="control-label col-sm-2">Login:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="login"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Password:</label>
        <div class="col-sm-5">
            <input type="password" class="form-control" name="password"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-5">
            <input type="submit" name="submit" class="btn btn-default" value="Login"/>
        </div>
    </div>
</form>

<jsp:include page="footer.jsp" />
