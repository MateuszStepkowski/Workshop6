<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="newTweet" type="pl.coderslab.entity.Tweet"--%>
<h4>Tweet here:</h4>
<form:form method="post" modelAttribute="newTweet">
    <form:errors path="text"/>
    <form:textarea path="text" cols="70" rows="2"/><br>
    <input type=submit value="add new Tweet">
</form:form><br>