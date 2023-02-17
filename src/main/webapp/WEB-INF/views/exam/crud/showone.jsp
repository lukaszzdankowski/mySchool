<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show Exam</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>Exam details</h1>
<table>
    <tr>
        <th>id</th>
        <td>${exam.id}</td>
    </tr>
    <tr>
        <th>title</th>
        <td>${exam.title}</td>
    </tr>
</table>
<br>
    <table>
        <tr>
            <th colspan="3">This Exam contains following Tasks:</th>
        </tr>
        <c:forEach items="${exam.tasks}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.content}</td>
                <td>${item.result}</td>
            </tr>
        </c:forEach>
    </table>
<a href="/exam/crud/showall">Return to Exam list</a><br>
</body>
</html>
