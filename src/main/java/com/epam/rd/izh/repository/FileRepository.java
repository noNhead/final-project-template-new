package com.epam.rd.izh.repository;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.epam.rd.izh.util.StringConstants.BOOKPATH;

@Repository
public class FileRepository {
    /**
     * Рекурсивный поиск файлов в директории
     *
     * @param path директория
     * @return Возвращает список всех файлов директории
     */
    public List<File> getAllFileInDirectory(String path) {
        File file = new File(BOOKPATH + path);
        List<File> allFilesInDirectory = new ArrayList<>();
        File[] childFiles = file.listFiles();
        for (File i: childFiles) {
            if (file.isFile()){
                allFilesInDirectory.add(i);
            } else {
                List<File> files = this.getAllFileInDirectory(file.getPath());
                allFilesInDirectory.addAll(files);
            }
        }
        return allFilesInDirectory;
    }

    /**
     * Поиск всех файлов конкретной директории бнз захода в поддиректории
     * @param path путь до директории
     * @return возвращает список путей в виде List<String>
     */
    public List<String> getAllTitleInDirectory(String path) {
        File file = new File(BOOKPATH + path);
        List<String> allFilesPathInDirectory = new ArrayList<>();
        File[] childFiles = file.listFiles();
        for (File i : childFiles) {
            if (file.isFile()) {
                allFilesPathInDirectory.add(i.getName());
            }
        }
        return allFilesPathInDirectory;
    }

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

    /**
     * Возвращает путь до изображения книги
     *
     * @param author Автор
     * @param title Название
     * @return Возвращает строку - путь
     */
    public String getPathImageByTitleAndAuthor(String title, String author){
        char literal = author.charAt(0);
        return BOOKPATH + literal + "/" + title + "/" + title;
    }
}
