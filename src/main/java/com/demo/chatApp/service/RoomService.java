package com.demo.chatApp.service;

import com.demo.chatApp.entities.Message;
import com.demo.chatApp.entities.Room;
import com.demo.chatApp.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

    public Room createRoom(String roomId) {
        if (roomRepo.findByRoomId(roomId) != null) {
            throw new IllegalArgumentException("Room already exists");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        roomRepo.save(room);
        return room;
    }

    public Room joinRoom(String roomId) {
        Room room = roomRepo.findByRoomId(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        return room;
    }

    public List<Message> getMessages(String roomId, int page, int size) {
        Room room = roomRepo.findByRoomId(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }

        List<Message> messages = room.getMessages();
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        return messages.subList(start, end);
    }
}
