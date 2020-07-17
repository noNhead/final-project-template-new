package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AuthorizedUser;

import java.sql.ResultSet;
import java.sql.SQLException;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.springframework.stereotype.Repository;

/**
 * Данный репозиторий хранит список зарегистрированных пользователей;
 * На данный момент он представляет из себя коллекцию List<AuthorizedUser> и методы доступа к ней;
 *
 * Необходимо превратить данный репозиторий в DAO класс:
 * Создать базу данных, подключить ее к приложению, сделать CRUD операции (или их часть) для доступа
 * к хранящимся сущностям.
 * Создать другие DAO классы для хранения бизнес-сущностей выбранной темы финального проекта в этом же пакете.
 */

@Repository
public class UserRepository {
  /**
   * В данном методе использована библиотека Stream API:
   * .filter проверяет каждый элемент коллекции на удовлетворение условия .equals(login), в случае, если совпадающий
   * элемент будет найдет, он будет возвращен методом .findFirst(). Если в коллекции не будет найдет удовлетворяющий
   * условию элемент, методом .orElse(null) будет возвращен null.
   * Допускается использовать вместо  Stream API стандартные циклы For и While.
   *
   * аннотации @Nullable и @Nonnull расставляются над возвращающими не примитивные значения методами и передаваемыми
   * в метод аргументами.
   */

  @Nullable
  public AuthorizedUser getAuthorizedUserByLogin(@Nonnull String login) throws SQLException, ClassNotFoundException {
    String sqlRequest = "SELECT id, login, password, role FROM finalprojectdatabase.autorizeduser WHERE login = '" + login + "';";
    ResultSet resultSet = SqlConn.getStatement().executeQuery(sqlRequest);
    resultSet.next();
    int id = resultSet.getInt("id");
    String loginSql = resultSet.getString("name");
    String passwordSql = resultSet.getString("password");
    String roleSql = resultSet.getString("role");

    AuthorizedUser authorizedUser = null;
    authorizedUser.setLogin(loginSql);
    authorizedUser.setPassword(passwordSql);
    authorizedUser.setRole(roleSql);
    return authorizedUser;
  }

  public boolean addAuthorizedUser(@Nullable AuthorizedUser user) throws SQLException, ClassNotFoundException {
    if (user != null) {
      String sqlRequest = String.format("INSERT INTO finalprojectdatabase.autorizeduser(login, password, role) VALUES (%s','%s','%s')", user.getLogin(), user.getPassword(), user.getRole());
      SqlConn.getStatement().executeQuery(sqlRequest);
      return true;
    }
    return false;
  }

  public boolean editAutorizedUser(@Nullable String login, String column, @Nullable String newString) throws SQLException, ClassNotFoundException {
    if (login != null && newString != null) {
      AuthorizedUser user = getAuthorizedUserByLogin(login);
      String sqlRequest = "UDPATE finalprojectdatabase.autorizeduser SET " + column + " = " + newString + "WHERE login = " + login;
      SqlConn.getStatement().executeQuery(sqlRequest);
      return true;
    }
    return false;
  }
  public boolean deleteAutorizedUser(@Nullable String login) throws SQLException, ClassNotFoundException {
    if (login != null) {
      String sqlRequest = "DELETE FROM finalprojectdatabase.autorizeduser(id, login, password, role) WHERE login = " + login;
      SqlConn.getStatement().executeQuery(sqlRequest);
      return true;
    }
    return false;
  }
}
