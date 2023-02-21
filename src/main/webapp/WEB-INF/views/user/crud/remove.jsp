<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User remove confirmation</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
Remove User
<c:if test="${user.role == 'student'}">
    <h1>If Student has Homeworks, removing him will also remove corresponding Homeworks. Check below</h1>
</c:if>
<table>
    <tr>
        <th>id</th>
        <td>${user.id}</td>
    </tr>
    <tr>
        <th>email</th>
        <td>${user.email}</td>
    </tr>
    <tr>
        <th>name</th>
        <td>${user.name}</td>
    </tr>
    <tr>
        <th>id</th>
        <td>${user.role}</td>
    </tr>
</table>
<br>
<c:if test="${not empty homeworksInUse}">
    <table>
        <tr><th colspan="4">This Task is used in following Homeworks:</th></tr>
        <tr>
            <th>id</th>
            <th>score</th>
            <th>show link</th>
            <th>remove link</th>
        </tr>
        <c:forEach items="${homeworksInUse}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.score}</td>
                <td><a href="/homework/crud/show/${item.id}">Show homework</a></td>
                <td><a href="/homework/crud/remove/${item.id}">Remove homework</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="/user/crud/delete/${user.id}">REMOVE</a><br>
<a href="/user/crud/showall">CANCEL</a><br>
</body>
</html>
