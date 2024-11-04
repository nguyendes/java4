<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 10/29/2024
  Time: 10:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Danh Mục</title>
</head>
<body>

<h1>Danh Sách Danh Mục</h1>


<table border="1">
    <tr>
        <th>ID</th>
        <th>Mã Danh Mục</th>
        <th>Tên Danh Mục</th>
        <th>Trạng Thái</th>
        <th>Ngày Tạo</th>
        <th>Ngày Sửa</th>
        <th>Thao Tác</th>
    </tr>
    <c:forEach var="danhMuc" items="${danhMucs}">
        <tr>
            <td>${danhMuc.id}</td>
            <td>${danhMuc.maDanhMuc}</td>
            <td>${danhMuc.tenDanhMuc}</td>
            <td>${danhMuc.trangThai}</td>
            <td>${danhMuc.ngayTao}</td>
            <td>${danhMuc.ngaySua}</td>
            <td>
                <a href="/danhmuc/xoa?id=${danhMuc.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này không?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Thêm Danh Mục Mới</h2>
<form action="/danhmuc/them" method="post">
    <label for="maDanhMuc">Mã Danh Mục:</label>
    <input type="text" id="maDanhMuc" name="maDanhMuc" required>

    <label for="tenDanhMuc">Tên Danh Mục:</label>
    <input type="text" id="tenDanhMuc" name="tenDanhMuc"required>

    <label for="trangThai">Trạng Thái:</label>
    <select id="trangThai" name="trangThai" required>
        <option value="Active">Hoạt Động</option>
        <option value="Inactive">Không Hoạt Động</option>
    </select>

    <button type="submit">Them</button>
</form>

</body>
</html>

