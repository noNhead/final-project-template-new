package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.UserValidate;
import com.epam.rd.izh.entity.AuthorizedUser;
import com.epam.rd.izh.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.epam.rd.izh.util.StringConstants.PASSWORD;

/**
 * Для авторизации через Spring security требуется реализация интерфейса UserDetailsService и его метода
 * .loadUserByUsername(String login).
 */

@Service
public class UserDetailsServiceMapper implements UserDetailsService {

  private static final Logger LOGGER = LogManager.getLogger();

  @Autowired
  UserRepository userRepository = new UserRepository();

  /**
   * Возвращает сущность пользователя
   * @param login
   * @return
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

    AuthorizedUser authorizedUserDto = null;
    try {
      authorizedUserDto = userRepository.getAuthorizedUserByLogin(login);
    } catch (SQLException throwables) {
      LOGGER.fatal(throwables.getMessage());
    }
    Set<GrantedAuthority> roles = new HashSet<>();
    roles.add(new SimpleGrantedAuthority(authorizedUserDto.getRole()));

    return new User(
        authorizedUserDto.getLogin(),
        authorizedUserDto.getPassword(),
        roles
    );
  }

  /**
   * Проверяет логин пароль перед отправкой запроса в базу, наполняет сущность ролью
   * Шифрует пароль.
   * @param user сущность пользователя
   * @return возвращает true если пользователь успешно добавлен
   */
  public boolean userRegistration(AuthorizedUser user) {
    String registerValidateResult = UserValidate.Validate(user);
    if (registerValidateResult == null) {
      return false;
    }
    user.setRole("user");
    user.setId(UUID.randomUUID());
    try {
      userRepository.addAuthorizedUser(user);
    } catch (SQLException throwables) {
      LOGGER.fatal(throwables.getMessage());
      return false;
    }
    return true;
  }

  /**
   * Изменяет пароль
   * @param username
   * @param password
   * @return
   */
  public boolean newChangePass(String username, String password){
    try {
      return userRepository.editAuthorizedUser(username, PASSWORD, password);
    } catch (SQLException throwables) {
      LOGGER.fatal(throwables.getMessage());
    }
    return false;
  }

  /**
   * Удаляет пользователя
   * @param username
   * @return
   */
  public boolean DeleteUser(String username){
    try {
      return userRepository.deleteAuthorizedUser(username);
    } catch (SQLException throwables) {
      LOGGER.fatal(throwables.getMessage());
    }
    return false;
  }

}
