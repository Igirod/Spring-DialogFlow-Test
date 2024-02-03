package ar.kanddys.springdialogflowtest.config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ar.kanddys.springdialogflowtest.model.ChatMessage;
import ar.kanddys.springdialogflowtest.model.MessageType;
import lombok.RequiredArgsConstructor;

/**
 * Esta clase será la encargada de capturar los eventos para mantener
 * actualizados a todos los usuarios que se encuentran conectados
 * a nuestro webscoket.
 * 
 * @author Igirod0
 * @version 1.0.0
 */
@Component // Se carga en memoría y está disponible cuando sea llamado.
@RequiredArgsConstructor
public class WebSocketEventListener {

   private final SimpMessageSendingOperations messagingTemplate;

   @EventListener
   public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
      StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
      String username = (String) headerAccessor.getSessionAttributes().get("username");
      if (username != null) {
         var chatMessage = ChatMessage.builder()
               .type(MessageType.LEAVE)
               .sender(username)
               .build();
         messagingTemplate.convertAndSend("/topic/public", chatMessage);
      }
   }
}