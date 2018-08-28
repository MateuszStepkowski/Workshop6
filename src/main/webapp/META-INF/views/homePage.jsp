<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 24.08.18
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
</head>
<body>

<jsp:include page="forms/tweetForm.jsp"/>

<br><br>
<h3><a href="http://localhost:8080/user/messages"> See your Messages ! </a> </h3><br>
<h3><a href="http://localhost:8080/user/editUser"> Edit your account --> </a> </h3>
<br><br>

<c:forEach var="tweet" items="${allTweets}">
    user: <a href="http://localhost:8080/user/${tweet.user.id}/allTweets">${tweet.user.username}</a> |
    created: ${tweet.created} |
    tweet id: <a href="http://localhost:8080/tweet/${tweet.id}">${tweet.id}</a> <br>
    ${tweet.text} <br><br>
</c:forEach>

</body>
</html>
