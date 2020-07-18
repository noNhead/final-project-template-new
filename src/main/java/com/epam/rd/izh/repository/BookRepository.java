package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AddedBook;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

import static com.epam.rd.izh.util.StringConstants.*;

public class BookRepository {
    public AddedBook getAddedBookRepository (UUID id) throws SQLException, ClassNotFoundException {
            if (id != null) {
                ResultSet resultSet = null;
                Connection connection = null;
                Statement stmt = null;
                try {
                    String requestSql = "SELECT name, author, genre, date, imgUrl FROM finalprojectdatabase.addedbook WHERE UUID = '" + id + "'";
                    connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                    stmt = connection.createStatement();
                    resultSet = stmt.executeQuery(requestSql);
                    resultSet.next();
                    return new AddedBook(resultSet.getString(NAME), resultSet.getString(AUTHOR),
                            resultSet.getString(GENRE), resultSet.getDate(WORDDATE), resultSet.getString(IMGURL), id);
                } finally {
                  if (resultSet != null) {
                      resultSet.close();
                  }
                  if (stmt != null) {
                      stmt.close();
                  }
                  if (connection != null) {
                      connection.close();

                  }
                }
            }
            return null;
        }

        public boolean addBook(AddedBook book) throws SQLException, ClassNotFoundException {
        if (book != null) {
            Connection connection = null;
            Statement stmt = null;
            try {
                String requestSql = "INSERT INTO finalprojectdatabase.addedbook(name, author, genre, date, imgUrl, UUID) VALUES ('" + book.getName() + "', '" + book.getAuthor() + "', '" + book.getGenre() + "', '" + book.getDate() + "', '" + book.getImgUrl() + "', '" + book.getId() + "')";
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                stmt.executeQuery(requestSql);
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

    public boolean editBook(UUID id, String column, String newString) throws SQLException, ClassNotFoundException {
        if (id != null) {
            Connection connection = null;
            Statement stmt = null;
            try {
                String requestSql = "UDPATE finalprojectdatabase.addedbook SET " + column + " = " + newString + " WHERE UUID = " + id;
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                stmt.executeQuery(requestSql);
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

    public boolean deleteBook(UUID id) throws SQLException, ClassNotFoundException {
        if (id != null) {
            Connection connection = null;
            Statement stmt = null;
            try {
                String requestSql = "DELETE FROM finalprojectdatabase.autorizeduser(id, name, author, genre, date, imgUrl, UUID) WHERE login = " + id;
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                stmt.executeQuery(requestSql);
                return true;
            } finally {
                if (connection != null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
        }
        return false;
    }
}
