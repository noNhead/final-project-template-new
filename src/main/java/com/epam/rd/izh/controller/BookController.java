package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.service.BookDetailsServiceMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BookController {
    BookDetailsServiceMapper bookDetailsServiceMapper = new BookDetailsServiceMapper();

    AddedBook objectBook = new AddedBook();

    @GetMapping("/bookform")
    public String bookForm(Model model){
        if (!model.containsAttribute("bookForm")){
            model.addAttribute("bookForm", new AddedBook());
        }
        return "bookform";
    }

    @PostMapping("/bookform/proceed")
    public String processBookAdding(@Valid @ModelAttribute("bookForm") AddedBook addedBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/bookform";
        }
        //Возможные проблемы с генерацией UUID а также с валидацией и нуллпоинтерами
        bookDetailsServiceMapper.BookAdding(addedBook);
        return "redirect:/bookform";
    }

    @GetMapping("/bookedit")
    public String bookEdit(Model model){
        if (!model.containsAttribute("bookEdit")){
            model.addAttribute("bookEdit", new AddedBook());
        }
        return "bookedit";
    }

    @PostMapping("/bookedit/proceed")
    public String processBookEdit(@Valid @ModelAttribute("bookEdit") AddedBook addedBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "bookedit";
        }
        if (addedBook.getTitle() != null && addedBook.getAuthor() != null) {
            objectBook = bookDetailsServiceMapper.getAddedBook(addedBook);
            return "redirect:/bookdatachange";
        } else {
            return "bookedit";
        }
    }

    @GetMapping("/bookdatachange")
    public String bookDataChange(Model model){
        model.addAttribute("objectBook", objectBook);
        model.addAttribute(new BookController());
        if (!model.containsAttribute("bookDataChange")){
            model.addAttribute("bookDataChange", new AddedBook());
        }
        return "bookdatachange";
    }

    @PostMapping("/bookdatachange/proceed")
    public String processBookDataChange(@Valid @ModelAttribute("bookDataChange") AddedBook newBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:bookdatachange";
        }
        newBook.setId();
        if(bookDetailsServiceMapper.BookDataChange(objectBook, newBook)){
            objectBook = null;
        } else {
            return "redirect:/bookdatachange";
        }
        return "redirect:/bookedit";
    }

    @GetMapping("/bookdatachange/delete/process")
    public String bookDelete(Authentication authentication){
        if(!bookDetailsServiceMapper.BookDelete(authentication, objectBook)){
            return "redirect:/bookdatachange";
        } else {
            objectBook = null;
        }
        return "redirect:/bookdatachange";
    }

    @GetMapping("/book/{author}/{title}")
    public String boolTitlePage(Model model, @PathVariable("author") String author, @PathVariable("title") String title){
        AddedBook book = new AddedBook();
        System.out.println(author + " " + title);
        book.setTitle(title);
        book.setAuthor(author);

        book = bookDetailsServiceMapper.getAddedBook(book);
        model.addAttribute("book", book);
        return "book";
    }

}
