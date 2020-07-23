package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AddedBook;

import javax.annotation.Nullable;
import java.sql.*;
import java.util.UUID;

import static com.epam.rd.izh.util.StringConstants.*;

public class BookRepository {
    public AddedBook getAddedBookByUUID (UUID id) throws SQLException, ClassNotFoundException {
        if (id != null) {
            ResultSet resultSet = null;
            Connection connection = null;
            Statement stmt = null;
            try {
                String requestSql = "SELECT title, author, genre, date, imgUrl FROM finalprojectdatabase.addedbook WHERE UUID = '" + id + "'";
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(requestSql);
                resultSet.next();
                return new AddedBook(resultSet.getString(TITLE), resultSet.getString(AUTHOR),
                        resultSet.getString(GENRE), resultSet.getString(WORDDATE), resultSet.getString(IMGURL), UUID.fromString(resultSet.getString(UUIDFORUSER)));
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

    public AddedBook getBookByTitleAndAuthor(String title, String author) throws SQLException {
        if (title != null && author != null){
            Connection connection = null;
            Statement stmt = null;
            ResultSet resultSet = null;
            try {
                String requestSql = "SELECT title, author, genre, date, imgUrl, UUID FROM finalprojectdatabase.addedbook WHERE `title` = '" + title + "' AND `author` = '"+ author + "'";
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(requestSql);
                resultSet.next();
                return new AddedBook(resultSet.getString(TITLE), resultSet.getString(AUTHOR),
                        resultSet.getString(GENRE), resultSet.getString(WORDDATE), resultSet.getString(IMGURL), UUID.fromString(resultSet.getString(UUIDFORUSER)));
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
            try {
                String requestSql = "INSERT INTO finalprojectdatabase.addedbook(title, author, genre, date, imgUrl, UUID) VALUES ('" + book.getTitle() + "', '" + book.getAuthor() + "', '" + book.getGenre() + "', '" + book.getYear() + "', '" + book.getImgUrl() + "', '" + UUID.randomUUID().toString() + "')";
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

    public boolean editBook(AddedBook newBook) {
        if (newBook != null) {
            Connection connection = null;
            Statement stmt = null;
            try {
                String requestSql = "UPDATE finalprojectdatabase.addedbook SET title = '" + newBook.getTitle() + "', author = '" + newBook.getAuthor() + "', genre = '" + newBook.getGenre() + "', date = '" + newBook.getYear() + "', imgUrl = '" + newBook.getImgUrl() + "' WHERE UUID = '" + newBook.getId().toString() + "'";
                try {
                    connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                    stmt = connection.createStatement();
                    stmt.executeUpdate(requestSql);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return true;
            } finally {

                    try {
                        if (stmt != null) {
                        stmt.close();
                        }
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        return false;
    }

    public boolean deleteBook(UUID uuid) throws SQLException, ClassNotFoundException {
        if (uuid != null) {
            Connection connection = null;
            Statement stmt = null;
            try {
                String requestSql = "DELETE FROM finalprojectdatabase.addedbook WHERE UUID = '" + uuid.toString() + "'";
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
