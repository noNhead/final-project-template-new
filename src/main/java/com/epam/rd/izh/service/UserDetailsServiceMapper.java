package com.epam.rd.izh.service;

import com.epam.rd.izh.entity.AuthorizedUser;
import com.epam.rd.izh.repository.UserRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

  private static Logger LOGGER;
  private final UserRepository userRepository = new UserRepository();

  /**
   * Возвращает сущность пользователя
   */
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

    AuthorizedUser authorizedUserDto = null;
    authorizedUserDto = userRepository.getAuthorizedUserByLogin(login);
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
    //String registerValidateResult = UserValidate.Validate(user);
    //if (registerValidateResult == null) {
    //  return false;
    //}
    user.setRole("user");
    System.out.println("Роль установлена");
    user.setId(UUID.randomUUID());
    System.out.println("UUID установлен");
    System.out.println(user.toString());
    userRepository.addAuthorizedUser(user);
    return true;
  }

  /**
   * Изменяет пароль
   */
  public boolean newChangePass(String username, String password){
    return userRepository.editAuthorizedUser(username, PASSWORD, password);
  }

  /**
   * Удаляет пользователя
   */
  public boolean DeleteUser(String username){
    return userRepository.deleteAuthorizedUser(username);
  }

}
