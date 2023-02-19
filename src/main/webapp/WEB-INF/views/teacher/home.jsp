<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Teacher homepage</title>
</head>
<body>
You are logged as: ${loggedUser.role}: ${loggedUser.email} <a href="/logout">LOGOUT</a><br>
<a href="/user/crud/showall">User list</a><br>
<a href="/task/crud/showall">Task list</a><br>
<a href="/exam/crud/showall">Exam list</a><br>
<a href="/homework/crud/showall">Homework list</a><br>
</body>
</html>
