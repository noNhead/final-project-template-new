package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AddedBook;

import javax.annotation.Nullable;
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
                            resultSet.getString(GENRE), resultSet.getString(WORDDATE), resultSet.getString(IMGURL));
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

        public boolean addBook(@Nullable AddedBook book) throws SQLException, ClassNotFoundException {
        if (book != null) {
            Connection connection = null;
            Statement stmt = null;
            System.out.println("1");
            try {
                String requestSql = "INSERT INTO finalprojectdatabase.addedbook(name, author, genre, date, imgUrl, UUID) VALUES ('" + book.getTitle() + "', '" + book.getAuthor() + "', '" + book.getGenre() + "', '" + book.getYear() + "', '" + book.getImgUrl() + "', '" + book.getId() + "')";
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                stmt.executeUpdate(requestSql);
                System.out.println("2");
                return true;
            } finally {
                if (stmt != null) {
                    stmt.close();
                    System.out.println("3");
                }
                if (connection != null) {
                    connection.close();
                    System.out.println("4");
                }
            }
        }
        System.out.println("5");
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
                stmt.executeUpdate(requestSql);
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
                stmt.executeUpdate(requestSql);
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
