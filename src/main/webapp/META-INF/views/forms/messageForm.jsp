<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="message" type="pl.coderslab.entity.Message"--%>
<form:form modelAttribute="message" method="post">
    <form:errors path="text"/>
    <form:textarea path="text" cols="25" rows="4"/>
    <input type="submit" value="Send massage--=>">
</form:form>
