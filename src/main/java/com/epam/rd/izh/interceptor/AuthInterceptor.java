package com.epam.rd.izh.interceptor;

import com.epam.rd.izh.dto.User;
import com.epam.rd.izh.service.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private IUserManager userManager;

    /**
     * В текущем методе происходит проверка наличия объекта текущего пользователя {@link User} в менеджере сессионных
     * пользователей {@link IUserManager}.
     * <p>
     * Если объект равен null, значит пользователь не проходил через процесс аутентификации, а значит он не авторизован.
     * При этом происходит редирект на страницу логина.
     * 
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User currentUser = userManager.getCurrentUser();

        if (currentUser == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

}
