package com.epam.rd.izh.repository;

import org.springframework.stereotype.Repository;

import java.io.File;

import static com.epam.rd.izh.util.StringConstants.BOOKPATH;

@Repository
public class FileRepository {
    /**
     * Возвращает путь до текстового файла, по имени и автору
     *
     * @param title Название
     * @param author Автор
     * @return Возвращает текстовый файл
     */
    public File getBookByAuthorAndTitle(String title, String author){
        char literal = author.charAt(0);
        return new File(BOOKPATH + literal + "/" + author + "/" + title + "/" + title + ".fb2.zip");
    }

}
