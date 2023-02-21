<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Cannot edit Task</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>You cannot edit task that is already used in Homeworks</h1>
<h1>Task details</h1>
<table>
    <tr>
        <th>id</th>
        <td>${task.id}</td>
    </tr>
    <tr>
        <th>content</th>
        <td>${task.content}</td>
    </tr>
    <tr>
        <th>result</th>
        <td>${task.result}</td>
    </tr>
</table>
<br>
<table>
    <tr><th colspan="5">This Task is used in following Homeworks:</th></tr>
    <tr>
        <th>id</th>
        <th>Student's name</th>
        <th>score</th>
        <th>show link</th>
        <th>remove link</th>
    </tr>
    <c:forEach items="${homeworksInUse}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.user.name}</td>
            <td>${item.score}</td>
            <td><a href="/homework/crud/show/${item.id}">Show homework</a></td>
            <td><a href="/homework/crud/remove/${item.id}">Remove homework</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/task/crud/showall">Return to Task list</a><br>
</body>
</html>
