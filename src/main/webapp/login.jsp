<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 11/1/2024
  Time: 10:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Đăng Nhập</title>
</head>
<body>
<h2>Đăng Nhập</h2>
<form action="/login" method="post">
  <label for="username">Tên đăng nhập:</label>
  <input type="text" id="username" name="username" required><br>

  <label for="password">Mật khẩu:</label>
  <input type="password" id="password" name="password" required><br>

  <button type="submit">Đăng nhập</button>
</form>
<c:if test="${not empty errorMessage}">
  <p style="color: red;">${errorMessage}</p>
</c:if>
</body>
</html>

