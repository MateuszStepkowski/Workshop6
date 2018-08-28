<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 26.08.18
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Tweets</title>
</head>
<body>
<h3>User:</h3>
id: ${user.id} | username: ${user.username}<br>
<h3>user tweets:</h3>

<c:if test="${thisUser != user}">
<jsp:include page="forms/messageForm.jsp"/>
</c:if>

<c:forEach var="tweet" items="${userTweets}">
    tweet id: <a href="http://localhost:8080/tweet/${tweet.id}">${tweet.id}</a> |
    created: ${tweet.created}<br>
    ${tweet.text} <br><br>
</c:forEach>

</body>
</html>
