<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 28.08.18
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

<h1>Edit Form</h1>
<%--@elvariable id="userDTO" type="pl.coderslab.dto.UserDTO"--%>
<form:form modelAttribute="userDTO" method="post">
    <form:errors path="email"/><br>
    <c:out value="${emailInfo}"/><br>
    email:<form:input path="email"/><br>
    <c:out value="${newPasswordInfo}"/><br>
    <form:errors path="newPassword"/><br>
    new password(optional):<form:password path="newPassword"/><br>
    confirm new password: <form:password path="newPasswordConfirm"/><br>
    <c:out value="${currentPasswordInfo}"/><br>
    current password(required):<form:password path="currentPassword"/>

    <input type="submit" value="save change/s">
</form:form>



</body>
</html>
