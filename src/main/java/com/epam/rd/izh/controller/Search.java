package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.service.BookDetailsServiceMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class Search extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    BookDetailsServiceMapper bookDetailsServiceMapper = new BookDetailsServiceMapper();

    /**
     * Метод поиска
     */
    @GetMapping("/search")
    public String searchPageWithoutParams(Model model){
        if(!model.containsAttribute("searchForm")) {
            model.addAttribute("searchForm", new AddedBook());
        }

        model.addAttribute("listBook", bookDetailsServiceMapper.lastBookAdded(20));
        return "search";
    }

    /**
     * Метод поиска
     */
    @GetMapping("/search/{tags}")
    public String searchPage(Model model, @PathVariable String tags){
        if(!model.containsAttribute("searchForm")){
            model.addAttribute("searchForm", new AddedBook());
        }

        List<AddedBook> listBook = bookDetailsServiceMapper.searchBook(tags);
        model.addAttribute("listBook", listBook);
        return "search";
    }

    /**
     * Метод поиска
     */
    @PostMapping("/search")
    public String searchPagePost(@Valid @ModelAttribute("searchForm") AddedBook book, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            System.out.println("Pikachu");
        }
        try {
            //t - title, a - author, g - genre, d - date
            return "redirect:/search/t="+ URLEncoder.encode(book.getTitle(), "UTF-8")+"a="+URLEncoder.encode(book.getAuthor(), "UTF-8")+"g="+URLEncoder.encode(book.getGenre(), "UTF-8")+"d="+URLEncoder.encode(book.getYear(), "UTF-8")+"&";
        } catch (UnsupportedEncodingException e) {
            LOGGER.fatal(e.getMessage());
        }
        return "redirect:/search";
    }
}
