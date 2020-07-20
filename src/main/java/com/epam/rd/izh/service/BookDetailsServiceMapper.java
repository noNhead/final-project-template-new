package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.BookValidate;
import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.repository.BookRepository;
import java.sql.SQLException;

public class BookDetailsServiceMapper {
    BookRepository bookRepository;
    public boolean BookAdding(AddedBook book){
        String bookValidateResult = BookValidate.Validate(book);
        if (bookValidateResult != null) {
            return false;
        } else {
            try {
                bookRepository.addBook(book);
                return true;
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
    }
}
