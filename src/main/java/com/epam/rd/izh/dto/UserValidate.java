package com.epam.rd.izh.dto;

import com.epam.rd.izh.entity.AuthorizedUser;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public class UserValidate {
    public static String Validate(@Valid AuthorizedUser registeredUser){
        if (registeredUser.getLogin().length() <= 6) {
            return "Логин должен быть больше 7 символов";
        } else if (registeredUser.getLogin().length() > 254) {
            return "Логин слишком длинный";
        } else if (registeredUser.getPassword().length() <= 6) {
            return "Пароль должен быть больше 6 символов";
        } else if (registeredUser.getPassword().length() > 64) {
            return "Пароль слишком длинный";
        } else {
            return null;
        }
    }
}
