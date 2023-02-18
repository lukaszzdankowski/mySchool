<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Exam List</title>
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
    <th>title</th>
    <th>show link</th>
    <th>edit link</th>
    <th>remove link</th>
    </thead>
    <tbody>
    <c:forEach items="${exams}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td><a href="/exam/crud/show/${item.id}">Show exam</a></td>
            <td><a href="/exam/crud/edit/${item.id}">Edit exam</a></td>
            <td><a href="/exam/crud/remove/${item.id}">Remove exam</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/exam/crud/add">Create exam</a><br>
<a href="/teacher/home">Home page</a>
</body>
</html>
