<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="tweet" type="pl.coderslab.entity.Tweet"--%>
<h4>Tweet here:</h4>
<form:form method="post" modelAttribute="tweet">
    <form:errors path="*"/>
    <form:textarea path="text" cols="70" rows="2"/><br>
    <input type=submit value="add new Tweet">
</form:form><br>