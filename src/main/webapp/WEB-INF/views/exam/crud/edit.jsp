<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Exam form</title>
</head>
<body>
<form:form method="post" action="/exam/crud/save" modelAttribute="exam">
    <form:label path="title">title: </form:label>
    <form:input path="title"/>
    <form:errors path="title"/><br>

    <form:label path="tasks">tasks: </form:label><br>
    <form:checkboxes path="tasks" items="${tasklist}" itemLabel="content" itemValue="id" delimiter="<br>"/><br>

    <form:hidden path="id"/>
    <input type="submit" value="Save Exam" />
</form:form>
<a href="/exam/crud/showall">Return to Exam list</a><br>
</body>
</html>
