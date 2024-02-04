package ar.kanddys.springdialogflowtest.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import ar.kanddys.springdialogflowtest.model.ChatMessage;
import ar.kanddys.springdialogflowtest.model.TypingMessage;

@Controller
public class ChatController {

   @MessageMapping("/chat.sendMessage")
   @SendTo("/topic/public")
   public ChatMessage sendMessage(
         @Payload ChatMessage chatMessage) {
      return chatMessage;
   }

   @MessageMapping("/chat.addUser")
   @SendTo("/topic/public")
   public ChatMessage addUser(
         @Payload ChatMessage chatMessage,
         SimpMessageHeaderAccessor headerAccessor) {
      // Add username in web socket session
      headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
      return chatMessage;
   }

   @MessageMapping("/chat.messageTyping")
   @SendTo("/topic/public")
   public TypingMessage sendTyping(@Payload TypingMessage typingMessage) {
      return typingMessage;
   }
}
