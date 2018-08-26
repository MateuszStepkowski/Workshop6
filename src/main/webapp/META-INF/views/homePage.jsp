<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

<%--@elvariable id="newTweet" type="pl.coderslab.entity.Tweet"--%>
<h4>Tweet here:</h4>
<form:form method="post" modelAttribute="newTweet">
    <form:errors path="text"/>
    <form:textarea path="text" cols="14" rows="10"/><br>
    <input type=submit value="add new Tweet">
</form:form><br>

<c:forEach var="tweet" items="${allTweets}">
    user: <a href="http://localhost:8080/user/${tweet.user.id}/allTweets">${tweet.user.username}</a> |
    created: ${tweet.created} |
    tweet id: <a href="http://localhost:8080/tweet/${tweet.id}">${tweet.id}</a> <br>
    ${tweet.text} <br><br>
</c:forEach>

</body>
</html>
