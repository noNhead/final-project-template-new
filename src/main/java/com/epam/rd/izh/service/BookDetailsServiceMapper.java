package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.BookValidate;
import com.epam.rd.izh.entity.AddedBook;

import java.io.IOException;

public class BookDetailsServiceMapper {
    public boolean BookAdding(AddedBook book){
        String bookValidateResult = BookValidate.Validate(book);
        if (bookValidateResult != null) {
            return false;
        }
        return false;
    }
}
