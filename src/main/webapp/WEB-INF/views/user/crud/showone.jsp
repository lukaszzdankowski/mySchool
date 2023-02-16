<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show User</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
<h1>User details</h1>
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
        <th>role</th>
        <td>${user.role}</td>
    </tr>
</table>
<a href="/user/crud/showall">Return to User list</a><br>
</body>
</html>
