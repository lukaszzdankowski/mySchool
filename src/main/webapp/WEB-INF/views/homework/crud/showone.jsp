<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show Homework</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>Homework details</h1>
<table>
    <tr>
        <th>id</th>
        <td>${homework.id}</td>
    </tr>
    <tr>
        <th>based on exam</th>
        <td>${homework.exam.title}</td>
    </tr>
    <tr>
        <th>student</th>
        <td>${homework.user.name}</td>
    </tr>
    <tr>
        <th>score</th>
        <td>${homework.score}</td>
    </tr>
</table>
<br>
    <table>
        <tr>
            <th colspan="3">Replies detail</th>
        </tr>
        <tr>
            <th>Task content</th>
            <th>Correct result</th>
            <th>Student answer</th>
        </tr>
        <c:forEach items="${homework.replies}" var="item">
            <tr>
                <td>${item.task.content}</td>
                <td>${item.task.result}</td>
                <td>${item.answer}</td>
            </tr>
        </c:forEach>
    </table>
<a href="/homework/crud/showall">Return to Homework list</a><br>
</body>
</html>
