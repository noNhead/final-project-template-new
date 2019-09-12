package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.User;
import com.epam.rd.izh.exception.AuthorizationException;
import com.epam.rd.izh.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private List<User> users = new ArrayList<>();

    @Autowired
    private IUserManager userManager;

    @Override
    public void addNewUser(User registeringUser) {
        Optional<User> userOpt = findUserByLogin(registeringUser.getLogin());

        if (userOpt.isPresent()) {
            throw new RegistrationException("Такой пользователь уже существует");
        }

        users.add(registeringUser);

        userManager.setCurrentUser(registeringUser);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        Assert.notNull(login, "login не может быть равен null");
        return users.stream().filter(user -> user.getLogin().equalsIgnoreCase(login)).findFirst();
    }

    @Override
    public boolean authorize(User authorizingUser) {
        Optional<User> userOpt = findUserByLogin(authorizingUser.getLogin());

        User user = userOpt.orElseThrow(() -> new AuthorizationException("Такой пользователь не найден"));

        if (!user.getPassword().equalsIgnoreCase(authorizingUser.getPassword())) {
            throw new AuthorizationException("Неверный пароль");
        }

        userManager.setCurrentUser(user);

        return true;
    }
}
