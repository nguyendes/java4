package org.example.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.config.security.JwtUtil;

import java.io.IOException;
import java.util.Arrays;

public class JWTAuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Cookie[] cookies = httpRequest.getCookies();
        boolean isAuthenticated = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    String username = JwtUtil.validateToken(token);

                    if (username != null) {
                        httpRequest.setAttribute("username", username);
                        isAuthenticated = true;
                        break;
                    } else {
                        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                        return;
                    }
                }
            }
        }

        if (!isAuthenticated) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

        chain.doFilter(request, response);
    }
}
