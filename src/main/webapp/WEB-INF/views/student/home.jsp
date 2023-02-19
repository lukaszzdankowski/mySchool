<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Student homepage</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<body>
You are logged as: ${loggedUser.role}: ${loggedUser.email} <a href="/logout">LOGOUT</a><br>
<br>
Your homeworks:
<table>
    <c:forEach items="${studentshomeworks}" var="item">
        <tr>
            <td>${item.exam.title}</td>
<%--            <td><a href="/student/attempt/${item.id}">ATTEMPT</a></td>--%>

            <td>
            <c:choose>
                <c:when test="${not empty item.score}">
                    ${item.score}
                </c:when>
                <c:otherwise>
                    <a href="/student/attempt/${item.id}">ATTEMPT</a>
                </c:otherwise>
            </c:choose>
            </td>


        </tr>
    </c:forEach>
</table>

</body>
</html>
