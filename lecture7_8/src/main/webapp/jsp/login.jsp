<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859–1">
    <title>Insert title here</title></head>
<body>

<div align="center">
    <h1>User Login Form</h1>
    <p style="color: red"><%= request.getAttribute("error") == null ? "" : request.getAttribute("error")%></p>
    <form action="login" method="post">
        <table>
            <tr>
                <td>Login</td>
                <td><input type="text" name="login"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/></form>
</div>
</body>
</html>