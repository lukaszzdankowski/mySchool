<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All User List</title>
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
    <th>email</th>
    <th>name</th>
    <th>role</th>
    <th>show link</th>
    <th>edit link</th>
    <th>remove link</th>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.email}</td>
            <td>${item.name}</td>
            <td>${item.role}</td>
            <td><a href="/user/crud/show/${item.id}">Show user</a></td>
            <td><a href="/user/crud/edit/${item.id}">Edit user</a></td>
            <td><a href="/user/crud/remove/${item.id}">Remove user</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/user/crud/add">Create user</a>
</body>
</html>
