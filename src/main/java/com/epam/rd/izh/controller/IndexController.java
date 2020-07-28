package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.Message;
import com.epam.rd.izh.service.BookDetailsServiceMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.epam.rd.izh.util.StringConstants.ENG_GREETING;

@Controller
public class IndexController {
  BookDetailsServiceMapper bookDetailsServiceMapper = new BookDetailsServiceMapper();

  @GetMapping("/")
  public String login(Authentication authentication, Model model) {
    Message greetingMessage = new Message();
    greetingMessage.setMessage(ENG_GREETING + authentication.getName());

    model.addAttribute("message", greetingMessage.getMessage());
    int numberOfIndexSize = 10;
    model.addAttribute("listBook", bookDetailsServiceMapper.lastBookAdded(numberOfIndexSize));
    return "index";
  }

}
