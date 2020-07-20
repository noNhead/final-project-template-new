package com.epam.rd.izh.dto;

import com.epam.rd.izh.entity.AuthorizedUser;
import com.epam.rd.izh.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLException;

public class UserDataChanger {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public AuthorizedUser FirstUserCreate(AuthorizedUser user){
        user.setRole("User");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public AuthorizedUser editUser(AuthorizedUser user, String column, String newString) {
        try {
            if(!userRepository.editAuthorizedUser(user.getLogin(), column, newString)){
                return null;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
