package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.repository.BookRepository;
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
    BookRepository bookRepository = new BookRepository();

    @GetMapping("/bookform")
    public String bookForm(Model model){
        if (!model.containsAttribute("bookForm")){
            model.addAttribute("bookForm", new AddedBook(null, null, null, null, null));
        }
        return "bookform";
    }

    @PostMapping("bookform/proceed")
    public String processBookAdding(@Valid @ModelAttribute("bookForm") AddedBook addedBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/bookform";
        }
        System.out.println(addedBook.getTitle());
        try {
            bookRepository.addBook(addedBook);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            return "redirect:/bookform";
        }
        return "bookform";
    }
}
