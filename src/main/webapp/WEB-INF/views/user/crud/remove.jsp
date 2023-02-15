<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</body>
<a href="/user/crud/delete/${user.id}">REMOVE</a><br>
<a href="/user/crud/showall">CANCEL</a><br>
</html>
