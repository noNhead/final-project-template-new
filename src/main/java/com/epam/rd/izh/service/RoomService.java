package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.Message;
import com.epam.rd.izh.dto.Room;
import com.epam.rd.izh.dto.User;
import com.epam.rd.izh.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService implements IRoomService {

    private List<Room> rooms = new ArrayList<>();

    {
        User user = new User("Серега");

        Room room = new Room(1L, "Поговорим о Java", "Здесь мы общаемся о Java");

        List<Message> messages = new ArrayList<>();
        room.setMessages(messages);

        messages.add(new Message(user, "Здаров!", LocalDateTime.now().minusHours(4)));
        messages.add(new Message(user, "Кто здесь?!", LocalDateTime.now().minusHours(3)));
        messages.add(new Message(user, "Я обожаю Java", LocalDateTime.now().minusHours(2)));

        rooms.add(room);

        room = new Room(2L, "Поговорим о Kotlin", "Здесь мы общаемся о Kotlin");

        messages = new ArrayList<>();
        room.setMessages(messages);

        messages.add(new Message(user, "Здаров!", LocalDateTime.now().minusHours(4)));
        messages.add(new Message(user, "Кто здесь?!", LocalDateTime.now().minusHours(3)));
        messages.add(new Message(user, "Я обожаю Kotlin", LocalDateTime.now().minusHours(2)));

        rooms.add(room);
    }

    @Override
    public Room get(Long id) {
        return rooms.stream().filter(room -> room.getId().equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }

    @Override
    public Room create(Room room) {
        return null;
    }

    @Override
    public List<Room> getAll() {
        return rooms;
    }

    @Override
    public List<Room> search(String word) {
        return rooms.stream().filter(room -> room.getName().contains(word))
                .collect(Collectors.toList());
    }
}
