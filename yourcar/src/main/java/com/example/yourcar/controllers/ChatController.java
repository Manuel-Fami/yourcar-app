package com.example.yourcar.controllers;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.yourcar.entities.ChatMessage;
import com.example.yourcar.entities.User;
import com.example.yourcar.repositories.ChatRepository;
import com.example.yourcar.repositories.UserRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatRepository chatRepository; 
    private final UserRepository userRepository;

    public ChatController(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        chatRepository.save(message); 
        return message; 
    }
	 
    @GetMapping("/messages/{userId}")
    public List<ChatMessage> getMessagesByUserId(@PathVariable Integer userId) {
        return chatRepository.findByUserId(userId);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	
}