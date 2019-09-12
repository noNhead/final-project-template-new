package com.epam.rd.izh.controller;

import com.epam.rd.izh.dto.Message;
import com.epam.rd.izh.dto.Room;
import com.epam.rd.izh.dto.User;
import com.epam.rd.izh.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("room")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @GetMapping("search")
    public ModelAndView search(@RequestParam(required = false, defaultValue = "")
            String word) {
        List<Room> rooms = roomService.search(word);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rooms");
        modelAndView.addObject("rooms", rooms);

        return modelAndView;
    }

    @GetMapping
    public ModelAndView get() {
        List<Room> rooms = roomService.getAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rooms");
        modelAndView.addObject("rooms", rooms);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getRoom(@PathVariable Long id) {
        Room room = roomService.get(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("room");
        modelAndView.addObject("room", room);

        return modelAndView;
    }

    @PostMapping(value = "/{id}/message")
    public ModelAndView addMessage(@PathVariable Long id, Message message) {
        Room room = roomService.get(id);

        User user = new User();
        user.setName("Серега");
        message.setAuthor(user);
        message.setCreationTime(LocalDateTime.now());

        room.getMessages().add(message);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/room/" + id);

        return modelAndView;
    }


}
