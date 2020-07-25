package com.epam.rd.izh.repository;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static com.epam.rd.izh.util.StringConstants.BOOKPATH;

@Repository
public class PackingBooks {

    FileRepository fileRepository = new FileRepository();
    /**
     * Упаковка книг в определённой директории
     *
     * @return возвращает true если упаковка закончилась успешно
     */
    public boolean packingBooks(){
        String path = "1.zip";
        File fileDirectory = new File(BOOKPATH);
        File outputZipFile = new File(BOOKPATH+"1.zip");
        outputZipFile.getParentFile().mkdirs();


        String inputDirPath = fileDirectory.getAbsolutePath();
        byte[] buffer = new byte[1024];

        FileOutputStream fileOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        try {
            List<File> allFilesInDerictory = fileRepository.getAllFileInDirectory(path);

            for (File i: allFilesInDerictory) {
                String filePath = i.getAbsolutePath();
                String entryName = filePath.substring(inputDirPath.length()+1);

                ZipEntry zipEntry = new ZipEntry(entryName);
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.setMethod(ZipEntry.DEFLATED);

                FileInputStream fileInputStream = new FileInputStream(filePath);

                int length;
                while((length = fileInputStream.read(buffer)) > 0){
                    zipOutputStream.write(buffer, 0, length);
                }
                fileInputStream.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                zipOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    /**
     * Распаковка и получение всех файлов архива
     *
     * @param path путь до архива
     * @return Возвращает список книг в виде List<File>
     */
    public List<File> unpackingBooks(String path){
        ZipInputStream zipInputStream = null;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(BOOKPATH + path));
                ZipEntry zipEntry;

                while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                    if (zipEntry.isDirectory()) {
                        System.out.println("Directory: ");
                    } else {
                        System.out.println("File: ");
                    }
                    System.out.println(zipEntry.getName());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                zipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Распаковка и получение всех путей файлов архива path
     *
     * @param path путь до архива
     * @return Возвращает список путей до книг в виде List<String>
     */
    public List<String> getPathBooksInPack(String path){
        ZipInputStream zipInputStream = null;
        try {
            try {
                zipInputStream = new ZipInputStream(new FileInputStream(BOOKPATH + path));
                ZipEntry zipEntry;

                while ((zipEntry = zipInputStream.getNextEntry()) != null) {

                    System.out.println(zipEntry.getName());

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                zipInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Распаковывает одну книгу
     *
     * @param path путь до архива
     * @param title название книги в архиве
     * @return Возвращает File книгу
     */
    public File unpackingBook(String path, String title){
        return null;
    }
}
