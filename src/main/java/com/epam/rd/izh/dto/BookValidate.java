package com.epam.rd.izh.dto;

import com.epam.rd.izh.entity.AddedBook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

public class BookValidate {
    public static String Validate(AddedBook book) {
        if (book.getTitle().length() >= 255) {
            return "Слишком длинное название";
        } else if (book.getAuthor().length() >= 255) {
            return "Слишком длинное имя автора";
        } else if (!Pattern.matches("^\\d{4}$", book.getYear())) {
            return "Год должен состоять из 4 цифр";
        } else {
            Image image = null;
            URL url = null;
            try {
                url = new URL("https://cdn1.ozone.ru/s3/multimedia-p/wc1200/6008237689.jpg");
                image = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
                return "Не получается обработать изображение";
            }
        }
        return null;
    }
}
