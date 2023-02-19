<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Homework form</title>
</head>
<body>
<form action="/homework/crud/save" method="post">
    <c:forEach items="${studentlist}" var="item">
        <input type="checkbox" id="item" name="students" value="${item.id}">
        <label for="item"> ${item.name}</label><br>
    </c:forEach>
    <label for="exam">Choose Exam: </label>
    <select name="exam" id="exam">
        <c:forEach items="${examlist}" var="item">
            <option value="${item.id}">${item.title}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Save Homework"/>
</form>
<a href="/homework/crud/showall">Return to Exam list</a><br>
</body>
</html>
