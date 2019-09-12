package com.epam.rd.izh.controller;

import com.epam.rd.izh.exception.AuthorizationException;
import com.epam.rd.izh.exception.NotFoundException;
import com.epam.rd.izh.exception.RegistrationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerControllerr {

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleException(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleRuntimeException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("error", "Что-то пошло не так, обратитесь в службу тех поддержки");
        return modelAndView;
    }

    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView handleAuthorizationException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login-page");
        modelAndView.addObject("errorMsg", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(RegistrationException.class)
    public ModelAndView handleRegistrationException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration-page");
        modelAndView.addObject("errorMsg", ex.getMessage());
        return modelAndView;
    }
}
