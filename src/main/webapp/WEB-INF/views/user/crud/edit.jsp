<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User form</title>
</head>
<body>
<form:form method="post" action="/user/crud/save" modelAttribute="user">
    <form:label path="email">email: </form:label>
    <form:input path="email"/><br>

    <form:label path="name">name: </form:label>
    <form:input path="name"/><br>

    <form:label path="role">role: </form:label>
    <form:input path="role"/><br>

    <form:hidden path="id"/>
    <input type="submit" value="Save User" />
</form:form>
<a href="/user/crud/showall">Return to User list</a><br>
</body>
</html>
