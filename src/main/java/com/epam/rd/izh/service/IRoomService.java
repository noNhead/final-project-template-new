package com.epam.rd.izh.service;

import com.epam.rd.izh.dto.Room;

import java.util.List;

public interface IRoomService {

    Room get(Long id);

    Room create(Room room);

    List<Room> getAll();

    List<Room> search(String word);
}
