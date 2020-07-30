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

    /**
     * Метод отвечающий за добавление книги
     */
    @GetMapping("/bookform")
    public String bookForm(Model model){
        if (!model.containsAttribute("bookForm")){
            model.addAttribute("bookForm", new AddedBook());
        }
        return "bookform";
    }

    /**
     * Метод отвечающий за добавление книги
     */
    @PostMapping("/bookform/proceed")
    public String processBookAdding(@Valid @ModelAttribute("bookForm") AddedBook addedBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:/bookform";
        }

        if (!bookDetailsServiceMapper.bookAdding(addedBook)){
            return "redirect:/404";
        }
        return "redirect:/bookform";
    }

    /**
     * Поиск книги перед редактированием по Названию и Автору
     */
    @GetMapping("/bookedit")
    public String bookEdit(Model model){
        if (!model.containsAttribute("bookEdit")){
            model.addAttribute("bookEdit", new AddedBook());
        }
        return "bookedit";
    }

    /**
     * Поиск книги перед редактированием по Названию и Автору
     */
    @PostMapping("/bookedit/proceed")
    public String processBookEdit(@Valid @ModelAttribute("bookEdit") AddedBook addedBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "bookedit";
        }
        if (addedBook.getTitle() != null && addedBook.getAuthor() != null) {
            objectBook = bookDetailsServiceMapper.getAddedBook(addedBook);
            return "redirect:/bookdatachange";
        } else {
            return "redirect:/bookedit";
        }
    }

    /**
     * Редактирование данных книги
     */
    @GetMapping("/bookdatachange")
    public String bookDataChange(Model model){
        model.addAttribute("objectBook", objectBook);
        model.addAttribute(new BookController());
        if (!model.containsAttribute("bookDataChange")){
            model.addAttribute("bookDataChange", new AddedBook());
        }
        return "bookdatachange";
    }
    /**
     * Редактирование данных книги
     */
    @PostMapping("/bookdatachange/proceed")
    public String processBookDataChange(@Valid @ModelAttribute("bookDataChange") AddedBook newBook, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "redirect:bookdatachange";
        }
        newBook.setId();
        if (newBook.getTitle() == null) {
                newBook.setTitle("0&");
        }
        if (newBook.getAuthor() == null) {
            newBook.setAuthor("0&");
        }
        if (newBook.getGenre() == null) {
            newBook.setGenre("0&");
        }
        if (newBook.getYear() == null) {
            newBook.setYear("0&");
        }
        if(bookDetailsServiceMapper.bookDataChange(objectBook, newBook)){
            objectBook = null;
        } else {
            return "redirect:/bookdatachange";
        }
        return "redirect:/bookedit";
    }

    /**
     * Удаление книги, если есть права администратора
     */
    @GetMapping("/bookdatachange/delete/process")
    public String bookDelete(Authentication authentication){
        if(!bookDetailsServiceMapper.bookDelete(authentication, objectBook)){
            return "redirect:/bookdatachange";
        } else {
            objectBook = null;
        }
        return "redirect:/bookdatachange";
    }
    /**
     * Удаление книги, если есть права администратора
     */
    @GetMapping("/book/{author}/{title}")
    public String boolTitlePage(Model model, @PathVariable("author") String author, @PathVariable("title") String title){
        AddedBook book = new AddedBook();
        book.setTitle(title);
        book.setAuthor(author);

        book = bookDetailsServiceMapper.getAddedBook(book);
        model.addAttribute("book", book);
        return "book";
    }

}
