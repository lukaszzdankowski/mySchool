<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add User form</title>
</head>
<body>
<form:form method="post" action="/user/crud/save" modelAttribute="user">
    <form:label path="email">email: </form:label>
    <form:input path="email"/>
    <form:errors path="email"/><br>

    <form:label path="password">password: </form:label>
    <form:password path="password"/>
    <form:errors path="password"/><br>

    <form:label path="name">name: </form:label>
    <form:input path="name"/><br>

    <form:label path="role">role: </form:label>
    <form:select path="role" items="${roles}" /><br>

    <form:hidden path="id"/>
    <input type="submit" value="Save User" />
</form:form>
<a href="/user/crud/showall">Return to User list</a><br>
</body>
</html>
