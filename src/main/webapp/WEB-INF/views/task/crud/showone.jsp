<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Show Task</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
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
    <tr><th>This task is used in Exams:</th></tr>
<c:forEach items="${exams}" var="item">
    <tr><td>${item.title}</td></tr>
</c:forEach>
</table>
<a href="/task/crud/showall">Return to Task list</a><br>
</body>
</html>
