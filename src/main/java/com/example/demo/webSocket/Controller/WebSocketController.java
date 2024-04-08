package com.example.demo.webSocket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dtos.ChatMessage;

@Controller
public class WebSocketController {
       @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/{roomId}")
    public ChatMessage chat(@DestinationVariable String roomId, ChatMessage message) {
       System.out.println(roomId);
        System.out.println(message);
        return new ChatMessage(message.getMessage(), message.getUser());
    }

    @MessageMapping("/send/{userId}")
    public SimpMessagingTemplate sendMessageToUser(@DestinationVariable String userId,ChatMessage message) {
        // Envoyer un message à un utilisateur spécifiqu
      
        messagingTemplate.convertAndSendToUser(userId, "/queue/messages", new ChatMessage(message.getMessage(), message.getUser()));
      
        return messagingTemplate;
    }

}