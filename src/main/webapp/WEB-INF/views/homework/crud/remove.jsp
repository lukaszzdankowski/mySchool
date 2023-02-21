<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Homework remove confirmation</title>
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
    </thead>
    <tbody>
    <tr>
        <td>${homework.id}</td>
        <td>${homework.exam.title}</td>
        <td>${homework.user.name}</td>
        <td>${homework.score}</td>
        <td><a href="/homework/crud/show/${homework.id}">Show homework</a></td>
    </tr>
    </tbody>
</table>
<a href="/homework/crud/delete/${homework.id}">REMOVE</a><br>
<a href="/homework/crud/showall">CANCEL</a><br>
</body>
</html>
