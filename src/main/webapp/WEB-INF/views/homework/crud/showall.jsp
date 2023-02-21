<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Homework List</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<table>
    <thead>
    <th>id</th>
    <th>based on exam</th>
    <th>student</th>
    <th>score</th>
    <th>show link</th>
    <th>remove link</th>
    </thead>
    <tbody>
    <c:forEach items="${homeworks}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.exam.title}</td>
            <td>${item.user.name}</td>
            <td>${item.score}</td>
            <td><a href="/homework/crud/show/${item.id}">Show homework</a></td>
            <td><a href="/homework/crud/remove/${item.id}">Remove homework</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/homework/crud/add">Create homework</a><br>
<a href="/teacher/home">Home page</a>
</body>
</html>
