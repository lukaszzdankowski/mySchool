<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Task remove confirmation</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
Removing Task
<h1>Removing Task will remove it from Exam</h1>
<h1>If Task is in use, removing it will also remove corresponding Homeworks. Check below</h1>
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
    <tr><th colspan="5">This task is used in Exams:</th></tr>
    <c:forEach items="${task.exams}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.title}</td>
            <td><a href="/exam/crud/show/${item.id}">Show exam</a></td>
            <td><a href="/exam/crud/edit/${item.id}">Edit exam</a></td>
            <td><a href="/exam/crud/remove/${item.id}">Remove exam</a></td>
        </tr>
    </c:forEach>
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
<a href="/task/crud/delete/${task.id}">REMOVE</a><br>
<a href="/task/crud/showall">CANCEL</a><br>
</body>
</html>
