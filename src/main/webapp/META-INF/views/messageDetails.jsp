<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 27.08.18
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

sender: ${message.sender.username} | receiver: ${message.receiver.username} <br>
displayed:
<c:choose>
    <c:when test="${message.displayed}">
        yes
    </c:when>
    <c:otherwise>
        not yet
    </c:otherwise>
</c:choose>
 | sent: ${message.sent} <br><br>
${message.text}

</body>
</html>
