package com.example.authenticationsystem.filter;

import com.example.authenticationsystem.beans.User;
import com.example.authenticationsystem.beans.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*", "/jsp/*"}, description = "Authorization token filter")
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println(request.getServletPath());

        if (request.getServletPath().endsWith(".jsp")) {
            response.sendRedirect("/user/");
            return;
        }

        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("user session is empty");
            response.sendRedirect("/login");
            return;
        }
        UserDto userData = (UserDto) session.getAttribute("userData");
        System.out.println("get user session " + userData);
        if (userData == null) {
            System.out.println("user data is empty");
            response.sendRedirect("/login");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
