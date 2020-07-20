package com.epam.rd.izh.dto;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.repository.BookRepository;

import java.sql.SQLException;

public class BookChanger {
    BookRepository bookRepository;
    public AddedBook editBook(AddedBook book, String column, String newString) {
        try {
            if (!bookRepository.editBook(book.getId(), column, newString)) {
                return null;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }
}
