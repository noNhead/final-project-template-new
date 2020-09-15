package com.epam.rd.izh.controller;

import com.epam.rd.izh.service.BookDetailsServiceMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Download extends HttpServlet {
    private  BookDetailsServiceMapper bookDetailsServiceMapper;

    /**
     * Загрузка с сервера
     */
    @GetMapping("/book/download/{author}/{title}")
    public void download(HttpServletResponse httpServletResponse, @PathVariable String author, @PathVariable String title){
        boolean flag = bookDetailsServiceMapper.downloading(httpServletResponse, title, author);
    }
}
