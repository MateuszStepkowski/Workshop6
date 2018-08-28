<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 26.08.18
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
</head>
<body>

<c:forEach var="message" items="${userMessages}">
    <c:choose>
        <c:when test="${message.displayed==false && message.receiver.id == thisUser.id}">
            <b> sender:<a href="http://localhost:8080/user/${message.sender.id}/allTweets">${message.sender.username}</a> | sent: ${message.sent}<br>
                    ${message.text}</b>
            <br><a href="http://localhost:8080/messageDetails/${message.id}">see details</a>
            <br><br>
        </c:when>
        <c:when test="${message.receiver.id == thisUser.id}">
            sender: <a href="http://localhost:8080/user/${message.sender.id}/allTweets">${message.sender.username}</a> | sent: ${message.sent}<br>
            ${message.text}
            <br><a href="http://localhost:8080/messageDetails/${message.id}">see details</a>
            <br><br>
        </c:when>
        <c:otherwise>
            receiver: <a href="http://localhost:8080/user/${message.receiver.id}/allTweets">${message.receiver.username}</a> | sent: ${message.sent}<br>
            ${message.text}
            <br><a href="http://localhost:8080/messageDetails/${message.id}">see details</a>
            <br><br>
        </c:otherwise>
    </c:choose>
</c:forEach>

</body>
</html>
