<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Homework time</title>
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
    <th>Task</th>
    <th>Your answer</th>
    </thead>
    <tbody>
    <form:form method="post" action="/student/attempt" modelAttribute="homework">
        <c:forEach items="${homework.replies}" var="item" varStatus="vs">
            <tr>
            <td>${item.task.content}</td>
            <td><form:input path="replies[${vs.index}].answer"/></td>
            </tr>
            <form:hidden path="replies[${vs.index}].homework.id"/>
            <form:hidden path="replies[${vs.index}].task.id"/>
            <form:hidden path="replies[${vs.index}].id"/>
        </c:forEach>
        <form:hidden path="id"/>
        <form:hidden path="exam.id"/>
        <form:hidden path="user.id"/>
        <input type="submit" value="Send Homework" />
    </form:form>
    </tbody>
</table>
</body>
</html>
