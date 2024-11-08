package org.example.sys.controller;

import jakarta.servlet.http.Cookie;
import org.example.config.security.JwtUtil;
import org.example.sys.domain.entity.User;
import org.example.sys.repository.impl.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(value = {"/login","/logout"})
public class UserController extends HttpServlet {
    private final UserRepository userRepo = new UserRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.equals("/logout")) {
            logout(req, resp);
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.equals("/login")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            User user = userRepo.findByUsernameAndPassword(username, password);

            if (user != null) {
                String token = JwtUtil.generateToken(username);
                System.out.println(token);
                Cookie cookie = createJwtCookie(token);

                resp.addCookie(cookie);
                System.out.println(cookie.getValue());
                req.getSession().setAttribute("currentUser", user);
                resp.sendRedirect("/sanpham/hien-thi");
            } else {
                req.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng");
                req.getRequestDispatcher("login.jsp").forward(req, resp); // Kiểm tra lại đường dẫn
            }
        }
    }
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Invalidate the session
        req.getSession().invalidate();

        // Clear the JWT cookie if it exists
        Cookie jwtCookie = new Cookie("JWT", null);
        jwtCookie.setMaxAge(0); // Delete the cookie
        jwtCookie.setPath("/"); // Ensure it's deleted across the whole application
        resp.addCookie(jwtCookie);

        resp.sendRedirect("/login");
    }

    private Cookie createJwtCookie(String token) {
        Cookie cookie = new Cookie("JWT", token);
        cookie.setMaxAge(86400); // Thời gian sống của cookie (1 ngày)
        cookie.setHttpOnly(true); // Ngăn JavaScript truy cập cookie
        cookie.setSecure(true); // Chỉ truyền cookie qua HTTPS
        cookie.setPath("/"); // Áp dụng cho toàn bộ ứng dụng
        return cookie;
    }
}
