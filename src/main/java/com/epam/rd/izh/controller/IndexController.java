package com.epam.rd.izh.controller;

import com.epam.rd.izh.service.BookDetailsServiceMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  BookDetailsServiceMapper bookDetailsServiceMapper = new BookDetailsServiceMapper();

  /**
   * Главная страница
   */
  @GetMapping("/")
  public String login(Model model) {
    int numberOfIndexSize = 10;
    model.addAttribute("listBook", bookDetailsServiceMapper.lastBookAdded(numberOfIndexSize));
    return "index";
  }

}
