package ar.kanddys.springdialogflowtest.model;

import lombok.Data;

@Data
public class Message {

   public Message(String name, String message) {
      super();
      this.name = name;
      this.text = message;
   }
   private String text;
   private String name;
}
