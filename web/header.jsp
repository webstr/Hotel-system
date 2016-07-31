<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hotel system</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">HotelSystem</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="/">Home</a></li>
                <c:if test="${not empty sessionScope.login and sessionScope.login==true}">
                    <li><a href="createApplication.jsp"> Create application</a></li>
                    <li><a href="viewApplications"> View applications</a></li>

                    <c:if test="${sessionScope.user_role == 2}">
                        <li><a href="viewAllApplications"> View all applications</a></li>
                        <li><a href="createRoom.jsp" style="margin-right: 2em"> Create room</a></li>
                    </c:if>

                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty sessionScope.login or (not empty sessionScope.login and sessionScope.login==false)}">
                    <li><a href="registration.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:if>
                <c:if test="${not empty sessionScope.login and sessionScope.login==true}">
                    <li><a> Hi, ${sessionScope.user}!</a></li>
                    <li><a href="logout">Logout</a></li>
                </c:if>
            </ul>
        </div>
    </nav>

</div>