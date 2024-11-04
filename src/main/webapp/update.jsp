<%--
  Created by IntelliJ IDEA.
  User: Tucuteprovjp
  Date: 10/29/2024
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sửa Sản Phẩm</title>
</head>
<body>
<h2>Sửa Sản Phẩm</h2>

<form action="sua" method="post">
    <input type="hidden" name="id" value="${sanPham.id}">

    <label for="maSanPham">Mã Sản Phẩm:</label>
    <input type="text" id="maSanPham" name="maSanPham" value="${sanPham.maSanPham}" required><br>

    <label for="tenSanPham">Tên Sản Phẩm:</label>
    <input type="text" id="tenSanPham" name="tenSanPham" value="${sanPham.tenSanPham}" required><br>

    <label for="trangThai">Trạng Thái:</label>
    <input type="text" id="trangThai" name="trangThai" value="${sanPham.trangThai}" required><br>

    <label for="danhMucId">Danh Mục:</label>
    <select id="danhMucId" name="danhMucId">
        <c:forEach var="danhMuc" items="${danhMucs}">
            <option value="${danhMuc.id}" <c:if test="${danhMuc.id == sanPham.danhMuc.id}">selected</c:if>>
                    ${danhMuc.tenDanhMuc}
            </option>
        </c:forEach>
    </select><br>

    <button type="submit">Cập Nhật</button>
    <a href="hien-thi">Quay lại</a>
</form>

</body>
</html>

