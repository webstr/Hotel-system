<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />

<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="ua.ponikarchuk.resources.text" />


<html lang="${sessionScope.language}">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="header.name" /></title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/index.jsp"><fmt:message key="header.name" /></a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/index.jsp"><fmt:message key="header.home" /></a></li>
                <c:if test="${not empty sessionScope.login and sessionScope.login==true}">
                    <li><a href="createApplication.jsp"><fmt:message key="header.createApplication" /></a></li>
                    <li><a href="viewApplications"><fmt:message key="header.viewApplications" /></a></li>

                    <c:if test="${sessionScope.user_role == 2}">
                        <li><a href="viewAllApplications"><fmt:message key="header.viewAllApplications" /></a></li>
                        <li><a href="createRoom.jsp" style="margin-right: 2em"><fmt:message key="header.createRoom" /></a></li>
                    </c:if>

                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="changeLanguage?language=eu">EU</a></li>
                <li><a href="changeLanguage?language=uk">UA</a></li>

                <c:if test="${empty sessionScope.login or (not empty sessionScope.login and sessionScope.login==false)}">
                    <li><a href="registration.jsp"><fmt:message key="signUp" /></a></li>
                    <li><a href="login.jsp"><fmt:message key="login.label.username" /></a></li>
                </c:if>
                <c:if test="${not empty sessionScope.login and sessionScope.login==true}">
                    <li><a> <fmt:message key="hi" />, ${sessionScope.user}!</a></li>
                    <li><a href="logout"><fmt:message key="logout" /></a></li>
                </c:if>
            </ul>
        </div>
    </nav>

</div>