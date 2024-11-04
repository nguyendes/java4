//package org.example.config.filter;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.config.security.JwtUtil;
//
//import java.io.IOException;
//
//
//public class JWTAuthenticationFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException{
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        Cookie[] cookies = httpRequest.getCookies();
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("JWT")) {
//                    String token = cookie.getValue();
//                    String username = JwtUtil.validateToken(token);
//                    if (username != null) {
//                    } else {
//                        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                        return;
//                    }
//                }
//            }
//        } else {
//            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//        chain.doFilter(request, response);
//    }
//}
