<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 10/29/2024
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h1>Error: <%= request.getAttribute("status") %></h1>
<p><%= request.getAttribute("message") %></p>
<a href="/index.jsp">Go to Homepage</a>
</body>
</html>
