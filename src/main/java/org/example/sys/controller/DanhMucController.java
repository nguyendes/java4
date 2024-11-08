package org.example.sys.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.sys.domain.entity.DanhMuc;
import org.example.sys.repository.impl.DanhMucRepoImpl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(value = {"/danhmuc/hien-thi", "/danhmuc/them", "/danhmuc/sua", "/danhmuc/xoa"})
public class DanhMucController extends HttpServlet {
    DanhMucRepoImpl danhMucRepo = new DanhMucRepoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/danhmuc/hien-thi":
                hienThiDanhMuc(req, resp);
                break;
            case "/danhmuc/xoa":
                xoaDanhMuc(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/danhmuc/them".equals(action)) {
            themDanhMuc(req, resp);
        }
    }

    private void hienThiDanhMuc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DanhMuc> danhMucs = danhMucRepo.findAll();
        req.setAttribute("danhMucs", danhMucs);
        req.getRequestDispatcher("/danhmuc.jsp").forward(req, resp);
    }

    private void themDanhMuc(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setMaDanhMuc(req.getParameter("maDanhMuc"));
        danhMuc.setTenDanhMuc(req.getParameter("tenDanhMuc"));
        danhMuc.setTrangThai(req.getParameter("trangThai"));
        danhMuc.setNgayTao(new Date());
        danhMucRepo.save(danhMuc);
        System.out.println(danhMuc.getTenDanhMuc());
        resp.sendRedirect("/danhmuc/hien-thi");
    }

    private void xoaDanhMuc(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        DanhMuc danhMuc = danhMucRepo.findById(id);
        danhMucRepo.delete(danhMuc);
        resp.sendRedirect("/danhmuc/hien-thi");
    }
}
