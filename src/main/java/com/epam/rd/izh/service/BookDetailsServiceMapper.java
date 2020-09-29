package com.epam.rd.izh.service;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.repository.BookRepository;
import com.epam.rd.izh.repository.FileRepository;
import com.epam.rd.izh.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

@Service
public class BookDetailsServiceMapper {
    private static final Logger LOGGER = LogManager.getLogger();
    private final BookRepository bookRepository = new BookRepository();
    private final UserRepository userRepository = new UserRepository();
    private final FileRepository fileRepository = new FileRepository();
    public static final int BUFFER_SIZE = 1024 * 1024 * 20;

    public BookDetailsServiceMapper() {
    }

    /**
     * Добавляет книгу
     *
     * @param book AddedBook
     * @return успех или нет
     */
    public boolean bookAdding(AddedBook book) {
        //String bookValidateResult = BookValidate.Validate(book);
        //if (bookValidateResult != null) {
        //    return false;
        //} else {
        return bookRepository.addBook(book);
        //}
    }

    /**
     * Изменяет параметры книги
     *
     * @param book    Выбранная книга с контроллера
     * @param newBook Новые данные для книги
     * @return true или false в зависимости от успеха операции
     */
    public boolean bookDataChange(AddedBook book, AddedBook newBook) {
        checkFieldBook(newBook);

        if (!newBook.getTitle().equals("")) {
            book.setTitle(newBook.getTitle());
        }
        if (!newBook.getAuthor().equals("")) {
            book.setAuthor(newBook.getAuthor());
        }
        if (!newBook.getGenre().equals("")) {
            book.setGenre(newBook.getGenre());
        }
        if (!newBook.getYear().equals("")) {
            book.setYear(newBook.getYear());
        }
        return bookRepository.editBook(book);
    }

    /**
     * Ищет добавленную книгу
     *
     * @param title  название
     * @param author автор
     * @return возвращает найденную книгу
     */
    public AddedBook getAddedBookByTitleAndAuthor(String title, String author) {
        AddedBook book = writeFieldForSearch(title, author);
        AddedBook gotBook = null;
        gotBook = bookRepository.getBookByTitleAndAuthor(book.getTitle(), book.getAuthor());
        return gotBook;
    }

    /**
     * Удаляет книгу
     *
     * @param authentication проверяет права
     * @param book           удаление книги
     * @return успех или нет
     */
    public boolean bookDelete(Authentication authentication, AddedBook book) {
        if (!Objects.equals(Objects.requireNonNull(userRepository.getAuthorizedUserByLogin(authentication.getName())).getRole(), "admin")) {
            return false;
        }
        return bookRepository.deleteBook(book.getId());
    }

    /**
     * Возвращает последние книги
     *
     * @param listSize длина списка
     * @return список
     */
    public List<AddedBook> lastBookAdded(int listSize) {
        return bookRepository.getLastAddedBookByTimestamp(listSize);
    }

    /**
     * Возвращает найденные книги
     *
     * @param tags искомые теги
     * @return Найденный список
     */
    public List<AddedBook> searchBook(String tags) {
        AddedBook book = tagsPars(tags);
        return bookRepository.searchBook(book);
    }

    /**
     * Проверяет поля AddedBook book, если находит null, заменяет на пустую строку
     *
     * @param book AddedBook с контроллера
     * @return возвращает книгу
     */
    private AddedBook checkFieldBook(AddedBook book) {
        book.setId();
        if (book.getTitle() == null) {
            book.setTitle("");
        }
        if (book.getAuthor() == null) {
            book.setAuthor("");
        }
        if (book.getGenre() == null) {
            book.setGenre("");
        }
        if (book.getYear() == null) {
            book.setYear("");
        }
        return book;
    }

    private AddedBook writeFieldForSearch(String title, String author) {
        AddedBook book = new AddedBook();
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }

    /**
     * Ищет добавленную книгу
     *
     * @param book искомое
     * @return возвращает найденную книгу
     */
    public AddedBook getAddedBook(AddedBook book) {
        AddedBook gotBook = null;
        gotBook = bookRepository.getBookByTitleAndAuthor(book.getTitle(), book.getAuthor());
        return gotBook;
    }

    /**
     * Скачивает
     * @param httpServletResponse Сервлет
     * @param title Название книги
     * @param author Автор книги
     * @return успешность
     */
    public boolean downloading(HttpServletResponse httpServletResponse, String title, String author) {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/zip");
        try {
            httpServletResponse.setHeader("Content-disposition", "attachment;filename=\"" + URLEncoder.encode(title, "UTF-8") + ".fb2.zip\"");
        } catch (UnsupportedEncodingException e) {
            LOGGER.fatal(e.getMessage());
        }
        File downloadFile = fileRepository.getBookByAuthorAndTitle(title, author);

        FileInputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = httpServletResponse.getOutputStream();
            fileInputStream = new FileInputStream(downloadFile);

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;

            while ((length = fileInputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            fileInputStream.close();
            outputStream.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            try {
                fileInputStream.close();
                outputStream.flush();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return false;
        }
    }

    /**
     * Дробление тегов для поиска
     * t - title, a - author, g - genre, d - date also пустая строка
     */
    public AddedBook tagsPars(String tags){
        AddedBook book = new AddedBook();
        StringBuilder string = new StringBuilder();
        char[] arr = tags.toCharArray();
        int i = 0;
        if (arr[i]=='t' && arr[i+1]=='=' && arr[i+2] != 'a' && arr[i+3] != '='){
            i+=2;
            while(arr[i] != 'a' && arr[i+1] != '='){
                string.append(arr[i]);
                i++;
                System.out.println(string);
            }
            book.setTitle(String.valueOf(string));
        } else {
            i+=2;
            book.setTitle("");
        }
        if (arr[i]=='a' && arr[i+1]=='=' && arr[i+2] != 'g' && arr[i+3] != '='){
            i+=2;
            while(arr[i] != 'g' && arr[i+1] != '='){
                string.append(arr[i]);
                i++;
                System.out.println(string);
            }
            book.setAuthor(String.valueOf(string));
        } else {
            i+=2;
            book.setAuthor("");
        }
        if (arr[i]=='g' && arr[i+1]=='=' && arr[i+2] != 'd' && arr[i+3] != '='){
            i+=2;
            while(arr[i] != 'd' && arr[i+1] != '='){
                string.append(arr[i]);
                i++;
                System.out.println(string);
            }
            book.setGenre(String.valueOf(string));
        } else {
            i+=2;
            book.setGenre("");
        }
        if (arr[i]=='d' && arr[i+1]=='=' && arr[i+2] != '&' && (i+2) != arr.length-1){
            i+=2;
            while(arr[i] != '&' && i != arr.length-1){
                string.append(arr[i]);
                i++;
                System.out.println(string);
            }
            book.setYear(String.valueOf(string));
        } else {
            book.setYear("");
        }
        return book;
    }
}