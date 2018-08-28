<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="comment" type="pl.coderslab.entity.Comment"--%>
<form:form method="post" modelAttribute="comment">
<form:errors path="*"/>
<form:input path="text"/>
<input type="submit" value="add Comment">
</form:form>