<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Task form</title>
</head>
<body>
<form:form method="post" action="/task/crud/save" modelAttribute="task">
    <form:label path="content">content: </form:label>
    <form:input path="content"/><br>

    <form:label path="result">result: </form:label>
    <form:input path="result"/><br>

    <form:hidden path="id"/>
    <input type="submit" value="Save Task" />
</form:form>
<a href="/task/crud/showall">Return to Task list</a><br>
</body>
</html>
