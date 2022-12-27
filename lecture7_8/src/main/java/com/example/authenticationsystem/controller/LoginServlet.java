package com.example.authenticationsystem.controller;

import com.example.authenticationsystem.beans.User;
import com.example.authenticationsystem.beans.UserDto;
import com.example.authenticationsystem.beans.ValidationError;
import com.example.authenticationsystem.dao.InMemoryUserDao;
import com.example.authenticationsystem.dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginServlet {
    private static final UserDao dao = new InMemoryUserDao();

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isToLogout = request.getParameter("logout") != null;
        request.setAttribute("userData", new UserDto());
        if (isToLogout) {
            request.getSession().invalidate();
            System.out.println("invalid user session");
        }
        return new ModelAndView("/jsp/login.jsp");
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        Optional<User> byLoginAndPassword = dao.findByLoginAndPassword(login, password);

        if (byLoginAndPassword.isPresent()) {
            System.out.println(byLoginAndPassword.get());
            session.setMaxInactiveInterval(5 * 60 * 300);
            session.setAttribute("userData", UserDto.from(byLoginAndPassword.get()));
            return new ModelAndView("redirect:/user/");
        } else {
            request.setAttribute("error", new ValidationError("invalid username or password"));
            System.out.println("error invalid username and password");
            return new ModelAndView("/jsp/login.jsp");
        }
    }
}