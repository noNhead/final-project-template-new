package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.service.BookDetailsServiceMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class BookController {
    BookDetailsServiceMapper bookDetailsServiceMapper;

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
        if(!bookDetailsServiceMapper.BookAdding(addedBook)){
            return "redirect:/bookform";
        }

        return "bookform";
    }
}
