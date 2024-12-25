package com.demo.chatApp.repo;

import com.demo.chatApp.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends MongoRepository<Room, String> {

    Room findByRoomId(String roomId);
}
