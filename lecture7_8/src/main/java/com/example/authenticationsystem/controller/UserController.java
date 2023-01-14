package com.example.authenticationsystem.controller;

import com.example.authenticationsystem.beans.UserDto;
import com.example.authenticationsystem.dao.InMemoryUserDao;
import com.example.authenticationsystem.dao.UserDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final UserDao dao = new InMemoryUserDao();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getMainPage(HttpServletRequest request) {
        UserDto userDto = (UserDto) request.getSession().getAttribute("userData");
        return new ModelAndView("/jsp/hello.jsp", "user", userDto);
    }

    @RequestMapping(value = "/viewusers", method = RequestMethod.GET)
    public ModelAndView viewUsers(){
        return new ModelAndView("/jsp/viewusers.jsp","list", dao.findAllUsers());
    }

}
