<%@ page import="com.example.authenticationsystem.beans.UserDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>

<head>
    <title>Main Page</title>
</head>

<body>
<div align="center">
    <h1>Hello, ${user.name}</h1>
    </div>
<h2>Menu</h2><br>
<a href="/login?logout=true">Logout</a><br>
<a href="viewusers">View Employees</a>

</body>

</html>