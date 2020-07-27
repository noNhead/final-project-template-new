package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.BookValidate;
import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.repository.BookRepository;
import com.epam.rd.izh.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class BookDetailsServiceMapper {

    private final BookRepository bookRepository = new BookRepository();
    private final UserRepository userRepository = new UserRepository();

    public boolean BookAdding(AddedBook book){
        String bookValidateResult = BookValidate.Validate(book);
        if (bookValidateResult != null) {
            return false;
        } else {
            try {
                bookRepository.addBook(book);
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
        }
    }

    public boolean BookDataChange(AddedBook book, AddedBook newBook) {
        if(!newBook.getTitle().equals("")){
            book.setTitle(newBook.getTitle());
        }
        if(!newBook.getAuthor().equals("")){
            book.setAuthor(newBook.getAuthor());
        }
        if(!newBook.getGenre().equals("")){
            book.setGenre(newBook.getGenre());
        }
        if(!newBook.getYear().equals("")){
            book.setYear(newBook.getYear());
        }
        bookRepository.editBook(book);
        return true;
    }

    public AddedBook getAddedBook(AddedBook book) {
        AddedBook gotBook = null;
        System.out.println(book.getTitle() + " " + book.getAuthor());
        try {
            gotBook = bookRepository.getBookByTitleAndAuthor(book.getTitle(), book.getAuthor());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return gotBook;
    }

    public boolean BookDelete(Authentication authentication, AddedBook book){
        try {
            if (!Objects.equals(Objects.requireNonNull(userRepository.getAuthorizedUserByLogin(authentication.getName())).getRole(), "admin")){
                System.out.println(Objects.requireNonNull(userRepository.getAuthorizedUserByLogin(authentication.getName())).getRole() + "? admin");
                return false;
            }
            bookRepository.deleteBook(book.getId());
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public List<AddedBook> lastBookAdded(int listSize){
        try {
            return bookRepository.getLastAddedBookByTimestamp(listSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
