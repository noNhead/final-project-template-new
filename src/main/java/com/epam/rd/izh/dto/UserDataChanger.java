package com.epam.rd.izh.dto;

import com.epam.rd.izh.entity.AuthorizedUser;
import com.epam.rd.izh.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

public class UserDataChanger {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    /**
     * Наполняет сущность ролью и шифрует пароль
     * @param user сущность
     * @return Возвращает сущность
     */
    public AuthorizedUser FirstUserCreate(AuthorizedUser user){
        user.setRole("User");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    /**
     * Редактирует одно из полей сущностей
     * @param user Сущность
     * @param column Поле, которое нужно отредактировать
     * @param newString Строка, на которую нужно заменить старую
     * @return В случаи успеха возвращает обновлённую сущность из базы, иначе null
     */
    public AuthorizedUser editUser(AuthorizedUser user, String column, String newString) {
        try {
            if(!userRepository.editAuthorizedUser(user.getLogin(), column, newString)){
                return null;
            }
            user = userRepository.getAuthorizedUserByLogin(user.getLogin());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public boolean userDelete(AuthorizedUser user){
        try {
            userRepository.deleteAuthorizedUser(user.getLogin());
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
