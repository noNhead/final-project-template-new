package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.User;

import java.util.Optional;

public interface IUserService {

    void addNewUser(User user);

    Optional<User> findUserByLogin(String login);

    boolean authorize(User authorizedUser);
}
