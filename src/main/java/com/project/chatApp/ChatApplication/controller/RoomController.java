package com.project.chatApp.ChatApplication.controller;

import com.project.chatApp.ChatApplication.model.Message;
import com.project.chatApp.ChatApplication.model.Room;
import com.project.chatApp.ChatApplication.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    //create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId){
        if(roomRepository.findByRoomId(roomId)!=null){
            // room exists
            return ResponseEntity.badRequest().body("Room Already Exists!");
        }
        //create new room
        Room room = new Room();
        room.setRoomId(roomId);
        Room saveRoom = roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRoom);
    }

    //get room
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(@PathVariable String roomId){
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().body("Room doesn't Exist");
        }
        return ResponseEntity.ok().body(room);
    }

    //get messages of room
    @GetMapping("/getroom/{roomId}")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId, @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ){
        Room room = roomRepository.findByRoomId(roomId);
        if(room == null){
            return ResponseEntity.badRequest().build();
        }

        List<Message> messages = room.getMessages();

        //Pagination:
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start+size);
        List<Message> paginatedMessages = messages.subList(start, end);
        return ResponseEntity.ok(messages);
    }
}
