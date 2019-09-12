package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.User;

public interface IUserManager {

    User getCurrentUser();

    void setCurrentUser(User currentUser);
}
