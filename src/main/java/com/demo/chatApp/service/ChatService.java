package com.demo.chatApp.service;

import com.demo.chatApp.entities.Message;
import com.demo.chatApp.entities.Room;
import com.demo.chatApp.payload.MessageRequest;
import com.demo.chatApp.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {

    @Autowired
    private RoomRepo roomRepo;

    public Message sendMessage(String roomId, MessageRequest request) {
        Room room = roomRepo.findByRoomId(request.getRoomId());
        if (room == null) {
            throw new RuntimeException("Room not found!");
        }

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimestamp(LocalDateTime.now());

        room.getMessages().add(message);
        roomRepo.save(room);

        return message;
    }
}
