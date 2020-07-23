package com.epam.rd.izh.dto;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.entity.AuthorizedUser;
import com.epam.rd.izh.repository.UserRepository;
import com.epam.rd.izh.service.BookDetailsServiceMapper;
import org.springframework.security.core.Authentication;

import java.sql.SQLException;
import java.util.Objects;

public class BookDataChanger {
    UserRepository userRepository;
    public AddedBook BookDataChange(AddedBook book, AddedBook newBook) {
        if(newBook.getTitle() != null){
            book.setTitle(newBook.getTitle());
        }
        if(newBook.getAuthor() != null){
            book.setAuthor(newBook.getAuthor());
        }
        if(newBook.getGenre() != null){
            book.setGenre(newBook.getGenre());
        }
        if(newBook.getYear() != null){
            book.setYear(newBook.getYear());
        }
        if(newBook.getImgUrl() != null){
            book.setImgUrl(newBook.getImgUrl());
        }

        return book;
    }

    public boolean UserRoleCheck(Authentication authentication){
        try {
            if (Objects.equals(Objects.requireNonNull(userRepository.getAuthorizedUserByLogin(authentication.getName())).getRole(), "admin")){
                return true;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return false;
    }

}
