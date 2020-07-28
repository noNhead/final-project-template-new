package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AddedBook;

import javax.annotation.Nullable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.epam.rd.izh.util.StringConstants.*;

public class BookRepository {
    @Nullable
    public AddedBook getBookByTitleAndAuthor(String title, String author) throws SQLException {
        if (title != null && author != null){
            Connection connection = null;
            Statement stmt = null;
            ResultSet resultSet = null;
            try {
                String requestSql = "SELECT * FROM finalprojectdatabase.addedbook WHERE `title` = '" + title + "' AND `author` = '"+ author + "'";
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(requestSql);
                resultSet.next();
                return new AddedBook(
                        resultSet.getString(TITLE),
                        resultSet.getString(AUTHOR),
                        resultSet.getString(GENRE),
                        resultSet.getString(WORDDATE),
                        UUID.fromString(resultSet.getString(UUIDFORUSER)));
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

    public boolean addBook(@Nullable AddedBook book) throws SQLException {
        if (book != null) {
            Connection connection = null;
            Statement stmt = null;
            try {
                LocalDateTime date = LocalDateTime.now();
                Timestamp timestamp = Timestamp.valueOf(date);
                String requestSql = "INSERT INTO finalprojectdatabase.addedbook(title, author, genre, date, UUID, dateAdded) VALUES ('" + book.getTitle() + "', '" + book.getAuthor() + "', '" + book.getGenre() + "', '" + book.getYear() + "',  '" + UUID.randomUUID().toString() + "', '" + timestamp + "')";
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
                String requestSql = "UPDATE finalprojectdatabase.addedbook SET title = '" + newBook.getTitle() + "', author = '" + newBook.getAuthor() + "', genre = '" + newBook.getGenre() + "', date = '" + newBook.getYear() + "' WHERE UUID = '" + newBook.getId().toString() + "'";
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

    public boolean deleteBook(UUID uuid) throws SQLException {
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

    public List<AddedBook> getLastAddedBookByTimestamp(int cardinalityLimit) throws SQLException {
        if (cardinalityLimit > 0) {
            Connection connection = null;
            Statement stmt = null;
            ResultSet resultSet = null;
            try {
                String requestSql = "SELECT * FROM finalprojectdatabase.addedbook ORDER BY dateAdded DESC LIMIT " + cardinalityLimit;
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(requestSql);
                List<AddedBook> titleAndAuthor = new ArrayList<>();
                while (resultSet.next()) {
                    titleAndAuthor.add(
                            new AddedBook(resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("genre"),
                            resultSet.getString("date"),
                            UUID.fromString(resultSet.getString("UUID"))));
                }
                return titleAndAuthor;
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }
                if(connection!= null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
        }
        return null;
    }

    @Nullable
    public List<AddedBook> searchBook(AddedBook book) throws SQLException {
        if (book != null) {
            Connection connection = null;
            Statement stmt = null;
            ResultSet resultSet = null;
            String requestSql = "SELECT * FROM finalprojectdatabase.addedbook WHERE ";
            try {
                boolean t = false;
                boolean a = false;
                boolean g = false;
                if (!book.getTitle().equals("0")) {
                    requestSql += "title LIKE '%" + book.getTitle() + "%'";
                    t = true;
                }
                if (!book.getAuthor().equals("0")) {
                    if (t) {
                        requestSql+=" AND ";
                    }
                    requestSql += "author LIKE '%" + book.getAuthor() + "%'";
                    a = true;
                }
                if (!book.getGenre().equals("0")) {
                    if (a) {
                        requestSql+=" AND ";
                    }
                    requestSql += "genre LIKE '%" + book.getGenre() + "%'";
                    g = true;
                }
                if (!book.getYear().equals("0")) {
                    if (g) {
                        requestSql=" AND ";
                    }
                    requestSql += "date LIKE '%" + book.getYear() + "%'";
                }
                connection = DriverManager.getConnection(URL_DATABASE, ROOT_LOGIN, ROOT_PASS);
                stmt = connection.createStatement();
                resultSet = stmt.executeQuery(requestSql);
                List<AddedBook> bookList = new ArrayList<>();
                while (resultSet.next()) {
                    bookList.add(new AddedBook(
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("genre"),
                            resultSet.getString("date"),
                            UUID.fromString(resultSet.getString("UUID"))));

                }
                return bookList;
            } finally {
                System.out.println(requestSql);
                if (resultSet != null) {
                    resultSet.close();
                }
                if(connection!= null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            }
        }
        return null;
    }
}

