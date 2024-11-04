<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Sản Phẩm</title>
</head>
<body>
<% if (request.getAttribute("errorMessage") != null) { %>
<div class="alert alert-danger">
    <%= request.getAttribute("errorMessage") %>
</div>
<% } %>

<h2>Danh sách Sản Phẩm</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Mã Sản Phẩm</th>
        <th>Tên Sản Phẩm</th>
        <th>Trạng Thái</th>
        <th>Ngày Tạo</th>
        <th>Ngày Sửa</th>
        <th>Danh Mục</th>
        <th>Hành Động</th>
    </tr>
    <c:forEach var="sanPham" items="${sanPhams}">
        <tr>
            <td>${sanPham.id}</td>
            <td>${sanPham.maSanPham}</td>
            <td>${sanPham.tenSanPham}</td>
            <td>${sanPham.trangThai}</td>
            <td>${sanPham.ngayTao}</td>
            <td>${sanPham.ngaySua}</td>
            <td>${sanPham.tenDanhMuc}</td>
            <td>
                <a href="sua?id=${sanPham.id}">Sửa</a>
                <a href="/sanpham/xoa?id=${sanPham.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="/sanpham/them" method="post">
    <label for="maSanPham">Mã Sản Phẩm:</label>
    <input type="text" id="maSanPham" name="maSanPham" required><br>

    <label for="tenSanPham">Tên Sản Phẩm:</label>
    <input type="text" id="tenSanPham" name="tenSanPham" required><br>

    <label for="trangThai">Trạng Thái:</label>
    <input type="text" id="trangThai" name="trangThai" required><br>

    <label for="danhMucId">Danh Mục:</label>
    <select id="danhMucId" name="danhMucId">
        <c:forEach var="danhMuc" items="${danhMucs}">
            <option value="${danhMuc.id}">${danhMuc.tenDanhMuc}</option>
        </c:forEach>
    </select><br>

    <button type="submit">Thêm</button>
</form>
</body>
</html>
