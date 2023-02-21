<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Exam form</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
Editing Exam
<h1>If Exam is in use, saving it will make a copy. Check below</h1>
<form:form method="post" action="/exam/crud/save" modelAttribute="exam">
    <form:label path="title">title: </form:label>
    <form:input path="title"/>
    <form:errors path="title"/><br>

    <form:label path="tasks">tasks: </form:label><br>
    <form:checkboxes path="tasks" items="${tasklist}" itemLabel="content" itemValue="id" delimiter="<br>"/><br>

    <form:hidden path="id"/>
    <input type="submit" value="Save Exam" />
</form:form>
<br>
<table>
    <tr><th colspan="5">This Exam is used in following Homeworks:</th></tr>
    <tr>
        <th>id</th>
        <th>Student's name</th>
        <th>score</th>
        <th>show link</th>
        <th>remove link</th>
    </tr>
    <c:forEach items="${homeworksInUse}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.user.name}</td>
            <td>${item.score}</td>
            <td><a href="/homework/crud/show/${item.id}">Show homework</a></td>
            <td><a href="/homework/crud/remove/${item.id}">Remove homework</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/exam/crud/showall">Return to Exam list</a><br>
</body>
</html>
