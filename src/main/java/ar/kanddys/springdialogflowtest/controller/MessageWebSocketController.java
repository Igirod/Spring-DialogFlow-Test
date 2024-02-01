package ar.kanddys.springdialogflowtest.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import ar.kanddys.springdialogflowtest.model.Message;

@Controller
public class MessageWebSocketController {

   @MessageMapping("/status")
   @SendTo("/topic/messages")
   public Message sendMessage(Message message) {
      return new Message("Message with text : " + message.getText() + " received ", " from " + message.getName());
   }

}
