<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</body>
<a href="/task/crud/delete/${task.id}">REMOVE</a><br>
<a href="/task/crud/showall">CANCEL</a><br>
</html>
