package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.repository.BookRepository;
import com.epam.rd.izh.service.BookDetailsServiceMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.sql.SQLException;

@Controller
public class BookController {
    BookDetailsServiceMapper bookDetailsServiceMapper = new BookDetailsServiceMapper();
    AddedBook objectBook = new AddedBook();
    BookRepository bookRepository = new BookRepository();

    @GetMapping("/bookform")
    public String bookForm(Model model){
        if (!model.containsAttribute("bookForm")){
            model.addAttribute("bookForm", new AddedBook());
        }
        return "bookform";
    }

    @PostMapping("bookform/proceed")
    public String processBookAdding(@Valid @ModelAttribute("bookForm") AddedBook addedBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/bookform";
        }
        try {
            bookRepository.addBook(addedBook);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/bookform";
    }

    @GetMapping("/bookedit")
    public String bookEdit(Model model){
        if (!model.containsAttribute("bookEdit")){
            model.addAttribute("bookEdit", new AddedBook());
        }
        return "bookedit";
    }

    @PostMapping("bookedit/proceed")
    public String processBookEdit(@Valid @ModelAttribute("bookEdit") AddedBook addedBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "bookedit";
        }
        if (addedBook.getTitle() != null && addedBook.getAuthor() != null) {
            try {
                objectBook = bookRepository.getBookByTitleAndAuthor(addedBook.getTitle(), addedBook.getAuthor());
                return "redirect:/bookdatachange";
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return "bookedit";
            }
        } else {
            return "bookedit";
        }
    }

    @GetMapping("bookdatachange")
    public String bookDataChange(Model model){
        model.addAttribute("objectBook", objectBook);
        model.addAttribute(new BookController());
        if (!model.containsAttribute("bookDataChange")){
            model.addAttribute("bookDataChange", new AddedBook());
        }
        return "bookdatachange";
    }

    @PostMapping("bookdatachange/proceed")
    public String processBookDataChange(@Valid @ModelAttribute("bookDataChange") AddedBook newBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:bookdatachange";
        }
        System.out.println(objectBook.toString());
        newBook.setId();
        System.out.println(newBook.toString());
        if(bookDetailsServiceMapper.BookDataChange(objectBook, newBook)){
            objectBook = null;
        } else {
            return "redirect:/bookdatachange";
        }
        return "redirect:/bookedit";
    }

    @GetMapping("bookdatachange/delete/process")
    public String bookDelete(Authentication authentication){
        if(!bookDetailsServiceMapper.BookDelete(authentication, objectBook)){
            return "redirect:/bookdatachange";
        } else {
            objectBook = null;
        }
        return "redirect:/bookdatachange";
    }
}
