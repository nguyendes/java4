package org.example.sys.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sys.domain.dto.SanPhamDTO;
import org.example.sys.domain.entity.SanPham;
import org.example.sys.domain.entity.DanhMuc;
import org.example.sys.domain.entity.User;
import org.example.sys.repository.impl.SanPhamRepoImpl;
import org.example.sys.repository.impl.DanhMucRepoImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(value = {"/sanpham/hien-thi","/sanpham/them","/sanpham/sua","/sanpham/xoa"})
public class SanPhamController extends HttpServlet {
    private final SanPhamRepoImpl sanPhamRepo = new SanPhamRepoImpl();
    private final DanhMucRepoImpl danhMucRepo = new DanhMucRepoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/sanpham/hien-thi":
                hienThiSanPham(req, resp);
                break;
            case "/sanpham/sua":
                hienThiChiTietSanPham(req, resp);
                break;
            case "/sanpham/xoa":
                xoaSanPham(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/sanpham/them".equals(action)) {
            themSanPham(req, resp);
        } else if ("/sanpham/sua".equals(action)) {
            suaSanPham(req, resp);
        }
    }

    private void hienThiSanPham(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SanPhamDTO> sanPhams = sanPhamRepo.findAll();
        List<DanhMuc> danhMucs = danhMucRepo.findAll();
        req.setAttribute("sanPhams", sanPhams);
        req.setAttribute("danhMucs", danhMucs);
        req.getRequestDispatcher("/sanpham.jsp").forward(req, resp);
    }


    private void hienThiChiTietSanPham(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        SanPham sanPham = sanPhamRepo.findById(id);
        req.setAttribute("sanPham", sanPham);
        req.getRequestDispatcher("/update.jsp").forward(req, resp);
    }

    private void themSanPham(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String maSanPham = req.getParameter("maSanPham");
        String tenSanPham = req.getParameter("tenSanPham");
        String trangThai = req.getParameter("trangThai");
        int danhMucId = Integer.parseInt(req.getParameter("danhMucId"));
        DanhMuc danhMuc = danhMucRepo.findById(danhMucId);

        SanPham sanPham = new SanPham();
        sanPham.setMaSanPham(maSanPham);
        sanPham.setTenSanPham(tenSanPham);
        sanPham.setTrangThai(trangThai);
        sanPham.setNgayTao(new Date());
        sanPham.setDanhMuc(danhMuc);

        sanPhamRepo.save(sanPham);
        resp.sendRedirect("/sanpham/hien-thi");
    }

    private void suaSanPham(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String maSanPham = req.getParameter("maSanPham");
        String tenSanPham = req.getParameter("tenSanPham");
        String trangThai = req.getParameter("trangThai");
        int danhMucId = Integer.parseInt(req.getParameter("danhMucId"));
        DanhMuc danhMuc = danhMucRepo.findById(danhMucId);

        SanPham sanPham = sanPhamRepo.findById(id);
        if (sanPham != null) {
            sanPham.setMaSanPham(maSanPham);
            sanPham.setTenSanPham(tenSanPham);
            sanPham.setTrangThai(trangThai);
            sanPham.setNgaySua(new Date());
            sanPham.setDanhMuc(danhMuc);
            sanPhamRepo.update(sanPham);
        }
        resp.sendRedirect("/sanpham/hien-thi");
    }

    private void xoaSanPham(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User currentUser = (User) req.getSession().getAttribute("currentUser");

        if (currentUser == null) {
            resp.sendRedirect("/login");
            return;
        }

        SanPham sanPham = sanPhamRepo.findById(id);
        if (sanPham != null) {
            if ("ADMIN".equals(currentUser.getRole().name())) {
                sanPhamRepo.delete(sanPham);
                resp.sendRedirect("/sanpham/hien-thi");
            } else {
                req.setAttribute("errorMessage", "Bạn không có quyền xóa sản phẩm này.");
                req.getRequestDispatcher("/sanpham/hien-thi").forward(req, resp);
                            }
        } else {
            req.setAttribute("errorMessage", "Sản phẩm không tồn tại.");
        }


    }

}
