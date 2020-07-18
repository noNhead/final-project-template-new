package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AuthorizedUser;

import java.sql.*;
import java.util.UUID;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.stereotype.Repository;

import static com.epam.rd.izh.util.StringConstants.*;

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
    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      String sqlRequest = "SELECT id, login, password, role, UUID FROM finalprojectdatabase.autorizeduser WHERE login = '" + login + "';";
      connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
      stmt = connection.createStatement();
      rs = stmt.executeQuery(sqlRequest);
      rs.next();
      if (rs != null) {
        return new AuthorizedUser(rs.getString(NAME), rs.getString(PASSWORD), rs.getString(ROLE), UUID.fromString(rs.getString(UUIDFORUSER)));
      }
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmt != null) {
        stmt.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
    return null;
  }

  public boolean addAuthorizedUser(@Nullable AuthorizedUser user) throws SQLException, ClassNotFoundException {
    Connection connection = null;
    Statement stmt = null;
    if (user != null) {
      try {
        String sqlRequest = String.format("INSERT INTO finalprojectdatabase.autorizeduser(login, password, role, UUID) VALUES (%s','%s','%s', '%s')", user.getLogin(), user.getPassword(), user.getRole(), user.getId().toString());
        connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
        stmt = connection.createStatement();
        stmt.executeQuery(sqlRequest);
        SqlConn.getStatement().executeQuery(sqlRequest);
        return true;
      } finally {
        if (stmt != null) {
          stmt.close();
        }
        if (connection != null) {
          connection.close();
        }
      }
    }
    return false;
  }

  public boolean editAutorizedUser(@Nullable String login, String column, @Nullable String newString) throws SQLException, ClassNotFoundException {
    Connection connection = null;
    Statement stmt = null;
    if (login != null && newString != null) {
      try {
        String sqlRequest = "UDPATE finalprojectdatabase.autorizeduser SET " + column + " = " + newString + "WHERE login = " + login;
        connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
        stmt = connection.createStatement();
        stmt.executeQuery(sqlRequest);
        return true;
      } finally {
        if (stmt != null) {
          stmt.close();
        }
        if (connection != null) {
          connection.close();
        }
      }
    }
    return false;
  }
  public boolean deleteAutorizedUser(@Nullable String login) throws SQLException, ClassNotFoundException {
    Connection connection = null;
    Statement stmt = null;
    if (login != null) {
      try {
        String sqlRequest = "DELETE FROM finalprojectdatabase.autorizeduser(id, login, password, role) WHERE login = " + login;
        connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
        stmt = connection.createStatement();
        stmt.executeQuery(sqlRequest);
        return true;
      } finally {
        if (stmt != null) {
          stmt.close();
        }
        if (connection != null) {
          connection.close();
        }
      }
    }
    return false;
  }
}
