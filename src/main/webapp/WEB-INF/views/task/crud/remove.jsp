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
    <c:forEach items="${task.exams}" var="item">
        <tr><td>${item.title}</td></tr>
    </c:forEach>
</table>
<a href="/task/crud/delete/${task.id}">REMOVE</a><br>
<a href="/task/crud/showall">CANCEL</a><br>
</body>
</html>
