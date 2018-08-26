<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 24.08.18
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logging</title>
</head>
<body>

<h1>Logging Form</h1>
<%--@elvariable id="user" type="pl.coderslab.entity.User"--%>
<form:form modelAttribute="user" method="post">
    <c:out value="${info}"/><br>
    <form:errors path="email"/><br>
    email:<form:input path="email"/><br>
    <form:errors path="password"/><br>
    password:<form:password path="password"/>
    <input type="submit" value="Sign IN">
</form:form>
<br><br>
For registration, click here:<a href="http://localhost:8080/signUp">| Sign UP |</a>
</body>
</html>
