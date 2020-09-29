package com.epam.rd.izh.repository;

import com.epam.rd.izh.entity.AddedBook;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Nullable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.epam.rd.izh.util.StringConstants.*;

public class BookRepository {
    JdbcTemplate jdbcTemplate = new JdbcTemplate();

    /**
     * Поиск книги по Названию и пользователю в базе
     */
    @Nullable
    public AddedBook getBookByTitleAndAuthor(String title, String author) {
        return (AddedBook) jdbcTemplate.query("SELECT * FROM finalprojectdatabase.addedbook WHERE `title` = ?'" + title + "' AND `author` = ?'"+ author + "'",
                (resultSet, rowNum) -> new AddedBook(
                        resultSet.getString(TITLE),
                        resultSet.getString(AUTHOR),
                        resultSet.getString(GENRE),
                        resultSet.getString(WORDDATE),
                        UUID.fromString(resultSet.getString(UUIDFORUSER))));

    }

    /**
     * Добавлеяет книгу в базу
     */
    public boolean addBook(@Nullable AddedBook book) {
        LocalDateTime date = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(date);
        jdbcTemplate.execute("INSERT INTO finalprojectdatabase.addedbook(title, author, genre, date, UUID, dateAdded) VALUES ('?" + book.getTitle() + "', '?" + book.getAuthor() + "', '?" + book.getGenre() + "', '?" + book.getYear() + "',  '?" + UUID.randomUUID().toString() + "', '?" + timestamp + "')");
        return false;
    }

    /**
     * Редактирует книгу в базе
     */
    public boolean editBook(AddedBook newBook) {
        jdbcTemplate.execute("UPDATE finalprojectdatabase.addedbook SET title = '" + newBook.getTitle() + "', author = '" + newBook.getAuthor() + "', genre = '" + newBook.getGenre() + "', date = '" + newBook.getYear() + "' WHERE UUID = '" + newBook.getId().toString() + "'");
        return true;
    }

    /**
     * Удаляет книгу из базы
     */
    public boolean deleteBook(UUID uuid) {
        jdbcTemplate.execute("DELETE FROM finalprojectdatabase.addedbook WHERE UUID = '" + uuid.toString() + "'");
        return true;
    }

    /**
     * Ищет книги последние добавленные книги по количеству
     */
    public List<AddedBook> getLastAddedBookByTimestamp(int cardinalityLimit) {
        return jdbcTemplate.query("SELECT * FROM finalprojectdatabase.addedbook ORDER BY dateAdded DESC LIMIT ?" + cardinalityLimit, (resultSet, rowNum) ->
                new AddedBook(resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("genre"),
                resultSet.getString("date"),
                UUID.fromString(resultSet.getString("UUID"))));
    }

    /**
     * Ищет книги по заданном параметрам
     */
    @Nullable
    public List<AddedBook> searchBook(AddedBook book) {
        String requestSql = "SELECT * FROM finalprojectdatabase.addedbook WHERE ";
        boolean t = false; //t - title
        boolean a = false; //a - author
        boolean g = false; //g - genre
        if (!book.getTitle().equals("")) {
            requestSql += "title LIKE '%?" + book.getTitle() + "%'";
            t = true;
        }
        if (!book.getAuthor().equals("")) {
            if (t) {
                requestSql+=" AND ?";
            }
            requestSql += "author LIKE '%" + book.getAuthor() + "%'";
            a = true;
        }
        if (!book.getGenre().equals("")) {
            if (a) {
                requestSql+=" AND ?";
            }
            requestSql += "genre LIKE '%" + book.getGenre() + "%'";
            g = true;
        }
        if (!book.getYear().equals("")) {
            if (g) {
                requestSql=" AND ?";
            }
            requestSql += "date LIKE '%" + book.getYear() + "%'";
        }

        return jdbcTemplate.query(requestSql, (resultSet, rowNum) -> new AddedBook(
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("genre"),
                resultSet.getString("date"),
                UUID.fromString(resultSet.getString("UUID"))));
    }
}

