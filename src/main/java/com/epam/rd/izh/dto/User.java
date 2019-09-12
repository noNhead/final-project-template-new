package com.epam.rd.izh.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

    @NotEmpty(message="Логин должен быть заполнен")
    @Size(min = 3, message="Логин должен быть длиннее 3 символов")
    private String login;

    @NotEmpty(message="Логин должен быть заполнен")
    @Size(min = 5, message="Логин должен быть длиннее 5 символов")
    private String password;

    @NotEmpty(message="Имя должно быть заполнено")
    @Size(min = 3, max = 12, message="Имя должно быть длиннее 3 символов и меньше 12 символов")
    private String name;

    private String email;

    private String role;

    public User() {
        super();
    }

    public User(@NotEmpty(message = "Имя должно быть заполнено")
            @Size(min = 3, max = 12, message = "Имя должно быть длиннее 3 символов и меньше 12 символов") String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
