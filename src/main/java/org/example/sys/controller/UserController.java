package org.example.sys.controller;

import jakarta.servlet.http.Cookie;
import org.example.sys.domain.entity.User;
import org.example.sys.domain.repository.impl.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(value = "/login")
public class UserController extends HttpServlet {
    private final UserRepository userRepo = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.equals("/login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            System.out.println(password);
            User user = userRepo.findByUsernameAndPassword(username, password);

            if (user != null) {
//                // Tạo JWT
//                String token = JwtUtil.generateToken(username);
//
//                // Tạo cookie chứa JWT
//                Cookie cookie = createJwtCookie(token);
//
//                // Thêm cookie vào response
//                resp.addCookie(cookie);

                req.getSession().setAttribute("currentUser", user);
                resp.sendRedirect("/sanpham/hien-thi");
            } else {
                req.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng");
                req.getRequestDispatcher("login.jsp").forward(req, resp); // Kiểm tra lại đường dẫn
            }
        }
    }

//    private Cookie createJwtCookie(String token) {
//        Cookie cookie = new Cookie("JWT", token);
//        cookie.setMaxAge(86400); // Thời gian sống của cookie (1 ngày)
//        cookie.setHttpOnly(true); // Ngăn JavaScript truy cập cookie
//        cookie.setSecure(true); // Chỉ gửi cookie qua HTTPS
//        cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
//        return cookie;
//    }
}
