package com.epam.rd.izh.interceptor;

import com.epam.rd.izh.dto.User;
import com.epam.rd.izh.service.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserNameAwareInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserManager userManager;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        User currentUser = userManager.getCurrentUser();
        modelAndView.addObject("user", currentUser);
    }

}
