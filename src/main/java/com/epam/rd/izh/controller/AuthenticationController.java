package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.User;
import com.epam.rd.izh.service.IUserManager;
import com.epam.rd.izh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthenticationController {

    @Autowired
    private IUserManager userManager;
    @Autowired
    private IUserService userService;

    /**
     * Данный метод обрабатывает запросы типа GET.
     * <p>
     * Возвращает View странички логина.
     */
    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login-page");
        return modelAndView;
    }

    /**
     * Мы можем указывать в методы контроллера которые обрабатывают запросы различные дополнительные входные параметры.
     * Например: HttpServletRequest и HttpServletResponse. Данные объекты автоматически заполняться данными о реквесте и респонсе.
     * Эти данные мы можем использовать (например достать куки, сессию, хедеры итд).
     */
    @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        modelAndView.setViewName("login-page");
        request.getSession().invalidate();
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(ModelAndView modelAndView, User user) {
        userService.authorize(user);
        modelAndView.setViewName("redirect:/room");
        return modelAndView;

    }

    @GetMapping("/registration")
    public ModelAndView registration(ModelAndView modelAndView) {
        modelAndView.setViewName("registration-page");
        return modelAndView;

    }

    @PostMapping("/registration")
    public ModelAndView registration(ModelAndView modelAndView, @Validated User user,
            BindingResult result) {

        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            modelAndView.setViewName("registration-page");
        } else {
            userService.addNewUser(user);
            modelAndView.setViewName("redirect:/room");
        }

        return modelAndView;
    }
}