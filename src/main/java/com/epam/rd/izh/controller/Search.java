package com.epam.rd.izh.controller;

import com.epam.rd.izh.entity.AddedBook;
import com.epam.rd.izh.service.BookDetailsServiceMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class Search extends HttpServlet {
    BookDetailsServiceMapper bookDetailsServiceMapper = new BookDetailsServiceMapper();

    @GetMapping("/search")
    public String searchPageWithoutParams(Model model){
        if(!model.containsAttribute("searchForm")) {
            model.addAttribute("searchForm", new AddedBook());
        }
        return "search";
    }

    @GetMapping("/search/{tags}")
    public String searchPage(Model model, HttpServletResponse httpServletResponse, @PathVariable String tags){
        if(!model.containsAttribute("searchForm")){
            model.addAttribute("searchForm", new AddedBook());
        }
        AddedBook book = tagsPars(tags);
        processRequest(httpServletResponse, bookDetailsServiceMapper.searchBook(book));
        return "search";

    }

    private AddedBook tagsPars(String tags){
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
            book.setTitle("0");
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
            book.setAuthor("0");
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
            book.setGenre("0");
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
            book.setYear("0");
        }
        System.out.println(book.getTitle()+book.getAuthor()+book.getGenre()+book.getYear());
        return book;
    }

    @PostMapping("/search")
    public String searchPagePost(@Valid @ModelAttribute("searchForm") AddedBook book, BindingResult bindingResult, HttpServletResponse httpServletResponse){
        if(bindingResult.hasErrors()) {
            System.out.println("Pikachu");
        }
        try {
            return "redirect:/search/t="+ URLEncoder.encode(book.getTitle(), "UTF-8")+"a="+URLEncoder.encode(book.getAuthor(), "UTF-8")+"g="+URLEncoder.encode(book.getGenre(), "UTF-8")+"d="+URLEncoder.encode(book.getYear(), "UTF-8")+"&";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "redirect:/search";
    }

    protected void processRequest(HttpServletResponse httpServletResponse, List<AddedBook> list){
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("utf-8");
        try (PrintWriter out = httpServletResponse.getWriter()){
            String json = new Gson().toJson(list);
            System.out.println(json);
            out.print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
