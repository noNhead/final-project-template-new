package com.epam.rd.izh.dto;

import com.epam.rd.izh.entity.AddedBook;

import java.util.regex.Pattern;

public class BookValidate {
    /**
     * Проверка данных при регистрации книги
     */
    public static String Validate(AddedBook book) {
        if (book.getTitle().length() >= 255) {
            return "Слишком длинное название";
        } else if (book.getAuthor().length() >= 255) {
            return "Слишком длинное имя автора";
        } else if (!Pattern.matches("^\\d{4}$", book.getYear())) {
            return "Год должен состоять из 4 цифр";
        }
        return null;
    }
}
