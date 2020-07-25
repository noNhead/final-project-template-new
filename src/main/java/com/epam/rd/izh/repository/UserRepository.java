package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AuthorizedUser;

import java.sql.*;
import java.util.UUID;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.springframework.stereotype.Repository;

import static com.epam.rd.izh.util.StringConstants.*;

@Repository
public class UserRepository {

  @Nullable
  public AuthorizedUser getAuthorizedUserByLogin(@Nonnull String login) throws SQLException {
    Connection connection = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      String sqlRequest = "SELECT id, login, password, role, UUID FROM finalprojectdatabase.autorizeduser WHERE login = '" + login + "';";
      connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
      stmt = connection.createStatement();
      rs = stmt.executeQuery(sqlRequest);
      if (rs.next()) {
        return new AuthorizedUser(rs.getString(LOGIN), rs.getString(PASSWORD), rs.getString(ROLE), UUID.fromString(rs.getString(UUIDFORUSER)));
      } else {
        return null;
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
  }

  public boolean addAuthorizedUser(@Nullable AuthorizedUser user) throws SQLException {
    if (user != null) {
      Connection connection = null;
      Statement stmt = null;
      try {
        String sqlRequest = "INSERT INTO finalprojectdatabase.autorizeduser(login, password, role, UUID) VALUES ('"+ user.getLogin() + "','" + user.getPassword() + "','"+ user.getRole() +"', '" + user.getId().toString() + "')";
        connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
        stmt = connection.createStatement();
        stmt.executeUpdate(sqlRequest);
        return true;
      } finally {
        if (stmt != null) {
          stmt.close();
        }
        if (connection != null) {
          connection.close();
        }
      }
    } else {
      return false;
    }
  }

  public boolean editAuthorizedUser(@Nullable String login, String column, @Nullable String newString) throws SQLException {
    Connection connection = null;
    Statement stmt = null;
    if (login != null && newString != null) {
      try {
        String sqlRequest = "UPDATE finalprojectdatabase.autorizeduser SET " + column + " = " + newString + "WHERE login = " + login;
        connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
        stmt = connection.createStatement();
        stmt.executeUpdate(sqlRequest);
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
  public boolean deleteAuthorizedUser(@Nullable String login) throws SQLException {
    Connection connection = null;
    Statement stmt = null;
    if (login != null) {
      try {
        String sqlRequest = "DELETE FROM finalprojectdatabase.autorizeduser(id, login, password, role) WHERE login = " + login;
        connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
        stmt = connection.createStatement();
        stmt.executeUpdate(sqlRequest);
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
