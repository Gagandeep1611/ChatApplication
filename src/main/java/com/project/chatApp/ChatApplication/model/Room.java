package com.project.chatApp.ChatApplication.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "rooms")
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private String id;
    private String roomId;

    private List<Message> messages = new ArrayList<>();

    public String getRoomId() {
        return roomId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
