package com.demo.chatApp.controller;

import com.demo.chatApp.entities.Message;
import com.demo.chatApp.entities.Room;
import com.demo.chatApp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {
        try {
            Room room = roomService.createRoom(roomId);
            return ResponseEntity.status(HttpStatus.CREATED).body(room);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
        try {
            Room room = roomService.joinRoom(roomId);
            return ResponseEntity.ok(room);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessage(@PathVariable String roomId,
                                                    @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                    @RequestParam(value = "size", defaultValue = "20", required = false) int size) {
        try {
            List<Message> messages = roomService.getMessages(roomId, page, size);
            return ResponseEntity.ok(messages);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
