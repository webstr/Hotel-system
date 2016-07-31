<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.incorrect}">
    <% session.setAttribute("incorrect", false); %>
    <c:out value="registarion incorrect"/>
</c:if>

<form class="form-horizontal" role="form" action="controllerRegistration" method="post">
    <input type="hidden" name="action" value="registration"/>
    <div class="form-group">
        <label class="control-label col-sm-2">Name:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="name"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2">Surname:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="surname"/>
        </div>
    </div>
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
            <input type="submit" name="submit" class="btn btn-default" value="Create"/>
        </div>
    </div>
</form>

<jsp:include page="footer.jsp" />