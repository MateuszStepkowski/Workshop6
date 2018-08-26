<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mateusz
  Date: 26.08.18
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tweet Details</title>
</head>
<body>

tweet id: ${tweet.id} | author:
<a href="http://localhost:8080/user/${tweet.user.id}/allTweets">${tweet.user.username}</a><br><br>
content: ${tweet.text} <br>
created: ${tweet.created} <br><br>

<h3>Comments:</h3>
<c:forEach items="${tweetComments}" var="comment">
    author: <a href="http://localhost:8080/user/${comment.user.id}/allTweets">${comment.user.username}</a> | created: ${comment.created}<br>
    ${comment.text}<br><br>
</c:forEach>

<jsp:include page="forms/commentForm.jsp"/>

</body>
</html>
