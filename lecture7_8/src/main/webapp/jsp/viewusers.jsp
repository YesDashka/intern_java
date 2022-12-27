<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="2" width="70%" cellpadding="2">
  <tr><th>Login</th><th>Name</th></tr>
  <c:forEach var="user" items="${list}">
    <tr>
      <td>${user.login}</td>
      <td>${user.name}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
