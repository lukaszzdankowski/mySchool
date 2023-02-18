<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Task List</title>
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
    <th>content</th>
    <th>result</th>
    <th>show link</th>
    <th>edit link</th>
    <th>remove link</th>
    </thead>
    <tbody>
    <c:forEach items="${tasks}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.content}</td>
            <td>${item.result}</td>
            <td><a href="/task/crud/show/${item.id}">Show task</a></td>
            <td><a href="/task/crud/edit/${item.id}">Edit task</a></td>
            <td><a href="/task/crud/remove/${item.id}">Remove task</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/task/crud/add">Create task</a><br>
<a href="/teacher/home">Home page</a>
</body>
</html>
