<jsp:include page="header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.ponikarchuk.resources.text" />

<c:if test="${sessionScope.incorrect}">
    <% session.setAttribute("incorrect", false); %>
    <c:out value="registarion incorrect"/>
</c:if>

<form class="form-horizontal" role="form" action="controllerRegistration" method="post">
    <input type="hidden" name="action" value="registration"/>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="name" />:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="name"/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2"><fmt:message key="surname" />:</label>
        <div class="col-sm-5">
            <input type="text" class="form-control" name="surname"/>
        </div>
    </div>
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
            <fmt:message key="create" var="buttonValue" />
            <input type="submit" name="submit" class="btn btn-default" value="${buttonValue}"/>
        </div>
    </div>
</form>

<jsp:include page="footer.jsp" />