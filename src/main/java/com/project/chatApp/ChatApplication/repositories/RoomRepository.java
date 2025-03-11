package com.project.chatApp.ChatApplication.repositories;

import com.project.chatApp.ChatApplication.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
    Room findByRoomId(String roomId);
}
